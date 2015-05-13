/**
 * 	CallupAlarmManagerService.java
 * 	com.example.alarmmanagerdemo
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-12 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-12	下午3:39:38	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo ;

import android.app.Notification ;
import android.app.NotificationManager ;
import android.app.PendingIntent ;
import android.app.Service ;
import android.content.Context ;
import android.content.Intent ;
import android.net.Uri ;
import android.os.Bundle ;
import android.os.IBinder ;
import android.text.TextUtils ;
import android.widget.Toast ;

/**
 *	ClassName:	CallupAlarmManagerService
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	Norris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-12		下午3:39:38
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-12	下午3:39:38	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class CallupNotificationService extends Service {

	private NotificationEntity mNotificationEntity ;

	public static final int INTERVAL_DAY = 1000 * 60 * 60 * 24 ;// 24h

	public static final int REQUEST_CODE = 0x8888 ;

	private String mString_NotificationTitle , mString_SubTitle , mString_Content ;

	private int mLong_NotificationID ;

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Service#onStart(android.content.Intent, int)
	 */
	@ Override
	public void onStart(Intent intent , int startId) {
		mNotificationEntity = (NotificationEntity) intent
				.getSerializableExtra("NotificationEntity") ;
		if(mNotificationEntity != null) {
			mString_NotificationTitle = mNotificationEntity.NotificationTitle ;
			mString_SubTitle = mNotificationEntity.SubTitle ;
			mString_Content = mNotificationEntity.Content ;
			mLong_NotificationID = mNotificationEntity.ID ;
		}
		String service = Context.NOTIFICATION_SERVICE ;
		mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(
				service) ;
		mNotification = new Notification() ;
		// 显示时间
		mNotification.icon = R.drawable.ic_launcher ;// 设置通知的图标
		mNotification.tickerText = mString_NotificationTitle ; // 显示在状态栏中的文字
		mNotification.sound = Uri.parse("android.resource://com.sun.alex/raw/dida") ; // 自定义声音
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
		intent = new Intent(getApplicationContext() , DoSomething.class) ;
		intent.putExtra("NotificationEntity" , mNotificationEntity) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
		// 获取PendingIntent,点击时发送该Intent
		mPendingIntent = PendingIntent.getActivity(getApplicationContext() , 0 , intent , 0) ;
		// 设置通知的标题和内容
		mNotification.setLatestEventInfo(getApplicationContext() ,
				TextUtils.isEmpty(mString_SubTitle) ? "睡 你 麻痹 起来 嗨！" : mString_SubTitle ,
				TextUtils.isEmpty(mString_Content) ? "该吃药了！" : mString_Content , mPendingIntent) ;
		// 发出通知
		mNotification.when = System.currentTimeMillis() ; // 设置来通知时的时间
		mNotificationManager.notify(mLong_NotificationID , mNotification) ;
	}

	private Notification mNotification ;

	private NotificationManager mNotificationManager ;

	private PendingIntent mPendingIntent ;

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Service#onBind(android.content.Intent)
	 */
	@ Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null ;
	}
}
