/**
 * 	NotificationEntity.java
 * 	com.example.alarmmanagerdemo
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-12 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-12	下午3:53:14	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo.entities ;

import java.io.Serializable ;

/**
 *	ClassName:	NotificationEntity
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	Norris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-12		下午3:53:14
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-12	下午3:53:14	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class NotificationEntity implements Serializable {

	/**
	 * 	TODO
	 * 	long			:		serialVersionUID	
	 * 	@since Ver 1.0
	 */
	private static final long serialVersionUID = 1L ;

	public int ID ;

	public String NotificationTitle ;

	public String SubTitle ;

	public String Content ;

	/**
	 * 	Creates a new instance of NotificationEntity.
	 * 	@param iD
	 * 	@param notificationTitle
	 * 	@param subTitle
	 * 	@param content
	 */
	public NotificationEntity(int iD , String notificationTitle , String subTitle , String content) {
		super() ;
		ID = iD ;
		NotificationTitle = notificationTitle ;
		SubTitle = subTitle ;
		Content = content ;
	}

	/**
	 * 	Creates a new instance of NotificationEntity.
	 * 	@param notificationTitle
	 * 	@param subTitle
	 * 	@param content
	 */
	public NotificationEntity(String notificationTitle , String subTitle , String content) {
		super() ;
		NotificationTitle = notificationTitle ;
		SubTitle = subTitle ;
		Content = content ;
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see java.lang.Object#toString()
	 */
	@ Override
	public String toString() {
		return "NotificationEntity [NotificationTitle=" + NotificationTitle + ", SubTitle="
				+ SubTitle + ", Content=" + Content + "]" ;
	}
}
