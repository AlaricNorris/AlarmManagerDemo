/**
 * 	AlarmReminderDetailActivity.java
 * 	com.example.alarmmanagerdemo.ui
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-20 		20144L151
 *	Copyright (c) 2015, TNT All Rights Reserved.
 */
package com.example.alarmmanagerdemo.ui ;

import java.text.ParseException ;
import java.util.Calendar ;
import java.util.Date ;
import android.app.Activity ;
import android.os.Bundle ;
import android.util.Log ;
import android.view.View ;
import android.view.View.OnClickListener ;
import android.widget.ImageButton ;
import android.widget.TextView ;
import butterknife.ButterKnife ;
import com.android.datetimepicker.date.DatePickerDialog ;
import com.android.datetimepicker.date.DatePickerDialog.OnDateSetListener ;
import com.android.datetimepicker.time.RadialPickerLayout ;
import com.android.datetimepicker.time.TimePickerDialog ;
import com.android.datetimepicker.time.TimePickerDialog.OnTimeSetListener ;
import com.example.alarmmanagerdemo.R ;
import com.example.alarmmanagerdemo.daos.AlarmReminderDAO ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;

/**
 *	ClassName:	AlarmReminderDetailActivity
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	20144L151		
 *	@contact  	Norris.sly@gmail.com
 *	@version  	Ver 1.0
 *	@since   	I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-20		下午2:07:56
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 * 	Modified By 	20144L151		 2015-5-20下午2:07:56
 *	Modifications:	TODO
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class AlarmReminderDetailActivity extends Activity implements OnClickListener {

	/**
	 * 	显示时间
	 * 	TextView			:		mTextView_TimeDisplay	
	 * 	@since Ver 1.0
	 */
	private TextView mTextView_TimeDisplay ;

	/**
	 * 	开始日期
	 * 	TextView			:		mTextView_StartDateDisplay	
	 * 	@since Ver 1.0
	 */
	private TextView mTextView_StartDateDisplay ;

	/**
	 * 	结束日期
	 * 	TextView			:		mTextView_EndDateDisplay	
	 * 	@since Ver 1.0
	 */
	private TextView mTextView_EndDateDisplay ;

	/**
	 * 	选择结束日期
	 * 	ImageButton			:		mButton_EndDate	
	 * 	@since Ver 1.0
	 */
	private ImageButton mButton_StartDate ;

	/**
	 * 	选择结束日期
	 * 	ImageButton			:		mButton_EndDate	
	 * 	@since Ver 1.0
	 */
	private ImageButton mButton_EndDate ;

	/**
	 * 	选择提醒时间
	 * 	ImageButton			:		mButton_Time	
	 * 	@since Ver 1.0
	 */
	private ImageButton mButton_Time ;

	/**
	 * 	开始日期依赖的日历对象
	 * 	Calendar			:		mCalendar_StartDate	
	 * 	@since Ver 1.0
	 */
	private Calendar mCalendar_StartDate ;

	private Date mDate_Start ;

	/**
	 * 	开始日期依赖的日历对象
	 * 	Calendar			:		mCalendar_EndDate	
	 * 	@since Ver 1.0
	 */
	private Calendar mCalendar_EndDate ;

	private Date mDate_End ;

	/**
	 * 	提醒时间
	 * 	Date			:		mDate_TriggerAtTime	
	 * 	@since Ver 1.0
	 */
	private Date mDate_TriggerAtTime ;

	private DatePickerDialog mDatePickerDialog_Start ;

	private DatePickerDialog mDatePickerDialog_End ;

	private TimePickerDialog mTimePickerDialog ;

	private boolean hasBundle ;

	@ Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main_test) ;
		hasBundle = extractBundleData() ;
		initContentView() ;
		parseDateTime() ;
		fullfillContentView() ;
	}

	/**
	 * 	parseDateTime:()
	 *  ──────────────────────────────────	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-25上午8:54:09
	 *	Modifications:	 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void parseDateTime() {
		mCalendar_StartDate = Calendar.getInstance() ;
		mCalendar_EndDate = Calendar.getInstance() ;
		if(hasBundle) {
			// 解析实体的日期
			mDate_Start = mAlarmReminderEntity.getStratdate() ;
			mDate_End = mAlarmReminderEntity.getEnddate() ;
			mCalendar_StartDate.setTime(mDate_Start) ;
			mCalendar_EndDate.setTime(mDate_End) ;
			mDate_TriggerAtTime = mAlarmReminderEntity.getTriggerAtTime() ;
		}
		else {
			// 自动生成日期
			mDate_Start = mCalendar_StartDate.getTime() ;
			mCalendar_EndDate.add(Calendar.DATE , 1) ;
			mDate_End = mCalendar_EndDate.getTime() ;
			try {
				mDate_TriggerAtTime = AlarmReminderEntity.mSimpleDateFormat_HHmmss
						.parse(AlarmReminderEntity.mSimpleDateFormat_HHmmss.format(mDate_Start)) ;
			}
			catch(ParseException e) {
				mDate_TriggerAtTime = mDate_Start ;
				e.printStackTrace() ;
			}
		}
		Log.i("tag" , "Calender" + mCalendar_StartDate + "|" + mCalendar_EndDate) ;
		Log.i("tag" , "Date" + mDate_Start + "|" + mDate_End) ;
		mTimePickerDialog = TimePickerDialog.newInstance(new OnTimeSetListener() {

			@ Override
			public void onTimeSet(RadialPickerLayout view , int hourOfDay , int minute) {
				mCalendar_StartDate.set(minute , minute , hourOfDay , hourOfDay , minute , 0) ;
				mTextView_TimeDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_HHmmss
						.format(mDate_TriggerAtTime)) ;
				mTextView_TimeDisplay.setTextColor(getResources().getColor(
						android.R.color.holo_blue_light)) ;
			}
		} , mCalendar_StartDate.get(Calendar.HOUR_OF_DAY) , mCalendar_StartDate
				.get(Calendar.MINUTE) , true) ;
		mDatePickerDialog_Start = DatePickerDialog.newInstance(
				new OnDateSetListener() {

					public void onDateSet(DatePickerDialog datePickerDialog , int year , int month ,
							int day) {
						mCalendar_StartDate.set(year , month , day) ;
						mTextView_StartDateDisplay
								.setText(AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd
										.format(mCalendar_StartDate.getTime())) ;
						mTextView_StartDateDisplay.setTextColor(getResources().getColor(
								android.R.color.holo_blue_light)) ;
					}
				} , mCalendar_StartDate.get(Calendar.YEAR) , mCalendar_StartDate
						.get(Calendar.MONTH) ,
				mCalendar_StartDate.get(Calendar.DAY_OF_MONTH)) ;
		mDatePickerDialog_End = DatePickerDialog.newInstance(
				new OnDateSetListener() {

					public void onDateSet(DatePickerDialog datePickerDialog , int year , int month ,
							int day) {
						mCalendar_EndDate.set(year , month , day) ;
						mTextView_EndDateDisplay
								.setText(AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd
										.format(mCalendar_EndDate.getTime())) ;
						mTextView_EndDateDisplay.setTextColor(getResources().getColor(
								android.R.color.holo_blue_light)) ;
					}
				} , mCalendar_EndDate.get(Calendar.YEAR) , mCalendar_EndDate.get(Calendar.MONTH) ,
				mCalendar_EndDate.get(Calendar.DAY_OF_MONTH)) ;
	}

	/**
	 * 	fullfillContentView:()
	 *  ──────────────────────────────────	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-22下午6:07:24
	 *	Modifications:	 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void fullfillContentView() {
		mTextView_StartDateDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd
				.format(mDate_Start)) ;
		mTextView_EndDateDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd
				.format(mDate_End)) ;
		mTextView_TimeDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_HHmmss
				.format(mDate_TriggerAtTime)) ;
	}

	/**
	 * 	initContentView:()
	 *  ──────────────────────────────────	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-22下午6:00:37
	 *	Modifications:	 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void initContentView() {
		ButterKnife.inject(this) ;
		mTextView_StartDateDisplay = ButterKnife.findById(this , R.id.tvDate) ;
		mTextView_EndDateDisplay = ButterKnife.findById(this , R.id.text_enddate) ;
		mTextView_TimeDisplay = ButterKnife.findById(this , R.id.tvTime) ;
		mButton_StartDate = ButterKnife.findById(this , R.id.btnChangeDate) ;
		mButton_EndDate = ButterKnife.findById(this , R.id.btn_enddate) ;
		mButton_Time = ButterKnife.findById(this , R.id.btnChangeTime) ;
		mButton_StartDate.setOnClickListener(this) ;
		mButton_EndDate.setOnClickListener(this) ;
		mButton_Time.setOnClickListener(this) ;
	}

	private int mID ;

	private AlarmReminderEntity mAlarmReminderEntity ;

	/**
	 * 	extractBundleData:()
	 *  ──────────────────────────────────	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-22下午5:48:02
	 *	Modifications:	
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private boolean extractBundleData() {
		mID = getIntent().getIntExtra( ("ID") , - 1) ;
		Log.i("tag" , "extractBundleFromIntent" + mID + "detail") ;
		if(mID == - 1) {
			//TODO
			return false ;
		}
		mAlarmReminderEntity = new AlarmReminderDAO(getApplicationContext()).queryByID(mID) ;
		if(mAlarmReminderEntity == null) {
			//TODO
			return false ;
		}
		return true ;
	}

//	private void resetTime() {
//		mTextView_TimeDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_HHmm
//				.format(mDate_TriggerAtTime)) ;
//		mTextView_TimeDisplay.setTextColor(getResources().getColor(android.R.color.darker_gray)) ;
//	}
//	private void resetDate() {
//		if(hasBundle) {
//			mDate_Start = mAlarmReminderEntity.getStratdate() ;
//			if(mCalendar_StartDate == null) {
//				mCalendar_StartDate = Calendar.getInstance() ;
//			}
//			mCalendar_StartDate.setTime(mDate_Start) ;
//			mTextView_StartDateDisplay.setText(mAlarmReminderEntity.getStratdateString()) ;
//			mTextView_StartDateDisplay.setTextColor(getResources().getColor(
//					android.R.color.darker_gray)) ;
//		}
//		else {
//			mDate_Start = new Date() ;
//			if(mCalendar_StartDate == null) {
//				mCalendar_StartDate = Calendar.getInstance() ;
//			}
//			mCalendar_StartDate.setTime(mDate_Start) ;
//			mTextView_StartDateDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_HHmm
//					.format(mDate_Start)) ;
//			mTextView_StartDateDisplay.setTextColor(getResources().getColor(
//					android.R.color.darker_gray)) ;
//		}
//	}
	/**
	 * 	(non-Javadoc)
	 * 	@see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@ Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.btnChangeDate :
				mDatePickerDialog_Start.setYearRange(mCalendar_StartDate.get(Calendar.YEAR) , 2030) ;
				mDatePickerDialog_Start.show(getFragmentManager() , "START_DATE") ;
				break ;
			case R.id.btn_enddate :
				mDatePickerDialog_End.setYearRange(mCalendar_StartDate.get(Calendar.YEAR) , 2030) ;
				mDatePickerDialog_End.show(getFragmentManager() , "END_DATE") ;
				break ;
			case R.id.btnChangeTime :
				mTimePickerDialog.show(getFragmentManager() , "TIME") ;
				break ;
			default :
				break ;
		}
	}
}
