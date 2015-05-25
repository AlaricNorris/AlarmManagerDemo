/**
 * 	AlarmReminderEditFragment.java
 * 	com.example.alarmmanagerdemo.ui.fragments
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-19 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-19	下午3:06:28	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo.ui.fragments ;

import java.text.ParseException ;
import java.util.Calendar ;
import java.util.Date ;
import android.app.AlarmManager ;
import android.app.Fragment ;
import android.app.PendingIntent ;
import android.content.Context ;
import android.content.Intent ;
import android.os.Bundle ;
import android.util.Log ;
import android.view.LayoutInflater ;
import android.view.View ;
import android.view.View.OnClickListener ;
import android.view.ViewGroup ;
import android.widget.Button ;
import android.widget.EditText ;
import android.widget.ImageButton ;
import android.widget.Switch ;
import android.widget.TextView ;
import android.widget.Toast ;
import butterknife.ButterKnife ;
import butterknife.InjectView ;
import com.android.datetimepicker.date.DatePickerDialog ;
import com.android.datetimepicker.date.DatePickerDialog.OnDateSetListener ;
import com.android.datetimepicker.time.RadialPickerLayout ;
import com.android.datetimepicker.time.TimePickerDialog ;
import com.android.datetimepicker.time.TimePickerDialog.OnTimeSetListener ;
import com.example.alarmmanagerdemo.ClickUtil ;
import com.example.alarmmanagerdemo.R ;
import com.example.alarmmanagerdemo.daos.AlarmReminderDAO ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;
import com.example.alarmmanagerdemo.receivers.AlarmReminderReceiver ;
import com.example.alarmmanagerdemo.thirdparty.niftydialog.Effectstype ;
import com.example.alarmmanagerdemo.thirdparty.niftydialog.NiftyDialogBuilder ;
import com.example.alarmmanagerdemo.ui.AlarmReminderHomeActivity ;
import com.example.alarmmanagerdemo.utils.TimeConstants ;
import de.greenrobot.event.EventBus ;

/**
 *	ClassName:	AlarmReminderEditFragment
 *	Function: 	编辑提醒界面
 *	@author   	Norris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-19		下午3:06:28
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-19	下午3:06:28	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class AlarmReminderEditFragment extends Fragment implements OnClickListener {

	/**
	 * 	newInstance:(带参数新建Fragment)
	 *  ──────────────────────────────────
	 * 	@param 		inAlarmReminderEntity
	 * 	@return    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-19	下午3:09:49	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public static Fragment newInstance(Boolean inEditFlag ,
			AlarmReminderEntity inAlarmReminderEntity) {
		// Bundle up all of the data needed to create the fragment
		Bundle mBundle = new Bundle() ;
		mBundle.putSerializable(AlarmReminderHomeActivity.BUNDLE_KEY_ALARMREMINDER ,
				inAlarmReminderEntity) ;
		mBundle.putBoolean(AlarmReminderHomeActivity.BUNDLE_KEY_EDITFLAG , inEditFlag) ;
		if(inEditFlag) {
			if(inAlarmReminderEntity == null) {
				return ErrorInfoFragment.getInstance(mBundle) ;
			}
		}
		AlarmReminderEditFragment mFragment = new AlarmReminderEditFragment() ;
		mFragment.setArguments(mBundle) ;
		return mFragment ;
	}

	private AlarmReminderEntity mAlarmReminderEntity ;

	/**
	 * 	编辑模式标记
	 * 	boolean			:		editFlag	
	 * 	@since Ver 1.0
	 */
	private boolean editFlag ;

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@ Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		extractBundleData() ;
		parseDateTime() ;
	}

	/**
	 * 	extractBundleData:()
	 *  ──────────────────────────────────    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-20	上午9:10:36	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void extractBundleData() {
		try {
			mAlarmReminderEntity = (AlarmReminderEntity) getArguments().getSerializable(
					AlarmReminderHomeActivity.BUNDLE_KEY_ALARMREMINDER) ;
		}
		catch(Exception e) {
			Log.i("tag" , "extractBundle Exception "
					+ "getSerializable(AlarmReminderHomeActivity.BUNDLE_KEY_ALARMREMINDER)") ;
			e.printStackTrace() ;
		}
		try {
			editFlag = getArguments().getBoolean(AlarmReminderHomeActivity.BUNDLE_KEY_EDITFLAG) ;
		}
		catch(Exception e) {
			Log.i("tag" , "extractBundle Exception "
					+ "getBoolean(AlarmReminderHomeActivity.BUNDLE_KEY_EDITFLAG)") ;
			e.printStackTrace() ;
		}
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@ Override
	public View onCreateView(LayoutInflater inflater , ViewGroup container ,
			Bundle savedInstanceState) {
		if(editFlag && mAlarmReminderEntity == null) {
			return inflater.inflate(R.layout.fragment_page_error , null) ;
		}
		View mContentView = inflater.inflate(R.layout.fragment_alarmreminder_edit , null) ;
		ininContentView(mContentView) ;
		return mContentView ;
	}

	EditText mEditText_ID , mEditText_title , mEditText_description
//	, 
//	mEditText_start ,
//			mEditText_end , mEditText_triggerAtTime 
			;

	@ InjectView ( R.id.text_duration )
	TextView mTextView_Duration ;

	Switch mSwitch ;

	Button mButton ;

	/**
	 * 	ininContentView:()
	 *  ──────────────────────────────────
	 * 	@param mContentView    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-20	上午9:42:12	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void ininContentView(View mContentView) {
		ButterKnife.inject(this , mContentView) ;
		mButton = ButterKnife.findById(mContentView , R.id.btn_save) ;
		mSwitch = ButterKnife.findById(mContentView , R.id.switch_alarm) ;
		mEditText_ID = ButterKnife.findById(mContentView , R.id.edit_id) ;
		mEditText_title = ButterKnife.findById(mContentView , R.id.edit_title) ;
		mEditText_description = ButterKnife.findById(mContentView , R.id.edit_description) ;
		mTextView_Duration = ButterKnife.findById(mContentView , R.id.text_duration) ;
		mTextView_Duration.setOnClickListener(this) ;
		mButton.setOnClickListener(this) ;
		//************************************************
		mTextView_StartDateDisplay = ButterKnife.findById(mContentView , R.id.tvDate) ;
		mTextView_EndDateDisplay = ButterKnife.findById(mContentView , R.id.text_enddate) ;
		mTextView_TimeDisplay = ButterKnife.findById(mContentView , R.id.tvTime) ;
		mButton_StartDate = ButterKnife.findById(mContentView , R.id.btnChangeDate) ;
		mButton_EndDate = ButterKnife.findById(mContentView , R.id.btn_enddate) ;
		mButton_Time = ButterKnife.findById(mContentView , R.id.btnChangeTime) ;
		mButton_StartDate.setOnClickListener(this) ;
		mButton_EndDate.setOnClickListener(this) ;
		mButton_Time.setOnClickListener(this) ;
		mTextView_StartDateDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd
				.format(mDate_Start)) ;
		mTextView_EndDateDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd
				.format(mDate_End)) ;
		mTextView_TimeDisplay.setText(AlarmReminderEntity.mSimpleDateFormat_HHmmss
				.format(mDate_TriggerAtTime)) ;
		if(editFlag) {
			if(mAlarmReminderEntity != null) {
				fillContentView() ;
				return ;
			}
		}
		else {
			generateDefaultContentView() ;
		}
	}

	/**
	 * 	generateDefaultContentView:()
	 *  ──────────────────────────────────    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-20	上午9:56:16	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void generateDefaultContentView() {
		mSwitch.setChecked(true) ;
		mEditText_ID.setText(new AlarmReminderDAO(getActivity()).queryLatestId() + 1 + "") ;
		mEditText_title.setText("Auto") ;
		mEditText_description.setText("该吃药了") ;
		mTextView_Duration.setText(TimeConstants.converUnitReturnString(duration ,
				TimeConstants.TimeUnit.Unit_MINUTE)) ;
	}

	/**
	 * 	fillContentView:()
	 *  ──────────────────────────────────    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-20	上午9:55:19	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void fillContentView() {
		mSwitch.setChecked(mAlarmReminderEntity.isOn()) ;
		mEditText_ID.setText(mAlarmReminderEntity.getId() + "") ;
		mEditText_title.setText(mAlarmReminderEntity.getTitle() + "") ;
		mEditText_description.setText(mAlarmReminderEntity.getDescription() + "") ;
		duration = mAlarmReminderEntity.getDuration() ;
		mTextView_Duration.setText(TimeConstants.converUnitReturnString(duration ,
				TimeConstants.TimeUnit.Unit_MINUTE)) ;
	}

	private long duration = TimeConstants.MINUTES_SEMI ;

	private boolean saveAlarmReminder() {
		if(editFlag) {
			// 编辑模式  即更新数据库行
			mAlarmReminderEntity.setId(Integer.parseInt(mEditText_ID.getText().toString())) ;
			mAlarmReminderEntity.setTitle(mEditText_title.getText().toString().trim()) ;
			mAlarmReminderEntity.setDescription(mEditText_description.getText().toString().trim()) ;
			mAlarmReminderEntity.setTimesperday(1) ;
			mAlarmReminderEntity.setStratdate(mDate_Start) ;
			mAlarmReminderEntity.setEnddate(mDate_End) ;
			mAlarmReminderEntity.setTriggerAtTime(mDate_TriggerAtTime) ;
			mAlarmReminderEntity.setIsOn(mSwitch.isChecked() ? AlarmReminderEntity.FLAG_TRUE
					: AlarmReminderEntity.FLAG_FALSE) ;
			mAlarmReminderEntity.setNeedVibration(AlarmReminderEntity.FLAG_TRUE) ;
			mAlarmReminderEntity.setDuration(duration) ;
		}
		else {
			// 非编辑模式  插入新行
			mAlarmReminderEntity = new AlarmReminderEntity() ;
			mAlarmReminderEntity.setId(Integer.parseInt(mEditText_ID.getText().toString())) ;
			mAlarmReminderEntity.setTitle(mEditText_title.getText().toString().trim()) ;
			mAlarmReminderEntity.setDescription(mEditText_description.getText().toString().trim()) ;
			mAlarmReminderEntity.setTimesperday(1) ;
			mAlarmReminderEntity.setStratdate(mDate_Start) ;
			mAlarmReminderEntity.setEnddate(mDate_End) ;
			mAlarmReminderEntity.setTriggerAtTime(mDate_TriggerAtTime) ;
			mAlarmReminderEntity.setIsOn(mSwitch.isChecked() ? AlarmReminderEntity.FLAG_TRUE
					: AlarmReminderEntity.FLAG_FALSE) ;
			mAlarmReminderEntity.setNeedVibration(AlarmReminderEntity.FLAG_TRUE) ;
			mAlarmReminderEntity.setDuration(duration) ;
		}
		AlarmReminderDAO alarmReminderDAO = new AlarmReminderDAO(getActivity()) ;
		return alarmReminderDAO.createOrUpdate(mAlarmReminderEntity) ;
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@ Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.text_duration :
				//TODO
//				popupConfirmDialog() ;
				break ;
			case R.id.btn_save :
				popupConfirmDialog() ;
				break ;
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

	NiftyDialogBuilder mDialog_Confirm ;

	/**
	 * 	popupConfirmDialog:(保存事件回调)
	 *  ──────────────────────────────────    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-20	上午10:13:17	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void popupConfirmDialog() {
		if(ClickUtil.isFastDoubleClick()) {
			return ;
		}
		mDialog_Confirm = NiftyDialogBuilder.getInstance(getActivity()) ;
		mDialog_Confirm.withTitle("确认保存？")
				//.withTitle(null)  no title
				.withTitleColor("#FFFFFF")
				//def
				.withDividerColor("#11000000")
				//def
				.withMessage("请确认开启提醒")
				//.withMessage(null)  no Msg
				.withMessageColor("#FFFFFFFF")
				//def  | withMessageColor(int resid)
				.withDialogColor("#FFE74C3C")
				//def  | withDialogColor(int resid)
				.withIcon(getResources().getDrawable(android.R.drawable.ic_dialog_alert))
				.withDuration(400) //def
				.withEffect(Effectstype.Newspager) //def Effectstype.Slidetop
				.withButton1Text("确认") //def gone
				.withButton2Text("取消") //def gone
				.isCancelableOnTouchOutside(false) //def    | isCancelable(true)
//				.setCustomView(R.layout.custom_view , v.getContext()) //.setCustomView(View or ResId,context)
				.setButton1Click(new View.OnClickListener() {

					@ Override
					public void onClick(View v) {
						if(saveAlarmReminder()) {
							setupAlarmReminder() ;
							mDialog_Confirm.dismiss() ;
							Toast.makeText(getActivity() , "保存成功" , 0).show() ;
							getActivity().finish() ;
							EventBus.getDefault().post(
									new AlarmReminderHomeActivity.Event_Refresh()) ;
						}
						else {
							mDialog_Confirm.dismiss() ;
							Toast.makeText(getActivity() , "保存失败" , 0).show() ;
						}
					}
				}).setButton2Click(new View.OnClickListener() {

					@ Override
					public void onClick(View v) {
						mDialog_Confirm.dismiss() ;
					}
				}).show() ;
	}

	/**
	 * 	setupAlarmReminder:()
	 *  ──────────────────────────────────	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-20上午10:45:56
	 *	Modifications:	TODO
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	protected void setupAlarmReminder() {
		if( ! mAlarmReminderEntity.isOn()) {
			cancelAlarmReminder() ;
			return ;
		}
		Intent intent = new Intent(getActivity().getApplicationContext() ,
				AlarmReminderReceiver.class) ;
		intent.putExtra("ID" , mAlarmReminderEntity.getId()) ;
		PendingIntent sender = PendingIntent.getBroadcast(getActivity().getApplicationContext() ,
				mAlarmReminderEntity.getId() , intent , 0) ;
		AlarmManager manager = (AlarmManager) getActivity().getApplicationContext()
				.getSystemService(Context.ALARM_SERVICE) ;
		Log.i("tag" , "currentTimeMillis:" + System.currentTimeMillis()) ;
		Date tempDate = new Date(TimeConstants.MINUTES_2 + System.currentTimeMillis()) ;
		Log.i("tag" , "tempDate:" + tempDate.toString() + "\t" + tempDate.getTime()) ;
		Date mDate_YMD = new Date() ;
		try {
			tempDate = mAlarmReminderEntity.getTriggerAtTime() ;
			long milliseconds = mDate_YMD.getTime() / TimeConstants.DAY * TimeConstants.DAY
					+ tempDate.getTime() % TimeConstants.DAY ;
			Log.i("tag" , "milliseconds:" + tempDate) ;
			tempDate = new Date(milliseconds) ;
		}
		catch(Exception e) {
			e.printStackTrace() ;
		}
		Log.i("tag" , "tempDate:" + tempDate.toString() + "\t" + tempDate.getTime()) ;
//		manager.set(AlarmManager.RTC_WAKEUP , tempDate.getTime() , sender) ;
		manager.setRepeating(AlarmManager.RTC_WAKEUP , tempDate.getTime() ,
				TimeConstants.MINUTES_SEMI , sender) ;
	}

	/**
	 * 	cancelAlarmReminder:()
	 *  ──────────────────────────────────	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-20下午1:51:52
	 *	Modifications:	TODO
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void cancelAlarmReminder() {
		Intent intent = new Intent(getActivity().getApplicationContext() ,
				AlarmReminderReceiver.class) ;
		intent.putExtra("ID" , mAlarmReminderEntity.getId()) ;
		PendingIntent sender = PendingIntent.getBroadcast(getActivity().getApplicationContext() ,
				mAlarmReminderEntity.getId() , intent , 0) ;
		AlarmManager manager = (AlarmManager) getActivity().getApplicationContext()
				.getSystemService(Context.ALARM_SERVICE) ;
		manager.cancel(sender) ;
	}

	//***************以下为日期时间相关设置开始*****************************
	/**
	 * *************************************************
	 */
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

	/**
	 * 	parseDateTime:(解析日期时间操作)
	 *  ──────────────────────────────────	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-25上午8:54:09
	 *	Modifications:	
	 *	|——若为编辑模式		从实体类中解析提醒的日期时间
	 *	|——若非编辑模式		取当前时间解析提醒的日期时间
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void parseDateTime() {
		mCalendar_StartDate = Calendar.getInstance() ;
		mCalendar_EndDate = Calendar.getInstance() ;
		if(editFlag) {
			// 解析实体的日期
			mDate_Start = mAlarmReminderEntity.getStratdate() ;
			mDate_End = mAlarmReminderEntity.getEnddate() ;
			mCalendar_StartDate.setTime(mDate_Start) ;
			mCalendar_EndDate.setTime(mDate_End) ;
			mDate_TriggerAtTime = mAlarmReminderEntity.getTriggerAtTime() ;
		}
		else {
			// 自动生成日期
			mCalendar_StartDate.add(Calendar.MINUTE , 1) ;
			mCalendar_StartDate.set(Calendar.SECOND , 0) ;
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
				mDate_TriggerAtTime.setHours(hourOfDay) ;
				mDate_TriggerAtTime.setMinutes(minute) ;
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
						mDate_Start = mCalendar_StartDate.getTime() ;
						mTextView_StartDateDisplay
								.setText(AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd
										.format(mDate_Start)) ;
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
						mDate_End = mCalendar_EndDate.getTime() ;
						mTextView_EndDateDisplay
								.setText(AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd
										.format(mDate_End)) ;
						mTextView_EndDateDisplay.setTextColor(getResources().getColor(
								android.R.color.holo_blue_light)) ;
					}
				} , mCalendar_EndDate.get(Calendar.YEAR) , mCalendar_EndDate.get(Calendar.MONTH) ,
				mCalendar_EndDate.get(Calendar.DAY_OF_MONTH)) ;
	}
}
