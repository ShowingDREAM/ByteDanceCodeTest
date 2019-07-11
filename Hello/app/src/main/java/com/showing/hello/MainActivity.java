package com.showing.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity1";
    private Button mBtn;
    private TextView mTv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn=findViewById(R.id.btn1);
        mTv2=findViewById(R.id.tv2);
        Log.i(TAG, "onCreate: Null");
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: Button");
                mTv2.setText("这是一条新信息");
            }
        });
    }
}
