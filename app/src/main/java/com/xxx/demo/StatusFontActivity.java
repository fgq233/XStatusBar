package com.xxx.demo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fgq.xstatusbar.XStatusBar;

public class StatusFontActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_font);


        findViewById(R.id.bt_dark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatusFontColor(true);
            }
        });

        findViewById(R.id.bt_light).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatusFontColor(false);
            }
        });
    }


    public void setStatusFontColor(boolean isDarkMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            XStatusBar.setStatusFontColor(this, isDarkMode);
        }
    }


}
