package com.example.alarmmanagerdemo.receivers ;

import java.util.Date ;
import android.app.AlarmManager ;
import android.app.Notification ;
import android.app.NotificationManager ;
import android.app.PendingIntent ;
import android.content.BroadcastReceiver ;
import android.content.Context ;
import android.content.Intent ;
import android.os.Bundle ;
import android.text.TextUtils ;
import android.util.Log ;
import android.widget.Toast ;
import com.example.alarmmanagerdemo.R ;
import com.example.alarmmanagerdemo.daos.AlarmReminderDAO ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;
import com.example.alarmmanagerdemo.services.RegisAlarmReminderService ;
import com.example.alarmmanagerdemo.testcase.UnitTestCase ;
import com.example.alarmmanagerdemo.ui.AlarmReminderDetailActivity ;

/**
 * 
 * @ClassName: AlarmReceiver  
 * @Description: 闹铃时间到了会进入这个广播，这个时候可以做一些该做的业务。
 * @author HuHood
 * @date 2013-11-25 下午4:44:30  
 *
 */
public class AlarmReminderReceiver extends BroadcastReceiver {

	private Notification mNotification ;

	private NotificationManager mNotificationManager ;

	private AlarmReminderEntity mAlarmReminderEntity ;

	public static final int INTERVAL_DAY = 1000 * 60 * 60 * 24 ;// 24h

//	private String mString_NotificationTitle , mString_SubTitle , mString_Content ;
//
//	private int mLong_NotificationID ;
	private PendingIntent mPendingIntent ;

	Bundle mBundle ;

	int ID ;

	@ Override
	public void onReceive(Context context , Intent intent) {
		Log.i(UnitTestCase.TAG , "onReceive AlarmReminderReceiver") ;
		mBundle = intent.getExtras() ;
		if(mBundle == null) {
			return ;
		}
		ID = mBundle.getInt("ID") ;
		Log.i(UnitTestCase.TAG , "ID" + ID) ;
		mAlarmReminderEntity = new AlarmReminderDAO(context).queryByID(ID) ;
		if(mAlarmReminderEntity == null) {
			// TODO
			Toast.makeText(context , " Bundle But No Alarm" , Toast.LENGTH_LONG).show() ;
			return ;
		}
//		Intent mIntent = new Intent(context , RegisAlarmReminderService.class) ;
//		mIntent.putExtra("ID" , ID) ;
//		context.startService(mIntent) ;
//		String service = Context.NOTIFICATION_SERVICE ;
//		mNotificationManager = (NotificationManager) context.getSystemService(service) ;
//		mNotification = new Notification() ;
//		String tickerText = "测试Notifaction" ; // 通知提示
//		// 显示时间
//		long when = System.currentTimeMillis() ;
//		mNotification.icon = R.drawable.ic_launcher ;// 设置通知的图标
//		mNotification.tickerText = tickerText ; // 显示在状态栏中的文字
//		mNotification.when = when ; // 设置来通知时的时间
//		mNotification.sound = Uri.parse("android.resource://com.sun.alex/raw/dida") ; // 自定义声音
//		mNotification.flags = Notification.FLAG_NO_CLEAR ; // 点击清除按钮时就会清除消息通知,但是点击通知栏的通知时不会消失
//		mNotification.flags = Notification.FLAG_ONGOING_EVENT ; // 点击清除按钮不会清除消息通知,可以用来表示在正在运行
//		mNotification.flags |= Notification.FLAG_AUTO_CANCEL ; // 点击清除按钮或点击通知后会自动消失
//		mNotification.flags |= Notification.FLAG_INSISTENT ; // 一直进行，比如音乐一直播放，知道用户响应
//		mNotification.defaults = Notification.DEFAULT_SOUND ; // 调用系统自带声音
//		mNotification.defaults = Notification.DEFAULT_SOUND ;// 设置默认铃声
//		mNotification.defaults = Notification.DEFAULT_VIBRATE ;// 设置默认震动
//		mNotification.defaults = Notification.DEFAULT_ALL ; // 设置铃声震动
//		mNotification.defaults = Notification.DEFAULT_ALL ; // 把所有的属性设置成默认
//		// 单击通知后会跳转到NotificationResult类
//		intent = new Intent(context , MainActivity.class) ;
//		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
//		// 获取PendingIntent,点击时发送该Intent
//		pIntent = PendingIntent.getActivity(context , 0 , intent , 0) ;
//		// 设置通知的标题和内容
//		mNotification.setLatestEventInfo(context , "睡 你 麻痹 起来 嗨" , "该吃药了！" , pIntent) ;
//		// 发出通知
//		mNotificationManager.notify(1 , mNotification) ;
		alarm(context) ;
	}

	/**
	 * 	alarm:()
	 *  ──────────────────────────────────
	 * 	@param context    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-13	上午9:06:44	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void alarm(Context context) {
		Date date = new Date() ;
		Log.i("tag" , "date" + date) ;
		Log.i("tag" , mAlarmReminderEntity.toString()) ;
		if(mAlarmReminderEntity.getStratdate().after(date)
				|| mAlarmReminderEntity.getEnddate().before(date)) {
			cancelAlarmReminder(context) ;
			return ;
		}
		String service = Context.NOTIFICATION_SERVICE ;
		mNotificationManager = (NotificationManager) context.getSystemService(service) ;
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
		Intent intent = new Intent(context , AlarmReminderDetailActivity.class) ;
		intent.putExtra("ID" , ID) ;
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
		// 获取PendingIntent,点击时发送该Intent
		mPendingIntent = PendingIntent.getActivity(context , ID , intent , 0) ;
		// 设置通知的标题和内容
		mNotification.setLatestEventInfo(context , TextUtils.isEmpty(mAlarmReminderEntity
				.getTitle()) ? "该吃药了！" : mAlarmReminderEntity.getTitle() , TextUtils
				.isEmpty(mAlarmReminderEntity.getDescription()) ? "睡 你 麻痹 起来 嗨！"
				: mAlarmReminderEntity.getDescription() , mPendingIntent) ;
		// 发出通知
		mNotification.when = System.currentTimeMillis() ; // 设置来通知时的时间
		mNotificationManager.notify(mAlarmReminderEntity.getId() , mNotification) ;
	}

	private void cancelAlarmReminder(Context inContext) {
		Intent intent = new Intent(inContext , AlarmReminderReceiver.class) ;
		intent.putExtra("ID" , mAlarmReminderEntity.getId()) ;
		PendingIntent sender = PendingIntent.getBroadcast(inContext , mAlarmReminderEntity.getId() ,
				intent , 0) ;
		AlarmManager manager = (AlarmManager) inContext.getSystemService(Context.ALARM_SERVICE) ;
		manager.cancel(sender) ;
	}
}
