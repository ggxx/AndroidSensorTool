package edu.thu.ggxx.AndroidSensorTool;

import android.app.Activity;
import android.hardware.SensorManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: ggxx
 * Date: 10/21/13
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecordActivity extends Activity {

    public static final int MESSAGE_ID = 0x01;
    private RecordThread recordThread;
    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);

        final TextView tv = (TextView) findViewById(R.id.textView);

        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case RecordActivity.MESSAGE_ID:
                        //tv.invalidate();
                        tv.setText(msg.getData().getString("Test"));
                        break;
                }
                super.handleMessage(msg);
            }
        };

        recordThread = new RecordThread();
        recordThread.start();
    }

    @Override
    protected void onResume() {
        recordThread.start();
        super.onResume();
    }

    @Override
    protected void onPause() {
        recordThread.pause();
        super.onPause();
    }

    public class RecordThread extends Thread {

        private AudioRecord ar;
        private int bs;
        private int SAMPLE_RATE_IN_HZ = 8000;
        private boolean isRun = false;

        public RecordThread() {
            super();
            bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT);
            ar = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT, bs);


            ar = findAudioRecord();
            //ar.release();
        }


        private int[] mSampleRates = new int[]{8000, 11025, 22050, 44100};

        public AudioRecord findAudioRecord() {
            for (int rate : mSampleRates) {
                for (short audioFormat : new short[]{AudioFormat.ENCODING_PCM_8BIT, AudioFormat.ENCODING_PCM_16BIT}) {
                    for (short channelConfig : new short[]{AudioFormat.CHANNEL_IN_MONO, AudioFormat.CHANNEL_IN_STEREO}) {
                        try {
                            Log.d("TAG", "Attempting rate " + rate + "Hz, bits: " + audioFormat + ", channel: "
                                    + channelConfig);
                            int bufferSize = AudioRecord.getMinBufferSize(rate, channelConfig, audioFormat);

                            if (bufferSize != AudioRecord.ERROR_BAD_VALUE) {
                                // check if we can instantiate and have a success
                                AudioRecord recorder = new AudioRecord(MediaRecorder.AudioSource.DEFAULT, rate, channelConfig, audioFormat, bufferSize);

                                if (recorder.getState() == AudioRecord.STATE_INITIALIZED)
                                    return recorder;
                            }
                        } catch (Exception e) {
                            Log.e("TAG", rate + "Exception, keep trying.", e);
                        }
                    }
                }
            }
            return null;
        }


        @Override
        public void run() {

            super.run();

            ar.startRecording();
            // 用于读取的 buffer
            byte[] buffer = new byte[bs];
            isRun = true;
            while (isRun) {
                int r = ar.read(buffer, 0, bs);
                int v = 0;
                // 将 buffer 内容取出，进行平方和运算
                for (int i = 0; i < buffer.length; i++) {
                    // 这里没有做运算的优化，为了更加清晰的展示代码
                    v += buffer[i] * buffer[i];
                }
                // 平方和除以数据总长度，得到音量大小。可以获取白噪声值，然后对实际采样进行标准化。
                // 如果想利用这个数值进行操作，建议用 sendMessage 将其抛出，在 Handler 里进行处理。
                Log.d("spl", String.valueOf(v / (float) r));

                Message message = new Message();
                message.what = RecordActivity.MESSAGE_ID;
                Bundle bundle = new Bundle();
                bundle.putString("Test", String.valueOf(v / (float) r));
                message.setData(bundle);
                RecordActivity.this.handler.sendMessage(message);
            }
            ar.stop();
        }

        public void pause() {
            // 在调用本线程的 Activity 的 onPause 里调用，以便 Activity 暂停时释放麦克风
            isRun = false;
        }

        public void start() {
            // 在调用本线程的 Activity 的 onResume 里调用，以便 Activity 恢复后继续获取麦克风输入音量
            if (!isRun) {
                super.start();
            }
        }
    }
}