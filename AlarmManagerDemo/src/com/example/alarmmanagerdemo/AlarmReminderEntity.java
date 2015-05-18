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
package com.example.alarmmanagerdemo ;

import java.text.ParseException ;
import java.text.SimpleDateFormat ;
import java.util.Date ;
import com.j256.ormlite.field.DatabaseField ;
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
public class AlarmReminderEntity {

	@ DatabaseField ( generatedId = true )
	private Integer id ;

	@ DatabaseField ( columnName = "title" )
	private String title ;

	@ DatabaseField ( columnName = "description" )
	private String description ;

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
	private Integer timesperday ;

	/**
	 * Android System Params
	 */
	@ DatabaseField ( columnName = "needvibration" )
	private Integer needVibration ;

	public static final SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd") ;

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
	public Integer getId() {
		return id ;
	}

	/**
	 *	id
	 *	@param   id    the id to set
	 */
	public void setId(Integer id) {
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
	 * 	stratdate
	 * 	@return  	the stratdate
	 */
	public String getStratdateString() {
		return stratdate ;
	}

	public Date getStratdate() {
		Date mEndDate = null ;
		try {
			mEndDate = mSimpleDateFormat.parse(stratdate) ;
		}
		catch(ParseException e) {
			// TODO Auto-generated catch block
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
		this.stratdate = mSimpleDateFormat.format(inDate) ;
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
			mEndDate = mSimpleDateFormat.parse(enddate) ;
		}
		catch(ParseException e) {
			// TODO Auto-generated catch block
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
		this.enddate = mSimpleDateFormat.format(inDate) ;
	}

	/**
	 * 	timesperday
	 * 	@return  	the timesperday
	 */
	public Integer getTimesperday() {
		return timesperday ;
	}

	/**
	 *	timesperday
	 *	@param   timesperday    the timesperday to set
	 */
	public void setTimesperday(Integer timesperday) {
		this.timesperday = timesperday ;
	}

	/**
	 * 	needVibration
	 * 	@return  	the needVibration
	 */
	public Integer getNeedVibration() {
		return needVibration ;
	}

	/**
	 *	needVibration
	 *	@param   needVibration    the needVibration to set
	 */
	public void setNeedVibration(Integer needVibration) {
		this.needVibration = needVibration ;
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see java.lang.Object#toString()
	 */
	@ Override
	public String toString() {
		return "AlarmReminderEntity [id=" + id + ", title=" + title + ", description="
				+ description + ", stratdate=" + stratdate + ", enddate=" + enddate
				+ ", timesperday=" + timesperday + ", needVibration=" + needVibration
				+ ", mSimpleDateFormat=" + mSimpleDateFormat + "]" ;
	}
}
