package com.nfyg.hswx.model.entity;

import java.util.ArrayList;
import java.util.List;

public class ChannelManage {
	public static ChannelManage channelManage;
	/**
	 * 默认的用户选择频道列表
	 * */
	public static List<ChannelItem> defaultUserChannels;
	/**
	 * 默认的其他频道列表
	 * */
	public static List<ChannelItem> defaultOtherChannels;
	/** 判断数据库中是否存在用户数据 */
	private boolean userExist = false;
	static {
		defaultUserChannels = new ArrayList<ChannelItem>();
		defaultOtherChannels = new ArrayList<ChannelItem>();
		defaultUserChannels.add(new ChannelItem(1, "推荐", 1, 1));
		defaultUserChannels.add(new ChannelItem(2, "热点", 2, 1));
		defaultUserChannels.add(new ChannelItem(3, "上海", 3, 1));
		defaultUserChannels.add(new ChannelItem(4, "视频", 4, 1));
		defaultUserChannels.add(new ChannelItem(5, "科技", 5, 1));
		defaultUserChannels.add(new ChannelItem(6, "体育", 6, 1));
		defaultUserChannels.add(new ChannelItem(7, "军事", 7, 1));
		defaultOtherChannels.add(new ChannelItem(8, "财经", 1, 0));
		defaultOtherChannels.add(new ChannelItem(9, "汽车", 2, 0));
		defaultOtherChannels.add(new ChannelItem(10, "房产", 3, 0));
		defaultOtherChannels.add(new ChannelItem(11, "社会", 4, 0));
		defaultOtherChannels.add(new ChannelItem(12, "情感", 5, 0));
		defaultOtherChannels.add(new ChannelItem(13, "女人", 6, 0));
		defaultOtherChannels.add(new ChannelItem(14, "旅游", 7, 0));
		defaultOtherChannels.add(new ChannelItem(15, "健康", 8, 0));
		defaultOtherChannels.add(new ChannelItem(16, "美女", 9, 0));
		defaultOtherChannels.add(new ChannelItem(17, "游戏", 10, 0));
		defaultOtherChannels.add(new ChannelItem(18, "数码", 11, 0));
		defaultUserChannels.add(new ChannelItem(19, "娱乐", 12, 0));
	}



}
