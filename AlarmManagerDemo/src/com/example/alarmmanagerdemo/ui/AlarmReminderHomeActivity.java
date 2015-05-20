/**
 * 	AlarmReminderHomeActivity.java
 * 	com.example.alarmmanagerdemo
 * 	Function： 	主界面 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-18 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午2:36:46	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo.ui ;

import java.util.ArrayList ;
import android.app.Activity ;
import android.content.Intent ;
import android.os.Bundle ;
import android.support.v4.widget.SwipeRefreshLayout ;
import android.util.Log ;
import android.view.View ;
import android.widget.AdapterView ;
import android.widget.Toast ;
import android.widget.AdapterView.OnItemClickListener ;
import android.widget.ListView ;
import com.example.alarmmanagerdemo.ClickUtil ;
import com.example.alarmmanagerdemo.R ;
import com.example.alarmmanagerdemo.daos.AlarmReminderDAO ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;
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
		SwipeRefreshLayout.OnRefreshListener , OnItemClickListener {

	/**
	 * 	下拉刷新组件
	 * 	SwipeRefreshLayout			:		mSwipeLayout	
	 * 	@since Ver 1.0
	 */
	private SwipeRefreshLayout mSwipeLayout ;

	/**
	 * 	提醒列表
	 * 	ListView			:		mListView	
	 * 	@since Ver 1.0
	 */
	private ListView mListView ;

	/**
	 * 	提醒数据列表
	 * 	ArrayList<AlarmReminderEntity>			:		mArrayList	
	 * 	@since Ver 1.0
	 */
	private ArrayList<AlarmReminderEntity> mArrayList ;

	/**
	 * 	万能快捷Adapter
	 * 	BaseQuickAdapter<AlarmReminderEntity,BaseAdapterHelper>			:		mAdapter	
	 * 	@since Ver 1.0
	 */
	private BaseQuickAdapter<AlarmReminderEntity , BaseAdapterHelper> mAdapter ;

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
		mListView.setOnItemClickListener(this) ;
		loadOrRefreshData() ;
	}

	private boolean isButtonEnabled ;

	/**
	 * 	disableButtons:()
	 *  ──────────────────────────────────	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	AlaricNorris		 2015-5-19下午11:06:12
	 *	Modifications:	TODO
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void disableButtons() {
		isButtonEnabled = false ;
	}

	/**
	 * 	enableButtons:()
	 *  ──────────────────────────────────	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	AlaricNorris		 2015-5-19下午11:05:00
	 *	Modifications:	TODO
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void enableButtons() {
		isButtonEnabled = true ;
	}

	private void loadOrRefreshData() {
		new Thread() {

			public void run() {
				disableButtons() ;
				mSwipeLayout.setRefreshing(true) ;
				try {
					Thread.sleep(2000) ;
				}
				catch(InterruptedException e) {
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
			this.mArrayList = mArrayList ;
		}
	}

	/**
	 * 	onEventMainThread:(EventBus事件回调函数)
	 * 	当加载或者刷新完毕后自动推送至此做数据填充工作
	 *  ──────────────────────────────────
	 * 	@param 		inEvent    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-19	下午2:00:05	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
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
			if(mListView != null) {
				mListView.setAdapter(mAdapter) ;
			}
		}
		else {
			mAdapter.clear() ;
			mAdapter.addAll(inEvent.mArrayList) ;
			mAdapter.notifyDataSetChanged() ;
		}
		if(mSwipeLayout != null) {
			mSwipeLayout.setRefreshing(false) ;
		}
		isRefreshing = false ;
		enableButtons() ;
	}

	/**
	 * 	是否在刷新的标记
	 * 	boolean			:		isRefreshing	
	 * 	@since Ver 1.0
	 */
	private boolean isRefreshing ;

	/**
	 * 	(non-Javadoc)
	 * 	@see android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener#onRefresh()
	 */
	@ Override
	public void onRefresh() {
		if(isRefreshing) {
			return ;
		}
		isRefreshing = true ;
		loadOrRefreshData() ;
	}

	/**
	 * 	addReminder:(Button click事件回调)
	 *  ──────────────────────────────────
	 * 	@param 		inView    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-20	上午8:49:27	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public void addReminder(View inView) {
		if(ClickUtil.isFastDoubleClick()) {
			return ;
		}
		if( ! isButtonEnabled) {
			Toast.makeText(getApplicationContext() , "按钮暂不可点击" , 0).show() ;
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
		EventBus.getDefault().unregister(this) ;
		super.onDestroy() ;
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@ Override
	public void onItemClick(AdapterView< ? > parent , View view , int position , long id) {
		if(ClickUtil.isFastDoubleClick()) {
			return ;
		}
		if( ! isButtonEnabled) {
			Toast.makeText(getApplicationContext() , "暂不可点击请稍候。。。" , 0).show() ;
		}
		Intent mIntent = new Intent(getApplicationContext() , AlarmReminderEditActivity.class) ;
		Log.i("tag" , "itemClick" + position + mAdapter.getItem(position)) ;
		mIntent.putExtra(BUNDLE_KEY_ALARMREMINDER , mAdapter.getItem(position)) ;
		mIntent.putExtra(BUNDLE_KEY_EDITFLAG , true) ;
		startActivity(mIntent) ;
	}

	/**
	 * 	Intent Bundle 的Key   
	 * 	String			:		BUNDLE_KEY_ALARMREMINDER	
	 * 	@since Ver 1.0
	 */
	public static final String BUNDLE_KEY_ALARMREMINDER = "AlarmReminder" ;

	/**
	 * 	Intent Bundle 的Key
	 * 	String			:		BUNDLE_KEY_EDITFLAG	
	 * 	@since Ver 1.0
	 */
	public static final String BUNDLE_KEY_EDITFLAG = "EditFlag" ;

	public static class Event_Refresh {
	}

	/**
	 * 	onEvent:(EventBus事件回调函数)
	 * 	收到推送后刷新数据
	 *  ──────────────────────────────────
	 * 	@param 		inEvent	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-20上午10:44:38
	 *	Modifications:	TODO
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public void onEvent(Event_Refresh inEvent) {
		if(isRefreshing) {
			return ;
		}
		loadOrRefreshData() ;
	}
}