package com.nfyg.hswx.views.fragment;

import com.nfyg.hswx.biz.bus.VCMainBus;
import com.nfyg.hswx.events.MainEvent;
import com.nfyg.hswx.views.VuCallBack;
import com.nfyg.hswx.views.adapter.NewListAdapter;
import com.nfyg.hswx.views.subView.NewMainVu;

/**
 * Created by shengming.yang on 2015/11/16.
 */
public class NewsListFragment extends BasePresenterFragment<NewMainVu> {

    NewListAdapter adapter = new  NewListAdapter(VCMainBus.getInstanceBus().news);
    VuCallBack<Integer> selectCallback = new VuCallBack<Integer>() {
        @Override
        public void execute(Integer result) {
            bus.postSticky(new MainEvent(adapter.getItemId(result)+""));
        }
    };

    @Override
    protected void onBindVu() {
        vu.setSelectCallback(selectCallback);
    }

    @Override
    protected Class<NewMainVu> getVuClass() {
        return NewMainVu.class;
    }

    public static NewsListFragment newInstance(){
        return new NewsListFragment();
    }


}
