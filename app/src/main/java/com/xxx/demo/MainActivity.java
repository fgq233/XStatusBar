package com.xxx.demo;

import android.content.Intent;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        findViewById(R.id.bt_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ColorActivity.class));
            }
        });

        findViewById(R.id.bt_transparent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TransparentActivity.class));
            }
        });

        findViewById(R.id.bt_translucent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TranslucentActivity.class));
            }
        });

        findViewById(R.id.bt_font).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StatusFontActivity.class));
            }
        });
    }


}
