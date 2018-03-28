package com.xxx.demo;

import com.fgq.xstatusbar.XStatusBar;

public class TransparentActivity extends BaseActivity {

    @Override
    protected int initLayoutId() {
        return R.layout.activity_transparent;
    }

    @Override
    protected void init() {
        XStatusBar.setStatusBarTransparent(this);
    }


}
