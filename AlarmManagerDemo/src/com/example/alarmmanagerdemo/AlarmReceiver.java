package com.example.alarmmanagerdemo ;

import com.example.alarmmanagerdemo.entities.NotificationEntity ;
import android.app.Notification ;
import android.app.NotificationManager ;
import android.app.PendingIntent ;
import android.content.BroadcastReceiver ;
import android.content.Context ;
import android.content.Intent ;
import android.net.Uri ;
import android.os.IBinder ;
import android.text.TextUtils ;
import android.util.Log ;
import android.util.SparseArray ;
import android.widget.Toast ;

/**
 * 
 * @ClassName: AlarmReceiver  
 * @Description: 闹铃时间到了会进入这个广播，这个时候可以做一些该做的业务。
 * @author HuHood
 * @date 2013-11-25 下午4:44:30  
 *
 */
public class AlarmReceiver extends BroadcastReceiver {

	public static final String ACTION_A = "com.health.billion.broadcast.Alarm.A" ;

	public static final String ACTION_B = "com.health.billion.broadcast.Alarm.B" ;

	public static final String ACTION_C = "com.health.billion.broadcast.Alarm.C" ;

	public static final String ACTION_D = "com.health.billion.broadcast.Alarm.D" ;

	public static final String ACTION_E = "com.health.billion.broadcast.Alarm.E" ;

	public static final String ACTION_F = "com.health.billion.broadcast.Alarm.F" ;

	public static final String ACTION_G = "com.health.billion.broadcast.Alarm.G" ;

	public static final SparseArray<String> ACTION_ARRAY = new SparseArray<String>() ;
	static {
		ACTION_ARRAY.put(1 , ACTION_A) ;
		ACTION_ARRAY.put(2 , ACTION_B) ;
		ACTION_ARRAY.put(3 , ACTION_C) ;
		ACTION_ARRAY.put(4 , ACTION_D) ;
		ACTION_ARRAY.put(5 , ACTION_E) ;
		ACTION_ARRAY.put(6 , ACTION_F) ;
		ACTION_ARRAY.put(7 , ACTION_G) ;
	}

	private Notification mNotification ;

	private NotificationManager mNotificationManager ;

	private NotificationEntity mNotificationEntity ;

	public static final int INTERVAL_DAY = 1000 * 60 * 60 * 24 ;// 24h

	public static final int REQUEST_CODE = 0x8888 ;

//	private String mString_NotificationTitle , mString_SubTitle , mString_Content ;
//
//	private int mLong_NotificationID ;
	private PendingIntent mPendingIntent ;

	@ Override
	public void onReceive(Context context , Intent intent) {
		String mActionString = intent.getAction() ;
		if(TextUtils.isEmpty(mActionString) || mActionString.length() == 0) {
			Log.i("tag" , "error") ;
			return ;
		}
		Intent mIntent = new Intent(context , CallupNotificationService.class) ;
		switch(ACTION_ARRAY.indexOfValue(mActionString.toString())) {
			case 0 :
				mIntent.putExtra("NotificationEntity" , new NotificationEntity(1 , "ID:1===睡" ,
						"睡" , "该吃药了！")) ;
				mNotificationEntity = new NotificationEntity(1 , "ID:1===睡" , "睡" , "该吃药了！") ;
				break ;
			case 1 :
				mIntent.putExtra("NotificationEntity" , new NotificationEntity(2 , "ID:2===你" ,
						"你" , "该吃药了！")) ;
				mNotificationEntity = new NotificationEntity(2 , "ID:2===你" , "你" , "该吃药了！") ;
				break ;
			case 2 :
				mIntent.putExtra("NotificationEntity" , new NotificationEntity(3 , "ID:3===麻" ,
						"麻" , "该吃药了！")) ;
				mNotificationEntity = new NotificationEntity(3 , "ID:3===麻" , "麻" , "该吃药了！") ;
				break ;
			case 3 :
				mIntent.putExtra("NotificationEntity" , new NotificationEntity(4 , "ID:4===痹" ,
						"痹" , "该吃药了！")) ;
				mNotificationEntity = new NotificationEntity(4 , "ID:4===痹" , "痹" , "该吃药了！") ;
				break ;
			case 4 :
				mIntent.putExtra("NotificationEntity" , new NotificationEntity(5 , "ID:5===起" ,
						"起" , "该吃药了！")) ;
				mNotificationEntity = new NotificationEntity(5 , "ID:5===起" , "起" , "该吃药了！") ;
				break ;
			case 5 :
				mIntent.putExtra("NotificationEntity" , new NotificationEntity(6 , "ID:6===来" ,
						"来" , "该吃药了！")) ;
				mNotificationEntity = new NotificationEntity(6 , "ID:6===来" , "来" , "该吃药了！") ;
				break ;
			case 6 :
				mIntent.putExtra("NotificationEntity" , new NotificationEntity(7 , "ID:7===嗨" ,
						"嗨" , "该吃药了！")) ;
				mNotificationEntity = new NotificationEntity(7 , "ID:7===嗨" , "嗨" , "该吃药了！") ;
				break ;
			default :
				mNotificationEntity = new NotificationEntity(111 , "睡 你 麻痹 起来 嗨" , "睡 你 麻痹 起来 嗨" ,
						"该吃药了！") ;
				break ;
		}
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
		Toast.makeText(context , mActionString , Toast.LENGTH_LONG).show() ;
		Log.i("tag" , "Receive" + mActionString) ;
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
		String service = Context.NOTIFICATION_SERVICE ;
		mNotificationManager = (NotificationManager) context.getSystemService(service) ;
		mNotification = new Notification() ;
		// 显示时间
		mNotification.icon = R.drawable.ic_launcher ;// 设置通知的图标
		mNotification.tickerText = mNotificationEntity.NotificationTitle ; // 显示在状态栏中的文字
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
		Intent intent = new Intent(context , DoSomething.class) ;
		intent.putExtra("NotificationEntity" , mNotificationEntity) ;
		// 获取PendingIntent,点击时发送该Intent
		mPendingIntent = PendingIntent.getActivity(context , 0 , intent , 0) ;
		// 设置通知的标题和内容
		mNotification.setLatestEventInfo(context ,
				TextUtils.isEmpty(mNotificationEntity.SubTitle) ? "睡 你 麻痹 起来 嗨！"
						: mNotificationEntity.SubTitle , TextUtils
						.isEmpty(mNotificationEntity.Content) ? "该吃药了！"
						: mNotificationEntity.Content , mPendingIntent) ;
		// 发出通知
		mNotification.when = System.currentTimeMillis() ; // 设置来通知时的时间
		mNotificationManager.notify(mNotificationEntity.ID , mNotification) ;
	}
}
