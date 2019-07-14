package com.showing.character2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.showing.character2.R.id.tv_tv1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv=findViewById(tv_tv1);
        //tv.setEllipsize();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: ");
                //Toast.makeText()
            }
        });
    }
}
