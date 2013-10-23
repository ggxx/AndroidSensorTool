package edu.thu.ggxx.androidsensortool;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import edu.thu.ggxx.androidsensortool.util.SensorUtil;

public class MainActivity extends Activity {

    private static String TAG = "MainActivity";

    private void showUnsupportedSensor() {
        Toast.makeText(this, "unsupported sensor", Toast.LENGTH_SHORT).show();
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btnAccelerometer = (Button) findViewById(R.id.button_Accelerometer);
        btnAccelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SensorUtil.isSupported(MainActivity.this, Sensor.TYPE_ACCELEROMETER)) {
                    Intent intent = new Intent(MainActivity.this, AccelerometerActivity.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    showUnsupportedSensor();
                }
            }
        });

        Button btnOrientation = (Button) findViewById(R.id.button_Orientation);
        btnOrientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SensorUtil.isSupported(MainActivity.this, Sensor.TYPE_ORIENTATION)) {
                    Intent intent = new Intent(MainActivity.this, OrientationActivity.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    showUnsupportedSensor();
                }
            }
        });

        Button btnLight = (Button) findViewById(R.id.button_Light);
        btnLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SensorUtil.isSupported(MainActivity.this, Sensor.TYPE_LIGHT)) {
                    Intent intent = new Intent(MainActivity.this, LightActivity.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    showUnsupportedSensor();
                }
            }
        });

        Button btnMagneticField = (Button) findViewById(R.id.button_Magnetic);
        btnMagneticField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SensorUtil.isSupported(MainActivity.this, Sensor.TYPE_MAGNETIC_FIELD)) {
                    Intent intent = new Intent(MainActivity.this, MagneticFieldActivity.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    showUnsupportedSensor();
                }
            }
        });

        Button btnPressure = (Button) findViewById(R.id.button_Pressure);
        btnPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SensorUtil.isSupported(MainActivity.this, Sensor.TYPE_PRESSURE)) {
                    Intent intent = new Intent(MainActivity.this, PressureActivity.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    showUnsupportedSensor();
                }
            }
        });

        Button btnProximity = (Button) findViewById(R.id.button_Proximity);
        btnProximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SensorUtil.isSupported(MainActivity.this, Sensor.TYPE_PROXIMITY)) {
                    Intent intent = new Intent(MainActivity.this, ProximityActivity.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    showUnsupportedSensor();
                }
            }
        });

        Button btnRecord = (Button) findViewById(R.id.button_Record);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button btnPrintAll = (Button) findViewById(R.id.button_PrintAll);
        btnPrintAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SensorsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
