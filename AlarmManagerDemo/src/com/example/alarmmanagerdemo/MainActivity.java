package com.example.alarmmanagerdemo ;

import java.sql.Time ;
import java.util.Calendar ;
import java.util.TimeZone ;
import android.app.Activity ;
import android.app.AlarmManager ;
import android.app.PendingIntent ;
import android.content.Intent ;
import android.os.Bundle ;
import android.os.SystemClock ;
import android.util.Log ;
import android.view.View ;
import android.widget.Button ;
import android.widget.Chronometer ;
import android.widget.Chronometer.OnChronometerTickListener ;
import android.widget.TimePicker ;
import android.widget.Toast ;

/**
 * 
 * @ClassName: MainActivity  
 * @Description: 主界面
 * @author HuHood
 * @date 2013-11-25 下午4:01:19  
 *
 */
public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName() ;

	private TimePicker mTimePicker ;

	private Button mButton1 ;

	private Button mButton2 ;

	private Button mButtonCancel ;

	private int mHour = - 1 ;

	private int mMinute = - 1 ;

	public static final long DAY = 1000L * 60 * 60 * 24 ;

	public static final long MINUTES = 1000L * 60 * 10 ;

	@ Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main_backup) ;
		try {
			setTitle( ((NotificationEntity) getIntent().getSerializableExtra("NotificationEntity")).SubTitle) ;
		}
		catch(Exception e) {
		}
		// 获取当前时间
		Calendar calendar = Calendar.getInstance() ;
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")) ;
		calendar.add(
				Calendar.SECOND ,
				60 * ((NotificationEntity) getIntent().getSerializableExtra("NotificationEntity")).ID) ;
		if(mHour == - 1 && mMinute == - 1) {
			mHour = calendar.get(Calendar.HOUR_OF_DAY) ;
			mMinute = calendar.get(Calendar.MINUTE) ;
		}
		mTimePicker = (TimePicker) findViewById(R.id.timePicker) ;
		mTimePicker.setCurrentHour(mHour) ;
		mTimePicker.setCurrentMinute(mMinute) ;
		mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

			@ Override
			public void onTimeChanged(TimePicker view , int hourOfDay , int minute) {
				mHour = hourOfDay ;
				mMinute = minute ;
			}
		}) ;
		mButton1 = (Button) findViewById(R.id.normal_button) ;
		mButton1.setOnClickListener(new View.OnClickListener() {

			@ Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this , AlarmReceiver.class) ;
				intent.putExtra("NotificationEntity" ,
						getIntent().getSerializableExtra("NotificationEntity")) ;
				PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this , 0 , intent ,
						0) ;
				// 过10s 执行这个闹铃
				Calendar calendar = Calendar.getInstance() ;
				calendar.setTimeInMillis(System.currentTimeMillis()) ;
				calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")) ;
				calendar.add(Calendar.SECOND , 10) ;
				// 进行闹铃注册
				AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE) ;
				manager.set(AlarmManager.RTC_WAKEUP , calendar.getTimeInMillis() , sender) ;
				Toast.makeText(MainActivity.this , "设置单次提醒成功!" , Toast.LENGTH_LONG).show() ;
			}
		}) ;
		mButton2 = (Button) findViewById(R.id.repeating_button) ;
		mButton2.setOnClickListener(new View.OnClickListener() {

			@ Override
			public void onClick(View v) {
				Intent intent = new Intent() ;
				switch( ((NotificationEntity) getIntent()
						.getSerializableExtra("NotificationEntity")).ID) {
					case 1 :
						intent.setAction(AlarmReceiver.ACTION_A) ;
						break ;
					case 2 :
						intent.setAction(AlarmReceiver.ACTION_B) ;
						break ;
					case 3 :
						intent.setAction(AlarmReceiver.ACTION_C) ;
						break ;
					case 4 :
						intent.setAction(AlarmReceiver.ACTION_D) ;
						break ;
					case 5 :
						intent.setAction(AlarmReceiver.ACTION_E) ;
						break ;
					case 6 :
						intent.setAction(AlarmReceiver.ACTION_F) ;
						break ;
					case 7 :
						intent.setAction(AlarmReceiver.ACTION_G) ;
						break ;
					default :
						break ;
				}
				PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this , 0 , intent ,
						0) ;
				long firstTime = SystemClock.elapsedRealtime() ; // 开机之后到现在的运行时间(包括睡眠时间)
				long systemTime = System.currentTimeMillis() ;
				Calendar calendar = Calendar.getInstance() ;
				calendar.setTimeInMillis(System.currentTimeMillis()) ;
				calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")) ; // 这里时区需要设置一下，不然会有8个小时的时间差
				calendar.set(Calendar.MINUTE , mMinute) ;
				calendar.set(Calendar.HOUR_OF_DAY , mHour) ;
				calendar.set(Calendar.SECOND , 0) ;
				calendar.set(Calendar.MILLISECOND , 0) ;
				// 选择的每天定时时间
				long selectTime = calendar.getTimeInMillis() ;
				// 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
				if(systemTime > selectTime) {
					Toast.makeText(MainActivity.this , "设置的时间小于当前时间" , Toast.LENGTH_SHORT).show() ;
					calendar.add(Calendar.DAY_OF_MONTH , 1) ;
					selectTime = calendar.getTimeInMillis() ;
				}
				// 计算现在时间到设定时间的时间差
				Log.i("tag" , "selectTime" + new Time(selectTime).toString() + "systemTime"
						+ new Time(systemTime).toString()) ;
				long time = selectTime - systemTime ;
				firstTime += time ;
				Log.i("tag" , "time" + time + "firstTime" + new Time(firstTime)) ;
				Log.i("tag" , "calendar" + new Time(calendar.getTimeInMillis())) ;
				// 进行闹铃注册
				AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE) ;
				manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP , firstTime , MINUTES ,
						sender) ;
				Log.i(TAG , "time ==== " + time + ", selectTime ===== " + selectTime
						+ ", systemTime ==== " + systemTime + ", firstTime === " + firstTime) ;
				Toast.makeText(MainActivity.this , "设置重复提醒成功! " , Toast.LENGTH_LONG).show() ;
			}
		}) ;
		mButtonCancel = (Button) findViewById(R.id.cancel_button) ;
		mButtonCancel.setOnClickListener(new View.OnClickListener() {

			@ Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this , AlarmReceiver.class) ;
				switch( ((NotificationEntity) getIntent()
						.getSerializableExtra("NotificationEntity")).ID) {
					case 1 :
						intent.setAction(AlarmReceiver.ACTION_A) ;
						break ;
					case 2 :
						intent.setAction(AlarmReceiver.ACTION_B) ;
						break ;
					case 3 :
						intent.setAction(AlarmReceiver.ACTION_C) ;
						break ;
					case 4 :
						intent.setAction(AlarmReceiver.ACTION_D) ;
						break ;
					default :
						break ;
				}
				PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this , 0 , intent ,
						0) ;
				// 取消闹铃
				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE) ;
				am.cancel(sender) ;
			}
		}) ;
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Activity#onDestroy()
	 */
	@ Override
	protected void onDestroy() {
		super.onDestroy() ;
	}
}
