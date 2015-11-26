package com.nfyg.hswx.activities;

import com.nfyg.hswx.BasePresentActivity;
import com.nfyg.hswx.views.MainVu;

/**
 * Created by shengming.yang on 2015/11/20.
 */
public class otherActivity extends BasePresentActivity<MainVu> {
    @Override
    protected Class<MainVu> getVClass() {
        return MainVu.class;
    }
}
