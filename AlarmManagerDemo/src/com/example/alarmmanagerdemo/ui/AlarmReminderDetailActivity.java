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

import butterknife.ButterKnife ;
import butterknife.InjectView ;
import com.example.alarmmanagerdemo.R ;
import com.example.alarmmanagerdemo.daos.AlarmReminderDAO ;
import android.app.Activity ;
import android.os.Bundle ;
import android.util.Log ;
import android.widget.TextView ;

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
public class AlarmReminderDetailActivity extends Activity {

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@ Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_alarm_reminder_detail) ;
		Log.i("tag" , getIntent().getIntExtra( ("ID") , - 1) + "detail") ;
		try {
			setTitle(new AlarmReminderDAO(getApplicationContext()).queryByID(
					getIntent().getIntExtra( ("ID") , - 1)).getTitle()) ;
		}
		catch(Exception e) {
		}
		try {
			((TextView) findViewById(R.id.text_detail)).setText(new AlarmReminderDAO(
					getApplicationContext()).queryByID(getIntent().getIntExtra( ("ID") , - 1))
					.toString()) ;
		}
		catch(Exception e) {
		}
	}
}
