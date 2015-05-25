/**
 * 	AlarmReminderEditActivity.java
 * 	com.example.alarmmanagerdemo
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-18 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午3:56:53	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo.ui ;

import android.app.Activity ;
import android.app.FragmentManager ;
import android.app.FragmentTransaction ;
import android.content.Context ;
import android.os.Bundle ;
import android.view.View ;
import android.view.inputmethod.InputMethodManager ;
import android.widget.LinearLayout ;
import com.example.alarmmanagerdemo.ClickUtil ;
import com.example.alarmmanagerdemo.R ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;
import com.example.alarmmanagerdemo.ui.fragments.AlarmReminderEditFragment ;

/**
 *	ClassName:	AlarmReminderEditActivity
 *	@author   	Norris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-18		下午3:56:53
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午3:56:53	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class AlarmReminderEditActivity extends Activity {

	private boolean editFlag = false ;

	private FragmentManager mFragmentManager ;

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@ Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_alarm_reminder_edit) ;
		extractBundleData() ;
		initContentView() ;
	}

	private LinearLayout mLinearLayout ;

	/**
	 * 	initContentView:()
	 *  ──────────────────────────────────    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-18	下午4:00:29	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void initContentView() {
		mFragmentManager = getFragmentManager() ;
		FragmentTransaction mTransaction = mFragmentManager.beginTransaction() ;
		mTransaction.add(R.id.frame_content ,
				AlarmReminderEditFragment.newInstance(editFlag , mAlarmReminderEntity)) ;
		mTransaction.commit() ;
	}

	private Bundle mBundle ;

	private AlarmReminderEntity mAlarmReminderEntity ;

	/**
	 * 	extractBundleData:()
	 *  ──────────────────────────────────    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-18	下午3:57:54	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void extractBundleData() {
		try {
			mBundle = getIntent().getExtras() ;
		}
		catch(Exception e) {
		}
		if(mBundle != null) {
			editFlag = mBundle.getBoolean(AlarmReminderHomeActivity.BUNDLE_KEY_EDITFLAG) ;
		}
		if(editFlag) {
			mAlarmReminderEntity = (AlarmReminderEntity) mBundle
					.getSerializable(AlarmReminderHomeActivity.BUNDLE_KEY_ALARMREMINDER) ;
		}
	}

	/**
	 * 	hideInput:()
	 *  ──────────────────────────────────
	 * 	@param 		inView	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-22下午5:54:34
	 *	Modifications:	Initiate
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public void hideInput(View inView) {
		try {
			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE) ;
			inputMethodManager.hideSoftInputFromWindow(inView.getWindowToken() , 0) ;
		}
		catch(Exception e) {
		}
	}

	/**
	 * 	cancel:(取消事件回调)
	 *  ──────────────────────────────────
	 * 	@param inView	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-22下午5:55:20
	 *	Modifications:	Initiate
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public void cancel(View inView) {
		if(ClickUtil.isFastDoubleClick()) {
			return ;
		}
		finish() ;
	}
}
