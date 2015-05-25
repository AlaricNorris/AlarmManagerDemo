/**
 * 	DBVersionCode.java
 * 	com.example.alarmmanagerdemo.annotations
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-25 		20144L151
 *	Copyright (c) 2015, TNT All Rights Reserved.
 */
package com.example.alarmmanagerdemo.annotations ;

import java.lang.reflect.Field ;
import java.util.HashMap ;
import java.util.Set ;
import javax.annotation.processing.AbstractProcessor ;
import javax.annotation.processing.RoundEnvironment ;
import javax.lang.model.element.Element ;
import javax.lang.model.element.TypeElement ;

/**
 *	ClassName:	DBVersionCode
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	20144L151		
 *	@contact  	Norris.sly@gmail.com
 *	@version  	Ver 1.0
 *	@since   	I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-25		下午12:05:03
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 * 	Modified By 	20144L151		 2015-5-25下午12:05:03
 *	Modifications:	TODO
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class DBVersionCodeProcessor extends AbstractProcessor {

	/**
	 * 	(non-Javadoc)
	 * 	@see javax.annotation.processing.AbstractProcessor#process(java.util.Set, javax.annotation.processing.RoundEnvironment)
	 */
	@ Override
	public boolean process(Set< ? extends TypeElement> annotations ,
			RoundEnvironment inRoudEnvironment) {
		HashMap<String , String> mHashMap = new HashMap<String , String>() ;
		for(TypeElement tempTypeElement : annotations) {
			for(Element element : inRoudEnvironment.getElementsAnnotatedWith(tempTypeElement)) {
				DatabaseVersionCode methodInfo = element.getAnnotation(DatabaseVersionCode.class) ;
//				map.put(element.getEnclosingElement().toString() , methodInfo.Version()) ;
			}
		}
		return false ;
	}
}
