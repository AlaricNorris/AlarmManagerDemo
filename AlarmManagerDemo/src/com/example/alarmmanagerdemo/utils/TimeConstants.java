/**
 * 	TimeConstants.java
 * 	com.example.alarmmanagerdemo.utils
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-20 		20144L151
 *	Copyright (c) 2015, TNT All Rights Reserved.
 */
package com.example.alarmmanagerdemo.utils ;

import java.util.List ;

/**
 *	ClassName:	TimeConstants
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	20144L151		
 *	@contact  	Norris.sly@gmail.com
 *	@version  	Ver 1.0
 *	@since   	I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-20		上午11:00:22
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 * 	Modified By 	20144L151		 2015-5-20上午11:00:22
 *	Modifications:	TODO
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class TimeConstants {

	public static enum TimeUnit {
		Unit_SECOND("秒" , SECOND) , Unit_MINUTE("分" , MINUTE) , Unit_HOUR("时" , HOUR) , Unit_DAY(
				"天" , DAY) ;

		/**
		 * 	单位名称
		 * 	String			:		Unit_Name	
		 * 	@since Ver 1.0
		 */
		private String Unit_Name ;

		/**
		 * 	单位时间  millionseconds
		 * 	long			:		SeedMillis	
		 * 	@since Ver 1.0
		 */
		private long SeedMillis ;

		/**
		 * 	Creates a new instance of TimeUnit.
		 * 	@param seedMillis
		 */
		private TimeUnit(long seedMillis) {
			this.SeedMillis = seedMillis ;
		}

		/**
		 * 	Creates a new instance of TimeUnit.
		 * 	@param unit_Name
		 * 	@param seedMillis
		 */
		private TimeUnit(String unit_Name , long seedMillis) {
			Unit_Name = unit_Name ;
			SeedMillis = seedMillis ;
		}

		/**
		 * 	unit_Name
		 * 	@return  	the unit_Name
		 */
		public String getUnit_Name() {
			return Unit_Name ;
		}

		/**
		 *	unit_Name
		 *	@param   unit_Name    the unit_Name to set
		 */
		public void setUnit_Name(String unit_Name) {
			Unit_Name = unit_Name ;
		}

		/**
		 * 	seedMillis
		 * 	@return  	the seedMillis
		 */
		public long getSeedMillis() {
			return SeedMillis ;
		}

		/**
		 *	seedMillis
		 *	@param   seedMillis    the seedMillis to set
		 */
		public void setSeedMillis(long seedMillis) {
			this.SeedMillis = seedMillis ;
		}

		/**
		 * 	getTimeUnitBySeed:(根据单位时间获得enum对象)
		 *  ──────────────────────────────────
		 * 	@param 		seedMillis
		 * 	@return	
		 *	@version	Ver 1.0	
		 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
		 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
		 * 	Modified By 	20144L151		 2015-5-25下午6:14:04
		 *	Modifications:	TODO
		 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
		 */
		public TimeUnit getTimeUnitBySeed(long seedMillis) {
			if(seedMillis == DAY) {
				return Unit_DAY ;
			}
			if(seedMillis == HOUR) {
				return Unit_HOUR ;
			}
			if(seedMillis == MINUTE) {
				return Unit_MINUTE ;
			}
			if(seedMillis == SECOND) {
				return Unit_SECOND ;
			}
			return null ;
		}

		/**
		 * 	(non-Javadoc)
		 * 	@see java.lang.Enum#toString()
		 */
		@ Override
		public String toString() {
			return Unit_Name + SeedMillis ;
		}
	}

	public static final long SECOND = 1000 ;

	public static final long MINUTE = SECOND * 60 ;

	public static final long HOUR = MINUTE * 60 ;

	public static final long DAY = HOUR * 24 ;

	public static final long WEEK = DAY * 7 ;

	public static final long MINUTES_SEMI = MINUTE / 2 ;

	public static final long MINUTES_2 = MINUTE * 2 ;

	public static final long MINUTES_3 = MINUTE * 3 ;

	public static final long MINUTES_5 = MINUTE * 5 ;

	public static final long MINUTES_10 = MINUTE * 10 ;

	/**
	 * 	converUnitReturnString:()
	 *  ──────────────────────────────────
	 * 	@param 		inMillionSeconds		传入的总时间数millionseconds
	 * 	@param 		inUnit					单位enum 对象
	 * 	@return	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-25下午6:15:07
	 *	Modifications:	TODO
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public static String converUnitReturnString(long inMillionSeconds , TimeUnit inUnit) {
		switch(inUnit) {
			case Unit_SECOND :
				break ;
			case Unit_MINUTE :
				break ;
			case Unit_HOUR :
				break ;
			case Unit_DAY :
				break ;
			default :
				break ;
		}
		if(inMillionSeconds < inUnit.getSeedMillis()) {
			return (double) ((double) inMillionSeconds / (double) inUnit.getSeedMillis())
					+ inUnit.getUnit_Name() ;
		}
		return (inMillionSeconds / inUnit.getSeedMillis()) + inUnit.getUnit_Name() ;
	}

	/**
	 * 	autoPickUnit:()
	 *  ──────────────────────────────────
	 * 	@param inCurrentValue
	 * 	@param inTimeUnit
	 * 	@return	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	20144L151		 2015-5-25下午6:23:13
	 *	Modifications:	TODO
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public static List autoPickUnit(long inCurrentValue , TimeUnit inTimeUnit) {
		return null ;
	}
}
