/**
 * 	AlarmReminderDBOpenHelper.java
 * 	com.example.alarmmanagerdemo
 * 	Function： 	提醒功能数据库操作类 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-18 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午4:29:35	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo.db ;

import java.sql.SQLException ;
import android.content.Context ;
import android.database.sqlite.SQLiteDatabase ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper ;
import com.j256.ormlite.dao.Dao ;
import com.j256.ormlite.support.ConnectionSource ;
import com.j256.ormlite.table.TableUtils ;

/**
 *	ClassName:	AlarmReminderDBOpenHelper
 *	Function: 	数据库操作类
 *	@author   	Norris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-18		下午4:29:35
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午4:29:35	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class AlarmReminderDBOpenHelper extends OrmLiteSqliteOpenHelper {

	public static final String TABLE_NAME = "alarm_reminders.db" ;

	/**
	 * 	DB Version Code  
	 * 	TODO Need modification when upgrade
	 * 	int			:		DATABASE_VERSION	
	 * 	@since Ver 1.0
	 */
	public static final int DATABASE_VERSION = 3 ;

	/**
	 * 	Creates a new instance of AlarmReminderDBOpenHelper.
	 * 	@param context
	 * 	@param databaseName
	 * 	@param factory
	 * 	@param databaseVersion
	 */
	public AlarmReminderDBOpenHelper(Context context) {
		super(context , TABLE_NAME , null , DATABASE_VERSION) ;
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase, com.j256.ormlite.support.ConnectionSource)
	 */
	@ Override
	public void onCreate(SQLiteDatabase arg0 , ConnectionSource arg1) {
		try {
			TableUtils.createTable(connectionSource , AlarmReminderEntity.class) ;
		}
		catch(Exception e) {
			e.printStackTrace() ;
		}
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, com.j256.ormlite.support.ConnectionSource, int, int)
	 */
	@ Override
	public void onUpgrade(SQLiteDatabase database , ConnectionSource connectionSource ,
			int oldVersion , int newVersion) {
		try {
			TableUtils.dropTable(connectionSource , AlarmReminderEntity.class , true) ;
			onCreate(database , connectionSource) ;
		}
		catch(Exception e) {
			e.printStackTrace() ;
		}
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see android.database.sqlite.SQLiteOpenHelper#onDowngrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@ Override
	public void onDowngrade(SQLiteDatabase database , int oldVersion , int newVersion) {
		try {
			TableUtils.dropTable(connectionSource , AlarmReminderEntity.class , true) ;
			onCreate(database , connectionSource) ;
		}
		catch(Exception e) {
			e.printStackTrace() ;
		}
	}

	private static AlarmReminderDBOpenHelper mInstance ;

	/** 
	 * 单例获取该Helper 
	 *  
	 * @param context 
	 * @return 
	 */
	public static synchronized AlarmReminderDBOpenHelper getHelper(Context context) {
		if(mInstance == null) {
			synchronized(AlarmReminderDBOpenHelper.class) {
				if(mInstance == null)
					mInstance = new AlarmReminderDBOpenHelper(context) ;
			}
		}
		return mInstance ;
	}

	/**
	 *	alarmReminderDAO	每张表对于一个 
	 * 	Dao<AlarmReminderEntity,Integer>			:		alarmReminderDAO	
	 * 	@since Ver 1.0
	 */
	private Dao<AlarmReminderEntity , Integer> alarmReminderDAO ;

	public Dao<AlarmReminderEntity , Integer> getAlarmReminderDAO() throws SQLException {
		if(alarmReminderDAO == null) {
			alarmReminderDAO = getDao(AlarmReminderEntity.class) ;
		}
		return alarmReminderDAO ;
	}

	/** 
	 * 释放资源 
	 */
	@ Override
	public void close() {
		super.close() ;
		alarmReminderDAO = null ;
	}
}
