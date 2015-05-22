/**
 * 	AlarmReminderApplication.java
 * 	com.example.alarmmanagerdemo
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-22 		20144L151
 *	Copyright (c) 2015, TNT All Rights Reserved.
 */
package com.example.alarmmanagerdemo ;

import butterknife.ButterKnife ;
import android.app.Application ;
import android.widget.Toast ;

/**
 *	ClassName:	AlarmReminderApplication
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	20144L151		
 *	@contact  	Norris.sly@gmail.com
 *	@version  	Ver 1.0
 *	@since   	I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-22		下午2:30:59
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 * 	Modified By 	20144L151		 2015-5-22下午2:30:59
 *	Modifications:	TODO
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class AlarmReminderApplication extends Application {

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Application#onCreate()
	 */
	@ Override
	public void onCreate() {
		super.onCreate() ;
		ButterKnife.setDebug(BuildConfig.DEBUG) ;
	}
}
