package edu.thu.ggxx.androidsensortool;

import android.app.Activity;
import android.hardware.Sensor;
import android.os.Bundle;
import android.widget.TextView;
import edu.thu.ggxx.androidsensortool.util.SensorUtil;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ggxx
 * Date: 13-10-23
 * Time: 下午10:41
 * To change this template use File | Settings | File Templates.
 */
public class SensorsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors);

        TextView tvSensors = (TextView)findViewById(R.id.textView_Sensors);
        List<Sensor> sensors = SensorUtil.getSensorList(this);// 获得传感器列表
        StringBuffer str = new StringBuffer();
        str.append("该手机有" + sensors.size() + "个传感器,分别是:\n\n");
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
                    str.append(i + "光线传感器");
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    str.append(i + "地磁场传感器");
                    break;
                case Sensor.TYPE_ORIENTATION:
                    //方向传感器实际上并不存在对应的硬件设备，它是完全基于软件的，它的数据来自于地磁场传感器和加速度传感器
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
                case Sensor.TYPE_GRAVITY:
                    str.append(i + "重力传感器");
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION:
                    str.append(i + "线性加速度传感器");
                    break;
                case Sensor.TYPE_ROTATION_VECTOR:
                    str.append(i + "旋转矢量传感器");
                    break;
                default:
                    str.append(i + "未知传感器,类型:" + s.getType());
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
}