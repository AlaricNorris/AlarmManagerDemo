package com.example.alarmmanagerdemo.ui ;

import java.util.Calendar ;
import java.util.Date ;
import android.app.Activity ;
import android.content.Intent ;
import android.content.SharedPreferences ;
import android.os.Bundle ;
import android.preference.PreferenceManager ;
import android.util.Log ;
import android.view.View ;
import android.view.View.OnClickListener ;
import android.widget.ImageButton ;
import android.widget.TextView ;
import android.widget.Toast ;
import butterknife.ButterKnife ;
import butterknife.InjectView ;
import butterknife.OnClick ;
import com.android.datetimepicker.date.DatePickerDialog ;
import com.android.datetimepicker.date.DatePickerDialog.OnDateSetListener ;
import com.android.datetimepicker.time.RadialPickerLayout ;
import com.android.datetimepicker.time.TimePickerDialog ;
import com.android.datetimepicker.time.TimePickerDialog.OnTimeSetListener ;
import com.example.alarmmanagerdemo.ClickUtil ;
import com.example.alarmmanagerdemo.R ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;

public class MainActivityTest extends Activity {

	private TextView mTextView_TimeDisplay ;

	TextView mTextView_EndDateDisplay ;

	TextView mTextView_StartDateDisplay ;

	@ InjectView ( R.id.btn_enddate )
	ImageButton mButton_EndDate ;

	@ OnClick ( R.id.btn_enddate )
	public void pickEndDate() {
		if(ClickUtil.isFastDoubleClick()) {
			return ;
		}
		Toast.makeText(getApplicationContext() , "asdfasdf" , 0).show() ;
	}

	private final Calendar mCalendar_StartDate = Calendar.getInstance() ;

	private Date mDate_Start = mCalendar_StartDate.getTime() ;

	private Calendar mCalendar_EndDate ;

	private Date mDate_End ;

	final TimePickerDialog timePickerDialog24h = TimePickerDialog.newInstance(
			new OnTimeSetListener() {

				@ Override
				public void onTimeSet(RadialPickerLayout view , int hourOfDay , int minute) {
					mCalendar_StartDate.set(minute , minute , hourOfDay , hourOfDay , minute , 0) ;
					mTextView_TimeDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_HHmm
							.format(mCalendar_StartDate.getTime())) ;
					mTextView_TimeDisplay.setTextColor(getResources().getColor(
							android.R.color.holo_blue_light)) ;
				}
			} , mCalendar_StartDate.get(Calendar.HOUR_OF_DAY) , mCalendar_StartDate
					.get(Calendar.MINUTE) , true) ;

	final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new OnDateSetListener() {

		public void onDateSet(DatePickerDialog datePickerDialog , int year , int month , int day) {
			mCalendar_StartDate.set(year , month , day) ;
			mTextView_StartDateDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd
					.format(mCalendar_StartDate.getTime())) ;
			mTextView_StartDateDisplay.setTextColor(getResources().getColor(
					android.R.color.holo_blue_light)) ;
		}
	} , mCalendar_StartDate.get(Calendar.YEAR) , mCalendar_StartDate.get(Calendar.MONTH) ,
			mCalendar_StartDate.get(Calendar.DAY_OF_MONTH)) ;

	@ Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main_test) ;
		mCalendar_EndDate = Calendar.getInstance() ;
		Log.i("tag" , "" + mCalendar_StartDate + "|" + mCalendar_EndDate) ;
		ButterKnife.inject(this) ;
		mTextView_TimeDisplay = ButterKnife.findById(this , R.id.tvTime) ;
		mTextView_StartDateDisplay = ButterKnife.findById(this , R.id.tvDate) ;
		mTextView_EndDateDisplay = ButterKnife.findById(this , R.id.text_enddate) ;
		mButton_EndDate = ButterKnife.findById(this , R.id.btn_enddate) ;
		resetDate() ;
		resetTime() ;
		mTextView_StartDateDisplay.setOnClickListener(new View.OnClickListener() {

			@ Override
			public void onClick(View v) {
				// nothing to do, just to let onTouchListener work 
			}
		}) ;
		mTextView_StartDateDisplay.setOnTouchListener(new SwipeDismissTouchListener(
				mTextView_StartDateDisplay , null ,
				new SwipeDismissTouchListener.DismissCallbacks() {

					@ Override
					public boolean canDismiss(Object token) {
						return true ;
					}

					@ Override
					public void onDismiss(View view , Object token) {
						resetDate() ;
					}
				})) ;
		mTextView_TimeDisplay.setOnClickListener(new View.OnClickListener() {

			@ Override
			public void onClick(View v) {
				// nothing to do, just to let onTouchListener work 
			}
		}) ;
		mTextView_TimeDisplay.setOnTouchListener(new SwipeDismissTouchListener(
				mTextView_TimeDisplay , null , new SwipeDismissTouchListener.DismissCallbacks() {

					@ Override
					public boolean canDismiss(Object token) {
						return true ;
					}

					@ Override
					public void onDismiss(View view , Object token) {
						resetTime() ;
					}
				})) ;
		/**
		 * TODO  用不着12小时制
		 */
//		final TimePickerDialog timePickerDialog12h = TimePickerDialog.newInstance(
//				new OnTimeSetListener() {
//
//					@ Override
//					public void onTimeSet(RadialPickerLayout view , int hourOfDay , int minute) {
//						Object c = pad3(hourOfDay) ;
//						mTextView_TimeDisplay.setText(new StringBuilder().append(pad2(hourOfDay))
//								.append(":").append(format2Digits(minute)).append(c)) ;
//						mTextView_TimeDisplay.setTextColor(getResources().getColor(
//								android.R.color.holo_blue_light)) ;
//					}
//				} , mCalendar.get(Calendar.HOUR_OF_DAY) , mCalendar.get(Calendar.MINUTE) , false) ;
		findViewById(R.id.btnChangeDate).setOnClickListener(new OnClickListener() {

			private String tag ;

			@ Override
			public void onClick(View v) {
				datePickerDialog.setYearRange(mCalendar_StartDate.get(Calendar.YEAR) , 2030) ;
				datePickerDialog.show(getFragmentManager() , tag) ;
				;
			}
		}) ;
		findViewById(R.id.btnChangeTime).setOnClickListener(new OnClickListener() {

			private String tag ;

			@ Override
			public void onClick(View v) {
				timePickerDialog24h.show(getFragmentManager() , tag) ;
				/**
				 * TODO  用不着12小时制
				 */
//				if(mSwitch_24.isChecked()) {
//				}
//				else {
//					timePickerDialog12h.show(getFragmentManager() , tag) ;
//				}
			}
		}) ;
	}

	private void resetTime() {
		mTextView_TimeDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_HHmm
				.format(mCalendar_StartDate.getTime())) ;
		mTextView_TimeDisplay.setTextColor(getResources().getColor(android.R.color.darker_gray)) ;
	}

	private void resetDate() {
		mTextView_StartDateDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd
				.format(mCalendar_StartDate.getTime())) ;
		mTextView_StartDateDisplay.setTextColor(getResources()
				.getColor(android.R.color.darker_gray)) ;
	}

	private static String format2Digits(int c) {
		if(c >= 10)
			return String.valueOf(c) ;
		else
			return "0" + String.valueOf(c) ;
	}

	/**
	 * TODO  用不着12小时制
	 */
//	private static String pad2(int c) {
//		if(c == 12)
//			return String.valueOf(c) ;
//		if(c == 00)
//			return String.valueOf(c + 12) ;
//		if(c > 12)
//			return String.valueOf(c - 12) ;
//		else
//			return String.valueOf(c) ;
//	}
	/**
	 * TODO  用不着12小时制
	 */
//	private static String pad3(int c) {
//		if(c == 12)
//			return " PM" ;
//		if(c == 00)
//			return " AM" ;
//		if(c > 12)
//			return " PM" ;
//		else
//			return " AM" ;
//	}
	@ Override
	protected void onResume() {
		super.onResume() ;
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext()) ;
		boolean previouslyStarted = prefs.getBoolean(getString(R.string.app_name) , false) ;
		if( ! previouslyStarted) {
			SharedPreferences.Editor edit = prefs.edit() ;
			edit.putBoolean(getString(R.string.app_name) , Boolean.TRUE) ;
			edit.commit() ;
			LaunchHint() ;
		}
	}

	private void LaunchHint() {
		Intent launchNewIntent = new Intent(MainActivityTest.this , Hint.class) ;
		startActivityForResult(launchNewIntent , 0) ;
	}
}