package edu.thu.ggxx.androidsensortool;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: ggxx
 * Date: 13-10-23
 * Time: 下午10:00
 * To change this template use File | Settings | File Templates.
 */
public class OrientationActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView tvAccX;
    private TextView tvAccY;
    private TextView tvAccZ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.orientation);

        tvAccX = (TextView) findViewById(R.id.textView_X);
        tvAccY = (TextView) findViewById(R.id.textView_Y);
        tvAccZ = (TextView) findViewById(R.id.textView_Z);
        sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        tvAccZ.setText("Z方向：" + String.valueOf(values[0]));
        tvAccX.setText("X方向：" + String.valueOf(values[1]));
        tvAccY.setText("Y方向：" + String.valueOf(values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}