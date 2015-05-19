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
import java.util.Date ;
import android.app.Activity ;
import android.content.Intent ;
import android.os.Bundle ;
import android.os.Handler ;
import android.support.v4.widget.SwipeRefreshLayout ;
import android.util.Log ;
import android.view.View ;
import android.widget.ListView ;
import com.example.alarmmanagerdemo.daos.AlarmReminderDAO ;
import com.example.alarmmanagerdemo.thirdparty.baseadapter.BaseAdapterHelper ;
import com.example.alarmmanagerdemo.thirdparty.baseadapter.BaseQuickAdapter ;
import com.example.alarmmanagerdemo.thirdparty.baseadapter.QuickAdapter ;
import de.greenrobot.event.EventBus ;

/**
 *	ClassName:	AlarmReminderHomeActivity
 *	Function: 	主界面
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

	private ArrayList<AlarmReminderEntity> mArrayList ;

	private BaseQuickAdapter<AlarmReminderEntity , BaseAdapterHelper> mAdapter ;

	private Handler mHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch(msg.what) {
				case REFRESH_COMPLETE :
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
		mListView.setAdapter(mAdapter) ;
		loadOrRefreshData() ;
	}

	private void loadOrRefreshData() {
		new Thread() {

			public void run() {
				mSwipeLayout.setRefreshing(true) ;
				try {
					Thread.sleep(2000) ;
				}
				catch(InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace() ;
				}
				mArrayList = new AlarmReminderDAO(getApplicationContext()).queryAll() ;
				EventBus.getDefault().post(new EventEntity_AlarmReminders(mArrayList)) ;
			} ;
		}.start() ;
	}

	public class EventEntity_AlarmReminders {

		public ArrayList<AlarmReminderEntity> mArrayList ;

		/**
		 * 	Creates a new instance of EventEntity_AlarmReminders.
		 * 	@param mArrayList
		 */
		public EventEntity_AlarmReminders(ArrayList<AlarmReminderEntity> mArrayList) {
			super() ;
			this.mArrayList = mArrayList ;
		}
	}

	public void onEventMainThread(EventEntity_AlarmReminders inEvent) {
		if(inEvent == null) {
			return ;
		}
		if(mAdapter == null) {
			mAdapter = new QuickAdapter<AlarmReminderEntity>(getApplicationContext() ,
					R.layout.activity_dosomething , inEvent.mArrayList) {

				/**
				 * 	(non-Javadoc)
				 * 	@see com.example.alarmmanagerdemo.thirdparty.baseadapter.BaseQuickAdapter#convert(com.example.alarmmanagerdemo.thirdparty.baseadapter.BaseAdapterHelper, java.lang.Object)
				 */
				@ Override
				protected void convert(BaseAdapterHelper helper , AlarmReminderEntity item) {
					helper.setText(R.id.text_do , item.toString()) ;
				}
			} ;
			mListView.setAdapter(mAdapter) ;
		}
		else {
			mAdapter.clear() ;
			mAdapter.addAll(inEvent.mArrayList) ;
			mAdapter.notifyDataSetChanged() ;
		}
		mSwipeLayout.setRefreshing(false) ;
		isRefreshing = false ;
	}

	private boolean isRefreshing ;

	public void onRefresh() {
		if(isRefreshing) {
			return ;
		}
		isRefreshing = true ;
		loadOrRefreshData() ;
	}

	public void addReminder(View inView) {
		if(ClickUtil.isFastDoubleClick()) {
			return ;
		}
//		Intent mIntent = new Intent(getApplicationContext() , AlarmReminderEditActivity.class) ;
//		mIntent.putExtra("EditFlag" , false) ;
//		startActivity(mIntent) ;
		new Thread() {

			public void run() {
				AlarmReminderEntity mAlarmReminderEntity = new AlarmReminderEntity() ;
				mAlarmReminderEntity.setTitle("add") ;
				mAlarmReminderEntity.setDescription("addTest ") ;
				mAlarmReminderEntity.setTimesperday(1) ;
				mAlarmReminderEntity.setStratdate(new Date()) ;
				mAlarmReminderEntity.setEnddate(new Date(2015 - 1900 , 6 - 1 , 11)) ;
				boolean result = new AlarmReminderDAO(getApplicationContext())
						.createOrUpdate(mAlarmReminderEntity) ;
				if(result) {
					loadOrRefreshData() ;
				}
				else
					Log.i("TestTag" , "fail") ;
			} ;
		}.start() ;
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