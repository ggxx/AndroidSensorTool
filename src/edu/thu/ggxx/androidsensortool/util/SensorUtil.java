package edu.thu.ggxx.androidsensortool.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Vibrator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ggxx
 * Date: 10/21/13
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SensorUtil {

    public static boolean isSupported(Context context, int sensorType) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(sensorType) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static List<Sensor> getSensorList(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        return sensorManager.getSensorList(Sensor.TYPE_ALL);
    }
}
