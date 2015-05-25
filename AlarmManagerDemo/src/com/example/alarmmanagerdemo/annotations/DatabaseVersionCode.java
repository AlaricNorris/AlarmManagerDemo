/**
 * 	DatabaseVersionCode.java
 * 	com.example.alarmmanagerdemo.annotations
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-25 		20144L151
 *	Copyright (c) 2015, TNT All Rights Reserved.
 */
package com.example.alarmmanagerdemo.annotations ;

import java.lang.annotation.Documented ;
import java.lang.annotation.ElementType ;
import java.lang.annotation.Inherited ;
import java.lang.annotation.Retention ;
import java.lang.annotation.RetentionPolicy ;
import java.lang.annotation.Target ;

/**
 *	ClassName:	DatabaseVersionCode
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	20144L151		
 *	@contact  	Norris.sly@gmail.com
 *	@version  	Ver 1.0
 *	@since   	I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-25		下午12:03:31
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 * 	Modified By 	20144L151		 2015-5-25下午12:03:31
 *	Modifications:	TODO
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
@ Documented
@ Retention ( RetentionPolicy.CLASS )
@ Target ( ElementType.FIELD )
@ Inherited
public @ interface DatabaseVersionCode {

	int Version() default 1 ;

	int RandonSeed() default 10000 ;
}
