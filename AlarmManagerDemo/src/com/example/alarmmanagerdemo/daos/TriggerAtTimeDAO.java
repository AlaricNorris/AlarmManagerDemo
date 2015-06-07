/**
 * 	TriggerAtTimeDAO.java
 * 	com.example.alarmmanagerdemo.daos
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-6-7 		AlaricNorris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 */
package com.example.alarmmanagerdemo.daos ;

import java.sql.SQLException ;
import java.util.ArrayList ;
import java.util.List ;
import android.content.Context ;
import com.example.alarmmanagerdemo.db.AlarmReminderDBOpenHelper ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;
import com.example.alarmmanagerdemo.entities.TriggerAtTimeEntity ;
import com.j256.ormlite.dao.Dao ;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus ;
import com.j256.ormlite.stmt.DeleteBuilder ;

/**
 *	ClassName:	TriggerAtTimeDAO
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	AlaricNorris		
 *	@contact  	Norris.sly@gmail.com
 *	@version  	Ver 1.0
 *	@since   	I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-6-7		下午1:07:02
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 * 	Modified By 	AlaricNorris		 2015-6-7下午1:07:02
 *	Modifications:	TODO
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class TriggerAtTimeDAO {

	private Dao<TriggerAtTimeEntity , Integer> mDao ;

	public TriggerAtTimeDAO(Context context) {
		try {
			this.mDao = AlarmReminderDBOpenHelper.getHelper(context).getDao(
					TriggerAtTimeEntity.class) ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
		}
	}

	public boolean createOrUpdate(TriggerAtTimeEntity inEntity) {
		CreateOrUpdateStatus mCreateOrUpdateStatus = null ;
		try {
			mCreateOrUpdateStatus = mDao.createOrUpdate(inEntity) ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
			return false ;
		}
		if(mCreateOrUpdateStatus == null) {
			return false ;
		}
		if(mCreateOrUpdateStatus.getNumLinesChanged() > 0) {
			return true ;
		}
		return false ;
	}

	public TriggerAtTimeEntity queryByID(int inID) {
		TriggerAtTimeEntity mResult = null ;
		try {
			mResult = mDao.queryForId(inID) ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
		}
		return mResult ;
	}

	public List<TriggerAtTimeEntity> queryByForeignID(int inForeignID) {
		List<TriggerAtTimeEntity> mResult = null ;
		try {
			mResult = mDao.queryBuilder().where()
					.eq(TriggerAtTimeEntity.COLUMN_NAME_Reminder_ID , inForeignID).query() ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
		}
		return mResult ;
	}

	public ArrayList<TriggerAtTimeEntity> queryAll() {
		ArrayList<TriggerAtTimeEntity> mResult = null ;
		try {
			mResult = (ArrayList<TriggerAtTimeEntity>) mDao.queryForAll() ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
		}
		return mResult ;
	}

	public boolean delete(TriggerAtTimeEntity inAlarmReminderEntity) {
		int result = - 1 ;
		try {
			result = mDao.delete(inAlarmReminderEntity) ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
		}
		if(result > 0) {
			return true ;
		}
		return false ;
	}

	public boolean deleteByID(int inID) {
		int result = - 1 ;
		try {
			result = mDao.deleteById(inID) ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
		}
		if(result > 0) {
			return true ;
		}
		return false ;
	}

	public boolean deleteByForeignID(int inForeignID) {
		List<TriggerAtTimeEntity> result = null ;
		try {
			result = mDao.deleteBuilder().where()
					.eq(TriggerAtTimeEntity.COLUMN_NAME_Reminder_ID , inForeignID).query() ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
		}
		if(result != null) {
			return true ;
		}
		return false ;
	}
}
