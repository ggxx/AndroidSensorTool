package edu.thu.ggxx.AndroidSensorTool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    private SensorManager sensorManager;
    private List<Sensor> sensors;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final TextView tvSensors = (TextView) findViewById(R.id.textView_Sensors);

        Button btnProximity = (Button) findViewById(R.id.button_Proximity);
        btnProximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProximityActivity.class);
                MainActivity.this.startActivity(intent);
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
                sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);// 获得传感器列表

                StringBuffer str = new StringBuffer();
                str.append("该手机有" + sensors.size() + "个传感器,分别是:\n");
                for (int i = 0; i < sensors.size(); i++) {
                    Sensor s = sensors.get(i);
                    switch (s.getType()) {
                        case Sensor.TYPE_ACCELEROMETER:
                            str.append(i + "加速度传感器");
                            break;
                        case Sensor.TYPE_GYROSCOPE:
                            str.append(i + "陀螺仪传感器");
                            break;
                        case Sensor.TYPE_LIGHT:
                            str.append(i + "环境光线传感器");
                            break;
                        case Sensor.TYPE_MAGNETIC_FIELD:
                            str.append(i + "电磁场传感器");
                            break;
                        case Sensor.TYPE_ORIENTATION:
                            str.append(i + "方向传感器");
                            break;
                        case Sensor.TYPE_PRESSURE:
                            str.append(i + "压力传感器");
                            break;
                        case Sensor.TYPE_PROXIMITY:
                            str.append(i + "距离传感器");
                            break;
                        case Sensor.TYPE_TEMPERATURE:
                            str.append(i + "温度传感器");
                            break;
                        default:
                            str.append(i + "未知传感器");
                            break;
                    }
                    str.append("\n");
                    str.append("设备名称:" + s.getName() + "\n");
                    str.append("设备版本:" + s.getVersion() + "\n");
                    str.append("通用类型号:" + s.getType() + "\n");
                    str.append("设备商名称:" + s.getVendor() + "\n");
                    str.append("传感器功耗:" + s.getPower() + "\n");
                    str.append("传感器分辨率:" + s.getResolution() + "\n");
                    str.append("传感器最大量程:" + s.getMaximumRange() + "\n\n");
                }
                tvSensors.setText(str);
            }
        });
    }
}
