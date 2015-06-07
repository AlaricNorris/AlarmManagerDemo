/**
 * 	TriggerAtTimeEntity.java
 * 	com.example.alarmmanagerdemo.entities
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-6-6 		AlaricNorris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 */
package com.example.alarmmanagerdemo.entities ;

import java.io.Serializable ;
import java.text.ParseException ;
import java.util.Date ;
import com.example.alarmmanagerdemo.db.AlarmReminderDBOpenHelper ;
import com.j256.ormlite.field.DatabaseField ;
import com.j256.ormlite.table.DatabaseTable ;

/**
 *	ClassName:	TriggerAtTimeEntity
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	AlaricNorris		
 *	@contact  	Norris.sly@gmail.com
 *	@version  	Ver 1.0
 *	@since   	I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-6-6		下午12:51:20
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 * 	Modified By 	AlaricNorris		 2015-6-6下午12:51:20
 *	Modifications:	TODO
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
@ DatabaseTable ( tableName = "alarm_trigger_at_times" )
public class TriggerAtTimeEntity implements Serializable {

	public static final String COLUMN_NAME_Reminder_ID = "reminder_id" ;

	public static final String COLUMN_NAME_TriggerAtTime = "triggerattime" ;

	/**
	 * 	TODO
	 * 	long			:		serialVersionUID	
	 * 	@since Ver 1.0
	 */
	private static final long serialVersionUID = 1L ;

	@ DatabaseField ( generatedId = true )
	private int id ;

	@ DatabaseField ( foreign = true , foreignAutoRefresh = true , columnName = COLUMN_NAME_Reminder_ID , canBeNull = false )
	private AlarmReminderEntity alarmReminderEntity ;

	/**
	 * 	在何时触发
	 * 	String			:		triggerAtTime	
	 * 	@since {@link AlarmReminderDBOpenHelper#DATABASE_VERSION}
	 */
	@ DatabaseField ( columnName = COLUMN_NAME_TriggerAtTime )
	private String triggerAtTime ;

	/**
	 * 	Creates a new instance of TriggerAtTimeEntity.
	 */
	public TriggerAtTimeEntity() {
		super() ;
	}

	/**
	 * 	id
	 * 	@return  	the id
	 */
	public int getId() {
		return id ;
	}

	/**
	 *	id
	 *	@param   id    the id to set
	 */
	public void setId(int id) {
		this.id = id ;
	}

	/**
	 * 	alarmReminderEntity
	 * 	@return  	the alarmReminderEntity
	 */
	public AlarmReminderEntity getAlarmReminderEntity() {
		return alarmReminderEntity ;
	}

	/**
	 *	alarmReminderEntity
	 *	@param   alarmReminderEntity    the alarmReminderEntity to set
	 */
	public void setAlarmReminderEntity(AlarmReminderEntity alarmReminderEntity) {
		this.alarmReminderEntity = alarmReminderEntity ;
	}

	/**
	 * 	triggerAtTime
	 * 	@return  	the triggerAtTime
	 */
	public String getTriggerAtTimeString() {
		return triggerAtTime ;
	}

	public Date getTriggerAtTime() {
		Date mDate = null ;
		try {
			mDate = AlarmReminderEntity.mSimpleDateFormat_HHmm.parse(triggerAtTime) ;
		}
		catch(ParseException e) {
			e.printStackTrace() ;
		}
		return mDate ;
	}

	/**
	 *	triggerAtTime
	 *	@param   triggerAtTime    the triggerAtTime to set
	 */
	public void setTriggerAtTime(String triggerAtTime) {
		this.triggerAtTime = triggerAtTime ;
	}

	public void setTriggerAtTime(Date inDate) {
		this.triggerAtTime = AlarmReminderEntity.mSimpleDateFormat_HHmm.format(inDate) ;
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see java.lang.Object#toString()
	 */
	@ Override
	public String toString() {
		return "TriggerAtTimeEntity [id=" + id + ", alarmReminderEntity=" + alarmReminderEntity
				+ ", triggerAtTime=" + triggerAtTime + "]" ;
	}

	/**
	 * 	toSimpleString:()
	 *  ──────────────────────────────────	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	AlaricNorris		 2015-6-7下午4:04:44
	 *	Modifications:	TODO
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public String toSimpleString() {
		return "TriggerAtTimeEntity [id=" + id + ", alarmReminderEntity="
				+ alarmReminderEntity.getId() + ", triggerAtTime=" + triggerAtTime + "]" ;
	}
}
