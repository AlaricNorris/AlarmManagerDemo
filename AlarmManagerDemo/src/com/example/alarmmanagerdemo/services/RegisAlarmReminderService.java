/**
 * 	RegisAlarmReminderService.java
 * 	com.example.alarmmanagerdemo.services
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-20 		20144L151
 *	Copyright (c) 2015, TNT All Rights Reserved.
 */
package com.example.alarmmanagerdemo.services ;

import java.util.Date ;
import android.app.Notification ;
import android.app.NotificationManager ;
import android.app.PendingIntent ;
import android.app.Service ;
import android.content.Context ;
import android.content.Intent ;
import android.os.IBinder ;
import android.text.TextUtils ;
import android.util.Log ;
import android.widget.Toast ;
import com.example.alarmmanagerdemo.R ;
import com.example.alarmmanagerdemo.daos.AlarmReminderDAO ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;
import com.example.alarmmanagerdemo.testcase.UnitTestCase ;
import com.example.alarmmanagerdemo.ui.AlarmReminderDetailActivity ;

/**
 *	ClassName:	RegisAlarmReminderService
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	20144L151		
 *	@contact  	Norris.sly@gmail.com
 *	@version  	Ver 1.0
 *	@since   	I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-20		下午2:54:11
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 * 	Modified By 	20144L151		 2015-5-20下午2:54:11
 *	Modifications:	TODO
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class RegisAlarmReminderService extends Service {

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Service#onBind(android.content.Intent)
	 */
	@ Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null ;
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@ Override
	public int onStartCommand(Intent intent , int flags , int startId) {
		Log.i(UnitTestCase.TAG , "onStartCommand") ;
		if(intent == null) {
			return Service.START_NOT_STICKY ;
		}
		ID = intent.getIntExtra("ID" , - 1) ;
		if(ID == - 1) {
			return Service.START_NOT_STICKY ;
		}
		Log.i(UnitTestCase.TAG , "ID" + ID) ;
		mAlarmReminderEntity = new AlarmReminderDAO(getApplicationContext()).queryByID(ID) ;
		if(mAlarmReminderEntity == null) {
			// TODO
			Toast.makeText(getApplicationContext() , " Bundle But No Alarm" , Toast.LENGTH_LONG)
					.show() ;
			return Service.START_NOT_STICKY ;
		}
		if(mAlarmReminderEntity.getEnddate().before(new Date())) {
		}
		registerAlarm() ;
		return Service.START_REDELIVER_INTENT ;
	}

	private Notification mNotification ;

	private NotificationManager mNotificationManager ;

	private AlarmReminderEntity mAlarmReminderEntity ;

	private PendingIntent mPendingIntent ;

	int ID ;

	/**
	 * 	registerAlarm:()
	 *  ──────────────────────────────────	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-20下午3:06:37
	 *	Modifications:	TODO
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void registerAlarm() {
		String service = Context.NOTIFICATION_SERVICE ;
		mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(
				service) ;
		mNotification = new Notification() ;
		// 显示时间
		mNotification.icon = R.drawable.ic_launcher ;// 设置通知的图标
		mNotification.tickerText = mAlarmReminderEntity.getTitle() ; // 显示在状态栏中的文字
//		mNotification.sound = Uri.parse("android.resource://com.sun.alex/raw/dida") ; // 自定义声音
		mNotification.flags = Notification.FLAG_NO_CLEAR ; // 点击清除按钮时就会清除消息通知,但是点击通知栏的通知时不会消失
		mNotification.flags = Notification.FLAG_ONGOING_EVENT ; // 点击清除按钮不会清除消息通知,可以用来表示在正在运行
		mNotification.flags |= Notification.FLAG_AUTO_CANCEL ; // 点击清除按钮或点击通知后会自动消失
		mNotification.flags |= Notification.FLAG_INSISTENT ; // 一直进行，比如音乐一直播放，知道用户响应
		mNotification.defaults = Notification.DEFAULT_SOUND ; // 调用系统自带声音
		mNotification.defaults = Notification.DEFAULT_SOUND ;// 设置默认铃声
		mNotification.defaults = Notification.DEFAULT_VIBRATE ;// 设置默认震动
		mNotification.defaults = Notification.DEFAULT_ALL ; // 设置铃声震动
		mNotification.defaults = Notification.DEFAULT_ALL ; // 把所有的属性设置成默认
		// 单击通知后会跳转到NotificationResult类
		Intent intent = new Intent(getApplicationContext() , AlarmReminderDetailActivity.class) ;
		intent.putExtra("ID" , ID) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
		// 获取PendingIntent,点击时发送该Intent
		mPendingIntent = PendingIntent.getActivity(getApplicationContext() , ID , intent , 0) ;
		// 设置通知的标题和内容
		mNotification.setLatestEventInfo(getApplicationContext() , TextUtils
				.isEmpty(mAlarmReminderEntity.getDescription()) ? "睡 你 麻痹 起来 嗨！"
				: mAlarmReminderEntity.getDescription() , TextUtils.isEmpty(mAlarmReminderEntity
				.getTitle()) ? "该吃药了！" : mAlarmReminderEntity.getTitle() , mPendingIntent) ;
		// 发出通知
		mNotification.when = System.currentTimeMillis() ; // 设置来通知时的时间
		mNotificationManager.notify(mAlarmReminderEntity.getId() , mNotification) ;
	}
}
