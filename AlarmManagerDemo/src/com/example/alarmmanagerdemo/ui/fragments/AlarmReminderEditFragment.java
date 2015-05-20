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
import android.widget.Switch ;
import android.widget.Toast ;
import butterknife.ButterKnife ;
import butterknife.InjectView ;
import butterknife.InjectViews ;
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
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
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

	private boolean editFlag ;

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@ Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		extractBundle() ;
	}

	/**
	 * 	extractBundle:()
	 *  ──────────────────────────────────    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-20	上午9:10:36	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void extractBundle() {
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

//	@ InjectViews ( { R.id.edit_id , R.id.edit_title , R.id.edit_description , R.id.edit_start ,
//			R.id.edit_end , R.id.edit_triggerattime } )
	EditText mEditText_ID , mEditText_title , mEditText_description , mEditText_start ,
			mEditText_end , mEditText_triggerAtTime ;

//	@ InjectView ( R.id.switch_alarm )
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
		mEditText_start = ButterKnife.findById(mContentView , R.id.edit_start) ;
		mEditText_end = ButterKnife.findById(mContentView , R.id.edit_end) ;
		mEditText_triggerAtTime = ButterKnife.findById(mContentView , R.id.edit_triggerattime) ;
		mButton.setOnClickListener(this) ;
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
		mEditText_start.setText(AlarmReminderEntity.mSimpleDateFormat_YYYYMMDD.format(new Date())
				+ "") ;
		mEditText_end.setText(AlarmReminderEntity.mSimpleDateFormat_YYYYMMDD.format(new Date(
				TimeConstants.DAY + System.currentTimeMillis())) + "") ;
		mEditText_triggerAtTime.setText(AlarmReminderEntity.mSimpleDateFormat_YYYYMMDD_HHMMSS
				.format(new Date(TimeConstants.MINUTES_SEMI + System.currentTimeMillis())) + "") ;
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
		mEditText_start.setText(mAlarmReminderEntity.getStratdateString() + "") ;
		mEditText_end.setText(mAlarmReminderEntity.getEnddateString() + "") ;
		mEditText_triggerAtTime.setText(mAlarmReminderEntity.getTriggerAtTimes() + "") ;
	}

	private boolean saveAlarmReminder() {
		if(editFlag) {
			mAlarmReminderEntity.setId(Integer.parseInt(mEditText_ID.getText().toString())) ;
			mAlarmReminderEntity.setTitle(mEditText_title.getText().toString().trim()) ;
			mAlarmReminderEntity.setDescription(mEditText_description.getText().toString().trim()) ;
			mAlarmReminderEntity.setTimesperday(1) ;
			mAlarmReminderEntity.setStratdate(mEditText_start.getText().toString().trim()) ;
			mAlarmReminderEntity.setEnddate(mEditText_end.getText().toString().trim()) ;
			mAlarmReminderEntity.setTriggerAtTimes(mEditText_triggerAtTime.getText().toString()
					.trim()) ;
			mAlarmReminderEntity.setIsOn(mSwitch.isChecked() ? AlarmReminderEntity.FLAG_TRUE
					: AlarmReminderEntity.FLAG_FALSE) ;
			mAlarmReminderEntity.setNeedVibration(AlarmReminderEntity.FLAG_TRUE) ;
		}
		else {
			mAlarmReminderEntity = new AlarmReminderEntity() ;
			mAlarmReminderEntity.setId(Integer.parseInt(mEditText_ID.getText().toString())) ;
			mAlarmReminderEntity.setTitle(mEditText_title.getText().toString().trim()) ;
			mAlarmReminderEntity.setDescription(mEditText_description.getText().toString().trim()) ;
			mAlarmReminderEntity.setTimesperday(1) ;
			mAlarmReminderEntity.setStratdate(mEditText_start.getText().toString().trim()) ;
			mAlarmReminderEntity.setEnddate(mEditText_end.getText().toString().trim()) ;
			mAlarmReminderEntity.setTriggerAtTimes(mEditText_triggerAtTime.getText().toString()
					.trim()) ;
			mAlarmReminderEntity.setIsOn(mSwitch.isChecked() ? AlarmReminderEntity.FLAG_TRUE
					: AlarmReminderEntity.FLAG_FALSE) ;
			mAlarmReminderEntity.setNeedVibration(AlarmReminderEntity.FLAG_TRUE) ;
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
			case R.id.btn_save :
				popupConfirmDialog() ;
				break ;
			default :
				break ;
		}
	}

	NiftyDialogBuilder dialogBuilder ;

	/**
	 * 	popupConfirmDialog:()
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
		dialogBuilder = NiftyDialogBuilder.getInstance(getActivity()) ;
		dialogBuilder.withTitle("确认保存？")
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
				.withDuration(700) //def
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
							dialogBuilder.dismiss() ;
							Toast.makeText(getActivity() , "保存成功" , 0).show() ;
							getActivity().finish() ;
							EventBus.getDefault().post(
									new AlarmReminderHomeActivity.Event_Refresh()) ;
						}
						else {
							dialogBuilder.dismiss() ;
							Toast.makeText(getActivity() , "保存失败" , 0).show() ;
						}
					}
				}).setButton2Click(new View.OnClickListener() {

					@ Override
					public void onClick(View v) {
						dialogBuilder.dismiss() ;
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
		Date tempDate = new Date(120000 + System.currentTimeMillis()) ;
		Log.i("tag" , "tempDate:" + tempDate.toString() + "\t" + tempDate.getTime()) ;
		try {
			tempDate = AlarmReminderEntity.mSimpleDateFormat_YYYYMMDD_HHMMSS
					.parse(mAlarmReminderEntity.getTriggerAtTimes()) ;
		}
		catch(ParseException e) {
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
}
