package my.kylogger.johnmelodyme.iot.embedded.cryptography.http.rsaencryption;
/**
 *                   Copyright 2020 © John Melody Me
 *
 *       Licensed under the Apache License, Version 2.0 (the "License");
 *       you may not use this file except in compliance with the License.
 *       You may obtain a copy of the License at
 *
 *                   http://www.apache.org/licenses/LICENSE-2.0
 *
 *       Unless required by applicable law or agreed to in writing, software
 *       distributed under the License is distributed on an "AS IS" BASIS,
 *       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *       See the License for the specific language governing permissions and
 *       limitations under the License.
 *       @Author : John Melody Me
 *       @Copyright: John Melody Me & Tan Sin Dee © Copyright 2020
 *       @INPIREDBYGF: Cindy Tan Sin Dee <3
 */

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.security.Key;
import java.security.KeyPair;
import java.util.Arrays;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class EncryptionActivity extends AppCompatActivity {
    public static final String TAG = "RSA";
    private EditText inputText;
    private TextView dispE, dispD;
    private Button Encryption, Decryption, Clear;
    public Key privateKey, publicKey;

    public void DeclarationInit() {
        inputText = findViewById(R.id.textInput);
        dispE = findViewById(R.id.dispEncrypted);
        dispD = findViewById(R.id.dispDecrypted);
        Encryption = findViewById(R.id.encrypt);
        Decryption = findViewById(R.id.decrypt);
        Clear = findViewById(R.id.clear);

        KEY_DECLARATION();
    }

    public void KEY_DECLARATION() {
        KeyPair keyPair = RSAKeyPair.keyPairRSA();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, EncryptionActivity.class.getSimpleName()) ;
        DeclarationInit();

        Encryption.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String input;
                byte[] encrypted;
                input = inputText.getText().toString().trim();
                encrypted = RSAEncryptDecrypt.encrypt(input, privateKey);
                dispE.setText("Encrypted : " + "{ \n " + Arrays.toString(encrypted) + "\n}");
                dispD.setVisibility(View.INVISIBLE);
                Log.d(TAG, "Encrypted Data " +input+ "{ \n " + Arrays.toString(encrypted) + "\n}");
            }
        });

        Decryption.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String dinput;
                dinput = inputText.getText().toString().trim();
                dispE.setText("Decrypted : " + dinput);
            }
        });

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispD.setText(null);
                dispE.setText(null);
                dispD.setText(getResources().getString(R.string.decryption));
                dispE.setText(getResources().getString(R.string.encryption));
            }
        });
    }

    @Override
    // TODO  onCreateOptionsMenu()
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    // TODO onOptionsItemSelected()
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.about) {
            new SweetAlertDialog(EncryptionActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                    .setTitleText("About")
                    .setContentText("Developed By John Melody Me")
                    .setCustomImage(R.mipmap.applogo)
                    .show();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}