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
 * Time: 下午10:30
 * To change this template use File | Settings | File Templates.
 */
public class PressureActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView tvPressure;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.pressure);

        tvPressure = (TextView) findViewById(R.id.textView_Pressure);
        sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        tvPressure.setText("压力：" + String.valueOf(values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}