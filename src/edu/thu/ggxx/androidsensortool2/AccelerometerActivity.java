package edu.thu.ggxx.androidsensortool;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: ggxx
 * Date: 13-10-23
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */
public class AccelerometerActivity extends Activity implements SensorEventListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.accelerometer);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}