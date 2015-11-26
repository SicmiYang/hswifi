package com.nfyg.hswx.views.subView;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nfyg.hswx.R;

/**
 * 自定义SlidingMenu 测拉菜单类
 * */
public class DrawerView implements OnClickListener{
	private final Activity activity;
	SlidingMenu localSlidingMenu;
	private TextView night_mode_text;
	private RelativeLayout setting_btn;
	public DrawerView(Activity activity) {
		this.activity = activity;
	}

	public SlidingMenu initSlidingMenu() {
		localSlidingMenu = new SlidingMenu(activity);
		localSlidingMenu.setMode(SlidingMenu.LEFT);//设置左右滑菜单
		localSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置要使菜单滑动，触碰屏幕的范围
//		localSlidingMenu.setTouchModeBehind(SlidingMenu.SLIDING_CONTENT);//设置了这个会获取不到菜单里面的焦点，所以先注释掉
		localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影图片的宽度
		localSlidingMenu.setShadowDrawable(R.drawable.shadow);//设置阴影图片
		localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu划出时主页面显示的剩余宽度
		localSlidingMenu.setFadeDegree(0.35F);//SlidingMenu滑动时的渐变程度
		localSlidingMenu.attachToActivity(activity, SlidingMenu.LEFT);//使SlidingMenu附加在Activity右边
//		localSlidingMenu.setBehindWidthRes(R.dimen.left_drawer_avatar_size);//设置SlidingMenu菜单的宽度
		localSlidingMenu.setMenu(R.layout.left_drawer_fragment);//设置menu的布局文件
//		localSlidingMenu.toggle();//动态判断自动关闭或开启SlidingMenu
//		localSlidingMenu.setSecondaryMenu(R.layout.profile_drawer_right);
//		localSlidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);
		localSlidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
					public void onOpened() {
						
					}
				});
		localSlidingMenu.setOnClosedListener(new SlidingMenu.OnClosedListener() {
			
			@Override
			public void onClosed() {
				// TODO Auto-generated method stub
				
			}
		});
		initView();
		return localSlidingMenu;
	}

	private void initView() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setting_btn:
//			activity.startActivity(new Intent(activity,SettingsActivity.class));
//			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			break;

		default:
			break;
		}
	}
}
