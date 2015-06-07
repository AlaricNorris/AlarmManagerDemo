/**
 * 	AlarmReminderEntity.java
 * 	com.example.alarmmanagerdemo
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-18 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午4:04:47	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo.entities ;

import java.io.Serializable ;
import java.text.ParseException ;
import java.text.SimpleDateFormat ;
import java.util.Date ;
import com.example.alarmmanagerdemo.db.AlarmReminderDBOpenHelper ;
import com.example.alarmmanagerdemo.utils.TimeConstants ;
import com.j256.ormlite.dao.ForeignCollection ;
import com.j256.ormlite.field.DatabaseField ;
import com.j256.ormlite.field.ForeignCollectionField ;
import com.j256.ormlite.table.DatabaseTable ;

/**
 *	ClassName:	AlarmReminderEntity
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	Norris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-18		下午4:04:47
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-18	下午4:04:47	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
@ DatabaseTable ( tableName = "alarm_reminders" )
public class AlarmReminderEntity implements Serializable {

	/**
	 * 	TODO
	 * 	long			:		serialVersionUID	
	 * 	@since Ver 1.0
	 */
	private static final long serialVersionUID = 1L ;

	@ DatabaseField ( generatedId = true )
	private int id ;

	/**
	 * 	标题
	 * 	String			:		title	
	 * 	@since Ver 1.0
	 */
	@ DatabaseField ( columnName = "title" )
	private String title ;

	/**
	 * 	描述
	 * 	String			:		description	
	 * 	@since Ver 1.0
	 */
	@ DatabaseField ( columnName = "description" )
	private String description ;

	/**
	 * 	成员
	 * 	String			:		member	
	 * 	@since Ver 1.0
	 */
	@ DatabaseField ( columnName = "member" )
	private String member ;

	/**
	 * 	药物
	 * 	String			:		medicine	
	 * 	@since Ver 1.0
	 */
	@ DatabaseField ( columnName = "medicine" )
	private String medicine ;

	/**
	 * 	开始日期 
	 * 	formatter  yyyy-MM-dd
	 * 	String			:		mDate_Start	
	 * 	@since Ver 1.0
	 */
	@ DatabaseField ( columnName = "startdate" )
	private String stratdate ;

	/**
	 * 	结束日期
	 * 	String			:		mDate_End	
	 * 	@since Ver 1.0
	 */
	@ DatabaseField ( columnName = "enddate" )
	private String enddate ;

	@ DatabaseField ( columnName = "timesperday" )
	private int timesperday ;

	/**
	 * 	在何时触发
	 * 	使用Json存储
	 * 	String			:		triggerAtTime	
	 * 	@since {@link AlarmReminderDBOpenHelper#DATABASE_VERSION}
	 */
	@ DatabaseField ( columnName = "triggerattimes" )
	private String triggerAtTime ;

	@ ForeignCollectionField ( columnName = "trigger_at_times" )
	private ForeignCollection<TriggerAtTimeEntity> atTimeEntities ;

	/**
	 * 	时间间隔
	 * 	long			:		duration	
	 * 	@since {@link AlarmReminderDBOpenHelper#DATABASE_VERSION}
	 * 	@see {@link TimeConstants#HOUR}
	 */
	@ DatabaseField ( columnName = "duration" )
	private long duration ;

	/**
	 * 	1 	on
	 * 	0 	off
	 * 	int			:		isOn	
	 * 	@since {@link AlarmReminderDBOpenHelper#DATABASE_VERSION}
	 */
	@ DatabaseField ( columnName = "isOn" )
	private int isOn ;

	public static final int FLAG_TRUE = 0x0001 ;

	public static final int FLAG_FALSE = 0x0000 ;

	/**
	 * Android System Params
	 */
	/**
	 * 	1 	need
	 *  0	noneed
	 * 	int			:		needVibration	
	 * 	@since {@link AlarmReminderDBOpenHelper#DATABASE_VERSION}
	 */
	@ DatabaseField ( columnName = "needvibration" )
	private int needVibration ;

	/**
	 * 	日期格式化工具   yyyy-MM-dd HH:mm:ss
	 * 	SimpleDateFormat			:		mSimpleDateFormat_yyyyMMdd_HHmmss	
	 * 	@since Ver 1.0
	 */
	public static final SimpleDateFormat mSimpleDateFormat_yyyyMMdd_HHmmss = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss") ;

	/**
	 * 	日期格式化工具   yyyy-MM-dd 
	 * 	SimpleDateFormat			:		mSimpleDateFormat_yyyyMMdd	
	 * 	@since Ver 1.0
	 */
	public static final SimpleDateFormat mSimpleDateFormat_yyyyMMdd = new SimpleDateFormat(
			"yyyy-MM-dd") ;

	/**
	 * 	日期格式化工具   HH:mm:ss
	 * 	SimpleDateFormat			:		mSimpleDateFormat_HHmmss	
	 * 	@since Ver 1.0
	 */
	public static final SimpleDateFormat mSimpleDateFormat_HHmmss = new SimpleDateFormat("HH:mm:ss") ;

	/**
	 * 	日期格式化工具   HH:mm
	 * 	SimpleDateFormat			:		mSimpleDateFormat_HHmm	
	 * 	@since Ver 1.0
	 */
	public static final SimpleDateFormat mSimpleDateFormat_HHmm = new SimpleDateFormat("HH:mm") ;

	/**
	 * 	Creates a new instance of AlarmReminderEntity.
	 */
	public AlarmReminderEntity() {
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
	 * 	title
	 * 	@return  	the title
	 */
	public String getTitle() {
		return title ;
	}

	/**
	 *	title
	 *	@param   title    the title to set
	 */
	public void setTitle(String title) {
		this.title = title ;
	}

	/**
	 * 	description
	 * 	@return  	the description
	 */
	public String getDescription() {
		return description ;
	}

	/**
	 *	description
	 *	@param   description    the description to set
	 */
	public void setDescription(String description) {
		this.description = description ;
	}

	/**
	 * 	member
	 * 	@return  	the member
	 */
	public String getMember() {
		return member ;
	}

	/**
	 *	member
	 *	@param   member    the member to set
	 */
	public void setMember(String member) {
		this.member = member ;
	}

	/**
	 * 	medicine
	 * 	@return  	the medicine
	 */
	public String getMedicine() {
		return medicine ;
	}

	/**
	 *	medicine
	 *	@param   medicine    the medicine to set
	 */
	public void setMedicine(String medicine) {
		this.medicine = medicine ;
	}

	/**
	 * 	stratdate
	 * 	@return  	the stratdate
	 */
	public String getStratdateString() {
		return stratdate ;
	}

	public Date getStratdate() {
		Date mEndDate = null ;
		try {
			mEndDate = mSimpleDateFormat_yyyyMMdd.parse(stratdate) ;
		}
		catch(ParseException e) {
			e.printStackTrace() ;
		}
		return mEndDate ;
	}

	/**
	 *	stratdate
	 *	@param   stratdate    the stratdate to set
	 */
	public void setStratdate(String stratdate) {
		this.stratdate = stratdate ;
	}

	public void setStratdate(Date inDate) {
		this.stratdate = mSimpleDateFormat_yyyyMMdd.format(inDate) ;
	}

	/**
	 * 	enddate
	 * 	@return  	the enddate
	 */
	public String getEnddateString() {
		return enddate ;
	}

	public Date getEnddate() {
		Date mEndDate = null ;
		try {
			mEndDate = mSimpleDateFormat_yyyyMMdd.parse(enddate) ;
		}
		catch(ParseException e) {
			e.printStackTrace() ;
		}
		return mEndDate ;
	}

	/**
	 *	enddate
	 *	@param   enddate    the enddate to set
	 */
	public void setEnddate(String enddate) {
		this.enddate = enddate ;
	}

	public void setEnddate(Date inDate) {
		this.enddate = mSimpleDateFormat_yyyyMMdd.format(inDate) ;
	}

	/**
	 * 	timesperday
	 * 	@return  	the timesperday
	 */
	public int getTimesperday() {
		return timesperday ;
	}

	/**
	 *	timesperday
	 *	@param   timesperday    the timesperday to set
	 */
	public void setTimesperday(int timesperday) {
		this.timesperday = timesperday ;
	}

	/**
	 * 	triggerAtTime
	 * 	@return  	the triggerAtTime
	 */
	public String getTriggerAtTimeString() {
		return triggerAtTime ;
	}

	public Date getTriggerAtTime() {
		Date mDate = new Date() ;
		try {
			mDate = mSimpleDateFormat_HHmmss.parse(triggerAtTime) ;
		}
		catch(ParseException e) {
			e.printStackTrace() ;
		}
		return mDate ;
	}

	/**
	 *	triggerAtTime
	 *	@param   triggerAtTime    the triggerAtTimes to set
	 */
	public void setTriggerAtTime(String triggerAtTime) {
		this.triggerAtTime = triggerAtTime ;
	}

	public void setTriggerAtTime(Date inDate) {
		this.triggerAtTime = mSimpleDateFormat_HHmmss.format(inDate) ;
	}

	/**
	 * 	atTimeEntities
	 * 	@return  	the atTimeEntities
	 */
	public ForeignCollection<TriggerAtTimeEntity> getAtTimeEntities() {
		return atTimeEntities ;
	}

	/**
	 *	atTimeEntities
	 *	@param   atTimeEntities    the atTimeEntities to set
	 */
	public void setAtTimeEntities(ForeignCollection<TriggerAtTimeEntity> atTimeEntities) {
		this.atTimeEntities = atTimeEntities ;
	}

	/**
	 * 	duration
	 * 	@return  	the duration
	 */
	public long getDuration() {
		return duration ;
	}

	/**
	 *	duration
	 *	@param   duration    the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration ;
	}

	/**
	 * 	isOn
	 * 	@return  	the isOn
	 */
	public int getIsOn() {
		return isOn ;
	}

	public boolean isOn() {
		return isOn == 1 ? true : false ;
	}

	/**
	 *	isOn
	 *	@param   isOn    the isOn to set
	 */
	public void setIsOn(int isOn) {
		this.isOn = isOn ;
	}

	/**
	 * 	needVibration
	 * 	@return  	the needVibration
	 */
	public int getNeedVibration() {
		return needVibration ;
	}

	/**
	 *	needVibration
	 *	@param   needVibration    the needVibration to set
	 */
	public void setNeedVibration(int needVibration) {
		this.needVibration = needVibration ;
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see java.lang.Object#toString()
	 */
	@ Override
	public String toString() {
		String mTriggerAtTimesString = "" ;
		for(TriggerAtTimeEntity tempEntity : atTimeEntities) {
			mTriggerAtTimesString += tempEntity.toSimpleString() ;
		}
		return "AlarmReminderEntity [id=" + id + ", title=" + title + ", description="
				+ description + ", member=" + member + ", medicine=" + medicine + ", stratdate="
				+ stratdate + ", enddate=" + enddate + ", timesperday=" + timesperday
				+ ", triggerAtTime=" + triggerAtTime + ", atTimeEntities=" + mTriggerAtTimesString
				+ ", duration=" + duration + ", isOn=" + isOn + ", needVibration=" + needVibration
				+ "]" ;
	}
}
