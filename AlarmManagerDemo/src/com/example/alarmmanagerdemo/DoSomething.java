/**
 * 	DoSomething.java
 * 	com.example.alarmmanagerdemo
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-12 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-12	下午5:18:36	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo ;

import android.app.Activity ;
import android.os.Bundle ;
import android.widget.TextView ;
import com.example.alarmmanagerdemo.daos.AlarmReminderDAO ;
import com.example.alarmmanagerdemo.db.AlarmReminderDBOpenHelper ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;

/**
 *	ClassName:	DoSomething
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	Norris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-12		下午5:18:36
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-12	下午5:18:36	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class DoSomething extends Activity {

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@ Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_dosomething) ;
		try {
			setTitle(new AlarmReminderDAO(getApplicationContext()).queryByID(
					getIntent().getIntExtra( ("ID") , - 1)).getTitle()) ;
		}
		catch(Exception e) {
		}
		try {
			((TextView) findViewById(R.id.text_do)).setText(new AlarmReminderDAO(
					getApplicationContext()).queryByID(getIntent().getIntExtra( ("ID") , - 1))
					.getDescription()) ;
		}
		catch(Exception e) {
		}
	}
}
