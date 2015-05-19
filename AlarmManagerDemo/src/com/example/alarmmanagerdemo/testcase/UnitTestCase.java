/**
 * 	UnitTestCase.java
 * 	com.example.alarmmanagerdemo.testcase
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-18 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午5:27:02	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo.testcase ;

import java.util.Date ;
import android.test.AndroidTestCase ;
import android.util.Log ;
import com.example.alarmmanagerdemo.daos.AlarmReminderDAO ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;
import com.j256.ormlite.android.apptools.OpenHelperManager ;

/**
 *	ClassName:	UnitTestCase
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	Norris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-18		下午5:27:02
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午5:27:02	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class UnitTestCase extends AndroidTestCase {

	public void testCreateOrUpdate() {
		AlarmReminderEntity mAlarmReminderEntity = new AlarmReminderEntity() ;
		mAlarmReminderEntity.setTitle("test") ;
		mAlarmReminderEntity.setDescription("TestCase") ;
		mAlarmReminderEntity.setTimesperday(3) ;
		mAlarmReminderEntity.setStratdate(new Date()) ;
		mAlarmReminderEntity.setEnddate(new Date(2015 - 1900 , 6 - 1 , 11)) ;
		AlarmReminderDAO alarmReminderDAO = new AlarmReminderDAO(getContext()) ;
		Log.i("TestTag" , "CorU" + alarmReminderDAO.createOrUpdate(mAlarmReminderEntity)) ;
		Log.i("TestTag" , "queryAll" + alarmReminderDAO.queryAll()) ;
		OpenHelperManager.releaseHelper() ;
	}

	public void queryAll() {
		Log.i("TestTag" , "queryAll" + new AlarmReminderDAO(getContext()).queryAll()) ;
	}
}
