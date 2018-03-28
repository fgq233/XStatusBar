package com.xxx.demo;

import android.widget.SeekBar;

import com.fgq.xstatusbar.XStatusBar;

public class TranslucentActivity extends BaseActivity {

    private SeekBar mSeekBar;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_translucent;
    }

    @Override
    protected void init() {
        XStatusBar.translucentStatusBar(TranslucentActivity.this);//默认透明度

        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mSeekBar.setMax(255);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                XStatusBar.translucentStatusBar(TranslucentActivity.this, progress);//自设透明度
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


}
