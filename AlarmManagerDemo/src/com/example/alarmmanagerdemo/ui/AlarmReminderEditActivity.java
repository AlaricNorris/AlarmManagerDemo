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

import com.example.alarmmanagerdemo.R ;
import com.example.alarmmanagerdemo.R.id ;
import com.example.alarmmanagerdemo.R.layout ;
import android.app.Activity ;
import android.os.Bundle ;
import android.widget.LinearLayout ;

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

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@ Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_alarm_reminder_home) ;
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
		mLinearLayout = (LinearLayout) findViewById(R.id.action_settings) ;
	}

	private Bundle mBundle ;

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
			editFlag = mBundle.getBoolean("EditFlag") ;
		}
	}
}
