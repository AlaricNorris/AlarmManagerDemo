/**
 * 	AlarmReminderHomeActivity.java
 * 	com.example.alarmmanagerdemo
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-18 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午2:36:46	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo ;

import java.util.ArrayList ;
import java.util.Arrays ;
import java.util.List ;
import de.greenrobot.event.EventBus ;
import android.app.Activity ;
import android.content.Intent ;
import android.os.Bundle ;
import android.os.Handler ;
import android.support.v4.widget.SwipeRefreshLayout ;
import android.view.View ;
import android.widget.ArrayAdapter ;
import android.widget.ListView ;

/**
 *	ClassName:	AlarmReminderHomeActivity
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	Norris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-18		下午2:36:46
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午2:36:46	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class AlarmReminderHomeActivity extends Activity implements
		SwipeRefreshLayout.OnRefreshListener {

	private static final int REFRESH_COMPLETE = 0X110 ;

	private SwipeRefreshLayout mSwipeLayout ;

	private ListView mListView ;

	private ArrayAdapter<String> mAdapter ;

	private List<String> mDatas = new ArrayList<String>(Arrays.asList("Java" , "Javascript" ,
			"C++" , "Ruby" , "Json" , "HTML")) ;

	private Handler mHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch(msg.what) {
				case REFRESH_COMPLETE :
					mDatas.addAll(Arrays.asList("Lucene" , "Canvas" , "Bitmap")) ;
					mAdapter.notifyDataSetChanged() ;
					mSwipeLayout.setRefreshing(false) ;
					isRefreshing = false ;
					break ;
			}
		} ;
	} ;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_alarm_reminder_home) ;
		EventBus.getDefault().register(this) ;
		mListView = (ListView) findViewById(R.id.id_listview) ;
		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh) ;
		mSwipeLayout.setOnRefreshListener(this) ;
		mSwipeLayout.setColorScheme(android.R.color.holo_green_dark ,
				android.R.color.holo_green_light , android.R.color.holo_orange_light ,
				android.R.color.holo_red_light) ;
		mAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , mDatas) ;
		mListView.setAdapter(mAdapter) ;
	}

	private boolean isRefreshing ;

	public void onRefresh() {
		if(isRefreshing) {
			return ;
		}
		isRefreshing = true ;
		EventBus.getDefault().post("") ;
		mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE , 2000) ;
	}

	public void addReminder(View inView) {
		if(ClickUtil.isFastDoubleClick()) {
			return ;
		}
		Intent mIntent = new Intent(getApplicationContext() , AlarmReminderEditActivity.class) ;
		mIntent.putExtra("EditFlag" , false) ;
		startActivity(mIntent) ;
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Activity#onDestroy()
	 */
	@ Override
	protected void onDestroy() {
		super.onDestroy() ;
		EventBus.getDefault().unregister(this) ;
	}
}