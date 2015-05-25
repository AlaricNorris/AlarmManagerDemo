/**
 * 	AlarmReminderDAO.java
 * 	com.example.alarmmanagerdemo.daos
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-18 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午4:42:41	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo.daos ;

import java.sql.SQLException ;
import java.util.ArrayList ;
import android.content.Context ;
import com.example.alarmmanagerdemo.db.AlarmReminderDBOpenHelper ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;
import com.j256.ormlite.dao.Dao ;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus ;
import com.j256.ormlite.stmt.QueryBuilder ;

/**
 *	ClassName:	AlarmReminderDAO
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	Norris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-18		下午4:42:41
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午4:42:41	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class AlarmReminderDAO {

	private Dao<AlarmReminderEntity , Integer> mDao ;

	public AlarmReminderDAO(Context context) {
		try {
			this.mDao = AlarmReminderDBOpenHelper.getHelper(context).getDao(
					AlarmReminderEntity.class) ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
		}
	}

	/**
	 * 	createOrUpdate:()
	 *  ──────────────────────────────────
	 * 	@param inAlarmReminderEntity
	 * 	@return	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-20上午11:17:44
	 *	Modifications:	TODO
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public boolean createOrUpdate(AlarmReminderEntity inAlarmReminderEntity) {
		CreateOrUpdateStatus mCreateOrUpdateStatus = null ;
		try {
			mCreateOrUpdateStatus = mDao.createOrUpdate(inAlarmReminderEntity) ;
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

	/**
	 * 	queryByID:(通过ID查单个)
	 *  ──────────────────────────────────
	 * 	@param inID
	 * 	@return    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-18	下午5:39:11	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public AlarmReminderEntity queryByID(int inID) {
		AlarmReminderEntity mResult = null ;
		try {
			mResult = mDao.queryForId(inID) ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
		}
		return mResult ;
	}

	public int queryLatestId() {
		if(queryAll() == null) {
			return 0 ;
		}
		QueryBuilder<AlarmReminderEntity , Integer> mQueryBuilder = mDao.queryBuilder() ;
		mQueryBuilder.orderBy("id" , false) ;
		AlarmReminderEntity mResult = null ;
		try {
			mResult = mQueryBuilder.queryForFirst() ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
			return 0 ;
		}
		if(mResult == null) {
			return 0 ;
		}
		return mResult.getId() ;
	}

	/**
	 * 	queryAll:(查所有)
	 *  ──────────────────────────────────
	 * 	@return    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-18	下午5:40:02	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public ArrayList<AlarmReminderEntity> queryAll() {
		ArrayList<AlarmReminderEntity> mResult = null ;
		try {
			mResult = (ArrayList<AlarmReminderEntity>) mDao.queryForAll() ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
		}
		return mResult ;
	}

	/**
	 * 	delete:(删除)
	 *  ──────────────────────────────────
	 * 	@param inAlarmReminderEntity
	 * 	@return    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-18	下午5:40:09	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public boolean delete(AlarmReminderEntity inAlarmReminderEntity) {
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

	/**
	 * 	deleteByID:(通过ID删除)
	 *  ──────────────────────────────────
	 * 	@param inID
	 * 	@return    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-18	下午5:40:20	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
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

	public boolean deleteByEntity(AlarmReminderEntity inEntity) {
		int result = - 1 ;
		try {
			result = mDao.delete(inEntity) ;
		}
		catch(SQLException e) {
			e.printStackTrace() ;
		}
		if(result > 0) {
			return true ;
		}
		return false ;
	}
}
