package com.example.reza.honarjo.Controller.parcelableUtil;

import android.util.Log;

import com.example.reza.honarjo.Model.alarm.DBAlarm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ParcelableUtil {
    private static final String TAG = ParcelableUtil.class.getName();
    public static byte[] toByteArray(DBAlarm alarm) {
        if (alarm == null) {
            Log.e(TAG, "alarm is null");
            return null;
        }
        byte[] data = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(alarm);
            out.flush();
            data = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }

    public static DBAlarm getFromByteArray(byte[] data) {
        if (data == null) {
            Log.e(TAG, "Byte array is null");
            return null;
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInput in = null;
        DBAlarm dbAlarm = null;
        try {
            in = new ObjectInputStream(bis);
            dbAlarm = (DBAlarm) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return dbAlarm;
    }
}