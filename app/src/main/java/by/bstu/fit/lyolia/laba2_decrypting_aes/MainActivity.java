package by.bstu.fit.lyolia.laba2_decrypting_aes;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText stringEt;
    EditText encryptedTextEt;
    Button getButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stringEt = (EditText) findViewById(R.id.stringEt);
        encryptedTextEt = (EditText) findViewById(R.id.encryptedEt);
        getButton = (Button) findViewById(R.id.getButton);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
    }

    public void onGetClick(View view) {
        String key = Misc.readFileSD("AES-key.txt", this.getApplicationContext());
        stringEt.setText(Misc.decrypting(encryptedTextEt.getText().toString(), key));
        Log.d(Misc.TAG, key);
        //INGUmDWLsffHI6tHC6H0ug==
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getButton.setEnabled(true);
                } else {
                    getButton.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }
}
