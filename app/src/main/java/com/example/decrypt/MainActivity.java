package com.example.decrypt;

// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputText = findViewById(R.id.inputText);
        final TextView outputText = findViewById(R.id.outputText);
        Button decryptButton = findViewById(R.id.Decrypt_Buuton);
        EditText key = findViewById(R.id.Key);

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputText.getText().toString();
                int shift;
                try {
                    shift = Integer.parseInt(key.getText().toString());
                } catch (NumberFormatException e) {
                    // Handle the case where the input cannot be parsed as an integer
                    e.printStackTrace();
                    // You might want to show an error message to the user
                    return;
                }
                String decryptedText = decrypt(input, shift); // Use your encryption logic here
                outputText.setText(decryptedText);
            }
        });

    }

    // Caesar Cipher encryption method
    private String decrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                // Subtract the shift value for decryption
                result.append((char) (((character - base - shift + 26) % 26) + base));
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }
}