package com.example.john.arewethereyet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.john.arewethereyet.R.id.textBox;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button but1 = (Button) findViewById(R.id.butt1);
        but1.setOnClickListener(new View.OnClickListener() {
            int buttonClickCount = 0;
            @Override
            public void onClick(View v) {
                but1.setText("button clicked " + buttonClickCount + " times");
                buttonClickCount++;
            }
        });
    }
}
