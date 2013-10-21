package edu.thu.ggxx.androidsensortool.util;

import android.content.Context;
import android.hardware.Sensor;

/**
 * Created with IntelliJ IDEA.
 * User: ggxx
 * Date: 10/21/13
 * Time: 11:23 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ISensorUtil {
    Sensor getSensor(int sensorType, Context context);

}
