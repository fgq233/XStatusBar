package com.xxx.demo;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.fgq.xstatusbar.XStatusBar;

import java.util.Random;

public class ColorActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mTvStatusBarAlpha;
    private SeekBar mSeekBar;
    private Button mBtChange;

    private int mColor;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_color;
    }

    @Override
    protected void init() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mBtChange = (Button) findViewById(R.id.bt_change);
        mTvStatusBarAlpha = (TextView) findViewById(R.id.tv_status_bar_alpha);
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);

        mColor = getResources().getColor(R.color.colorPrimary);

        initToolBar();
        initSeekBar();
        initBt();
    }


    private void initBt() {
        mBtChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                mColor = 0xff000000 | random.nextInt(0xffffff);
                mToolbar.setBackgroundColor(mColor);
                XStatusBar.setStatusBarColor(ColorActivity.this, mColor, mSeekBar.getProgress());
            }
        });
    }

    private void initSeekBar() {
        mSeekBar.setMax(255);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                XStatusBar.setStatusBarColor(ColorActivity.this, mColor, progress);
                mTvStatusBarAlpha.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
