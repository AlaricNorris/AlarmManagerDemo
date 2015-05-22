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

import java.text.ParseException ;
import java.util.ArrayList ;
import java.util.Date ;
import java.util.Iterator ;
import android.app.AlarmManager ;
import android.app.PendingIntent ;
import android.content.Context ;
import android.content.Intent ;
import android.test.AndroidTestCase ;
import android.util.Log ;
import com.example.alarmmanagerdemo.daos.AlarmReminderDAO ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;
import com.example.alarmmanagerdemo.receivers.AlarmReminderReceiver ;

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

	public static final String TAG = "TestTag" ;

	public void testCreateOrUpdate() {
		AlarmReminderEntity mAlarmReminderEntity = initEntity() ;
		setUpAlarm(mAlarmReminderEntity) ;
		save2DB(mAlarmReminderEntity) ;
		AlarmReminderEntity mAlarmReminderEntity2 = initEntity2() ;
		setUpAlarm(mAlarmReminderEntity2) ;
		save2DB(mAlarmReminderEntity2) ;
//		OpenHelperManager.releaseHelper() ;
	}

	/**
	 * 	initEntity2:()
	 *  ──────────────────────────────────
	 * 	@return    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-19	下午5:24:11	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private AlarmReminderEntity initEntity2() {
		AlarmReminderEntity mAlarmReminderEntity = new AlarmReminderEntity() ;
		mAlarmReminderEntity.setId(2) ;
		mAlarmReminderEntity.setTitle("testRepeate2") ;
		mAlarmReminderEntity.setDescription("testRepeateAlarm2") ;
		mAlarmReminderEntity.setTimesperday(2) ;
		mAlarmReminderEntity.setStratdate(new Date()) ;
		mAlarmReminderEntity.setEnddate(new Date(2 * 86400000 + System.currentTimeMillis())) ;
		mAlarmReminderEntity
				.setTriggerAtTimes(AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd_HHmmss
						.format(new Date(10000 + System.currentTimeMillis()))) ;
		mAlarmReminderEntity.setIsOn(AlarmReminderEntity.FLAG_TRUE) ;
		mAlarmReminderEntity.setNeedVibration(AlarmReminderEntity.FLAG_TRUE) ;
		return mAlarmReminderEntity ;
	}

	/**
	 * 	save2DB:()
	 *  ──────────────────────────────────
	 * 	@param mAlarmReminderEntity    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-19	下午5:11:21	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void save2DB(AlarmReminderEntity mAlarmReminderEntity) {
		AlarmReminderDAO alarmReminderDAO = new AlarmReminderDAO(getContext()) ;
		Log.i(TAG , "CorU" + alarmReminderDAO.createOrUpdate(mAlarmReminderEntity)) ;
		Log.i(TAG , "queryAll" + alarmReminderDAO.queryAll()) ;
	}

	/**
	 * 	setUpAlarm:()
	 *  ──────────────────────────────────
	 * 	@param mAlarmReminderEntity    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-19	下午5:11:07	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private void setUpAlarm(AlarmReminderEntity mAlarmReminderEntity) {
		Intent intent = new Intent(getContext() , AlarmReminderReceiver.class) ;
		intent.putExtra("ID" , mAlarmReminderEntity.getId()) ;
		PendingIntent sender = PendingIntent.getBroadcast(getContext() ,
				mAlarmReminderEntity.getId() , intent , 0) ;
		AlarmManager manager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE) ;
		Log.i(TAG , "currentTimeMillis:" + System.currentTimeMillis()) ;
		Date tempDate = new Date(120000 + System.currentTimeMillis()) ;
		Log.i(TAG , "tempDate:" + tempDate.toString() + "\t" + tempDate.getTime()) ;
		try {
			tempDate = AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd_HHmmss
					.parse(mAlarmReminderEntity.getTriggerAtTimes()) ;
		}
		catch(ParseException e) {
			e.printStackTrace() ;
		}
		Log.i(TAG , "tempDate:" + tempDate.toString() + "\t" + tempDate.getTime()) ;
//		manager.set(AlarmManager.RTC_WAKEUP , tempDate.getTime() , sender) ;
		manager.setRepeating(AlarmManager.RTC_WAKEUP , tempDate.getTime() , MINUTES , sender) ;
	}

	public static final long MINUTES = 1000L * 60 * 1 / 2 ;

	/**
	 * 	initEntity:()
	 *  ──────────────────────────────────
	 * 	@return    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-19	下午5:10:42	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	private AlarmReminderEntity initEntity() {
		AlarmReminderEntity mAlarmReminderEntity = new AlarmReminderEntity() ;
		mAlarmReminderEntity.setId(1) ;
		mAlarmReminderEntity.setTitle("testRepeate") ;
		mAlarmReminderEntity.setDescription("testRepeateAlarm") ;
		mAlarmReminderEntity.setTimesperday(1) ;
		mAlarmReminderEntity.setStratdate(new Date()) ;
		mAlarmReminderEntity.setEnddate(new Date(86400000 + System.currentTimeMillis())) ;
		mAlarmReminderEntity
				.setTriggerAtTimes(AlarmReminderEntity.mSimpleDateFormat_yyyyMMdd_HHmmss
						.format(new Date(5000 + System.currentTimeMillis()))) ;
		mAlarmReminderEntity.setIsOn(AlarmReminderEntity.FLAG_TRUE) ;
		mAlarmReminderEntity.setNeedVibration(AlarmReminderEntity.FLAG_TRUE) ;
		return mAlarmReminderEntity ;
	}

	public void queryAll() {
		Log.i("TestTag" , "queryAll" + new AlarmReminderDAO(getContext()).queryAll()) ;
	}

	public void testCancelAlarm() {
		ArrayList<AlarmReminderEntity> mArrayList = new AlarmReminderDAO(getContext()).queryAll() ;
		Log.i(TAG , "mArrayList" + mArrayList.toString()) ;
		Iterator<AlarmReminderEntity> mIterator = mArrayList.iterator() ;
		while(mIterator.hasNext()) {
			AlarmReminderEntity tempAlarmReminderEntity = (AlarmReminderEntity) mIterator.next() ;
			cancelAlarm(tempAlarmReminderEntity) ;
		}
	}

	public void cancelAlarm(AlarmReminderEntity mAlarmReminderEntity) {
		Log.i(TAG , "cancelAlarm" + mAlarmReminderEntity.toString()) ;
		Intent intent = new Intent(getContext() , AlarmReminderReceiver.class) ;
		intent.putExtra("ID" , mAlarmReminderEntity.getId()) ;
		PendingIntent sender = PendingIntent.getBroadcast(getContext() ,
				mAlarmReminderEntity.getId() , intent , 0) ;
		AlarmManager manager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE) ;
		manager.cancel(sender) ;
	}
	
	public void testQueryLatestID() {
		AlarmReminderDAO alarmReminderDAO = new AlarmReminderDAO(getContext()) ;
		Log.i(TAG , "CorU" + alarmReminderDAO.queryLatestId()) ;
		Log.i(TAG , "queryAll" + alarmReminderDAO.queryAll()) ;
	
		
	}
	
}
