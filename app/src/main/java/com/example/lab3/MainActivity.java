package com.example.lab3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.BreakIterator;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);
        Button btnSend = findViewById(R.id.btnSend);
        LinearLayout layoutMessages = findViewById(R.id.layoutMessages);

        ImageView imageView = findViewById(R.id.imageView);
        ImageButton btnNextImage = findViewById(R.id.btnNextImage);


        btnSend.setOnClickListener(v -> {
            String message = editText.getText().toString().trim();
            if (!message.isEmpty()) {
                TextView newMessage = new TextView(MainActivity.this);
                newMessage.setText(message);
                newMessage.setPadding(8, 8, 8, 8);
                layoutMessages.addView(newMessage);
                editText.setText("");
            }
        });

        Fragment fragment = new LinearLayoutFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}
