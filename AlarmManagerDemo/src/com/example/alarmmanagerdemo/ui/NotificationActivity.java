package com.example.alarmmanagerdemo.ui ;

import com.example.alarmmanagerdemo.R ;
import com.example.alarmmanagerdemo.R.drawable ;
import com.example.alarmmanagerdemo.R.id ;
import com.example.alarmmanagerdemo.R.layout ;
import com.example.alarmmanagerdemo.entities.NotificationEntity ;
import android.app.Activity ;
import android.app.Notification ;
import android.app.NotificationManager ;
import android.app.PendingIntent ;
import android.content.Intent ;
import android.net.Uri ;
import android.os.Bundle ;
import android.util.Log ;
import android.util.SparseArray ;
import android.view.View ;
import android.view.View.OnClickListener ;
import android.widget.Button ;

public class NotificationActivity extends Activity {

	private Notification mNotification ;

	private NotificationManager mNotificationManager ;

	private Intent mIntent ;

	private PendingIntent mPendingIntent ;

	SparseArray<String> mSparseArray = new SparseArray<String>() ;
	{
		mSparseArray.put(1 , "睡") ;
		mSparseArray.put(2 , "你") ;
		mSparseArray.put(3 , "麻") ;
		mSparseArray.put(4 , "痹") ;
		mSparseArray.put(5 , "起") ;
		mSparseArray.put(6 , "来") ;
		mSparseArray.put(7 , "嗨") ;
		Log.i("tag" , "SparseArray" + mSparseArray.toString()) ;
	}

	@ Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;
		mNotificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE) ;
		mNotification = new Notification() ;
		// 显示时间
		long when = System.currentTimeMillis() ;
		mNotification.icon = R.drawable.ic_launcher ;// 设置通知的图标
		mNotification.when = when ; // 设置来通知时的时间
		mNotification.sound = Uri.parse("android.resource://com.sun.alex/raw/dida") ; // 自定义声音
		mNotification.flags = Notification.FLAG_NO_CLEAR ; // 点击清除按钮时就会清除消息通知,但是点击通知栏的通知时不会消失
//		mNotification.flags = Notification.FLAG_ONGOING_EVENT ; // 点击清除按钮不会清除消息通知,可以用来表示在正在运行
		mNotification.flags |= Notification.FLAG_AUTO_CANCEL ; // 点击清除按钮或点击通知后会自动消失
//		mNotification.flags |= Notification.FLAG_INSISTENT ; // 一直进行，比如音乐一直播放，知道用户响应
		mNotification.defaults = Notification.DEFAULT_SOUND ; // 调用系统自带声音
		mNotification.defaults = Notification.DEFAULT_SOUND ;// 设置默认铃声
		mNotification.defaults = Notification.DEFAULT_VIBRATE ;// 设置默认震动
		mNotification.defaults = Notification.DEFAULT_ALL ; // 设置铃声震动
		mNotification.defaults = Notification.DEFAULT_ALL ; // 把所有的属性设置成默认
	}

	public void onClick(View inView) {
		int index = - 1 ;
		switch(inView.getId()) {
			case R.id.btn_sleep :
				index = mSparseArray.indexOfKey(1) ;
				break ;
			case R.id.btn_your :
				index = mSparseArray.indexOfKey(2) ;
				break ;
			case R.id.btn_mother :
				index = mSparseArray.indexOfKey(3) ;
				break ;
			case R.id.btn_b :
				index = mSparseArray.indexOfKey(4) ;
				break ;
			case R.id.btn_up :
				index = mSparseArray.indexOfKey(5) ;
				break ;
			case R.id.btn_come :
				index = mSparseArray.indexOfKey(6) ;
				break ;
			case R.id.btn_hi :
				index = mSparseArray.indexOfKey(7) ;
//				for(int i = 1 ; i < mSparseArray.size() ; i ++ ) {
//					try {
//						mNotificationManager.cancel(mSparseArray.indexOfKey(i)) ;
//					}
//					catch(Exception e) {
//						e.printStackTrace() ;
//					}
//				}
				break ;
			default :
				break ;
		}
		// 单击通知后会跳转到NotificationResult类
		mIntent = new Intent(NotificationActivity.this , MainActivity.class) ;
		mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
		mIntent.putExtra(
				"NotificationEntity" ,
				new NotificationEntity(index + 1 , mSparseArray.get(index + 1) ,
						mSparseArray.get(index + 1) + mSparseArray.get(index + 1)
								+ mSparseArray.get(index + 1) , mSparseArray.get(index + 1)
								+ "该吃药了！")) ;
		// 获取PendingIntent,点击时发送该Intent
		mPendingIntent = PendingIntent.getActivity(NotificationActivity.this , 0 , mIntent , 0) ;
		mNotification.tickerText = mSparseArray.get(index + 1) ; // 显示在状态栏中的文字
		// 设置通知的标题和内容
		mNotification.setLatestEventInfo(NotificationActivity.this , "睡 你 麻痹 起来 嗨！" , "该吃药了！" ,
				mPendingIntent) ;
		// 发出通知
//		mNotificationManager.notify(index , mNotification) ;
		startActivity(mIntent) ;
	}
}