package edu.thu.ggxx.androidsensortool;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: ggxx
 * Date: 10/21/13
 * Time: 9:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProximityActivity extends Activity implements SensorEventListener {

    private static final String TAG = "ProximityActivity";
    private SensorManager sensorManager;
    private Sensor sensor;
    private Vibrator vibrator;
    private float lastVal = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(ProximityActivity.TAG, "onCreate...");
        // Android 所有的传感器的统一接口
        this.sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        // 取得距离传感器
        this.sensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        // 用振动来反应距离的变化
        this.vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(ProximityActivity.TAG, "registerListener...");
        // 一定要在这注册
        this.sensorManager.registerListener(this, this.sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ProximityActivity.TAG, "unregisterListener...");
        // 一定要在这解注册
        this.sensorManager.unregisterListener(this, this.sensor);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
        Log.d(ProximityActivity.TAG, "onSensorChanged...");
        // 目前的距离
        float thisVal = event.values[0];
        if (this.lastVal == -1) {
            // 第一次进来
            this.lastVal = thisVal;
        } else {
            if (thisVal < this.lastVal) {
                // 接近长振动
                this.vibrator.vibrate(200);
            } else {
                // 离开短振动
                this.vibrator.vibrate(100);
            }
            this.lastVal = thisVal;
        }
        String msg = "Current val: " + this.lastVal;
        Log.d(ProximityActivity.TAG, msg);

/*
        long milliseconds = SystemClock.elapsedRealtime();
        synchronized (mLocks) {
            float distance = event.values[0];  //检测到手机和人体的距离
            long timeSinceLastEvent = milliseconds - mLastProximityEventTime;  //这次检测和上次检测的时间差
            mLastProximityEventTime = milliseconds;  //更新上一次检测的时间
            mHandler.removeCallbacks(mProximityTask);
            boolean proximityTaskQueued = false;

            // compare against getMaximumRange to support sensors that only return 0 or 1
            boolean active = (distance >= 0.0 && distance < PROXIMITY_THRESHOLD && distance < mProximitySensor.getMaximumRange());  //如果距离小于某一个距离阈值，默认是5.0f，说明手机和脸部距离贴近，应该要熄灭屏幕。

            if (mDebugProximitySensor) {
                Slog.d(TAG, "mProximityListener.onSensorChanged active: " + active);
            }
            if (timeSinceLastEvent < PROXIMITY_SENSOR_DELAY) {
                // enforce delaying atleast PROXIMITY_SENSOR_DELAY before processing
                mProximityPendingValue = (active ? 1 : 0);
                mHandler.postDelayed(mProximityTask, PROXIMITY_SENSOR_DELAY - timeSinceLastEvent);
                proximityTaskQueued = true;
            } else {
                // process the value immediately
                mProximityPendingValue = -1;
                proximityChangedLocked(active);   //熄灭屏幕操作
            }

            // update mProximityPartialLock state
            boolean held = mProximityPartialLock.isHeld();
            if (!held && proximityTaskQueued) {
                // hold wakelock until mProximityTask runs
                mProximityPartialLock.acquire();
            } else if (held && !proximityTaskQueued) {
                mProximityPartialLock.release();
            }
        }*/
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}