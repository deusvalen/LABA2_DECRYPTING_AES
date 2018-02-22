package by.bstu.fit.lyolia.laba2_decrypting_aes;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by User on 14.02.2018.
 */

public class Misc {

    public static final String TAG = "TEST";

    @Nullable
    public static String readFileSD(String filename, Context context) {

        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return null;
        }

        File sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath() + File.separator + "AES");
        File sdFile = new File(sdPath, filename);
        String str = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
                return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static String decrypting (String string, String key) {

        byte[] decodedBytes = null;
        try {
            SecretKeySpec sks = new SecretKeySpec(Base64.decode(key, Base64.DEFAULT), "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, sks);
            decodedBytes = c.doFinal(Base64.decode(string, Base64.DEFAULT));
            return new String(decodedBytes);
        } catch (Exception e) {
            Log.e(TAG, "AES decryption error");
        }
        return null;
    }
}
