/**
 * 	ClickUtil.java
 * 	com.alaric.norris.game.ieue.utils
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2013-12-15 		AlaricNorris
 *	Copyright (c) 2013, TNT All Rights Reserved.
 */
package com.example.alarmmanagerdemo ;
/**
 *	ClassName:	ClickUtil
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	AlaricNorris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2013		2013-12-15		下午4:39:29
 *	@see 	 	
 */
public class ClickUtil {

	/**
	 * 	连击时差100毫秒
	 * 	long			:		DELTA_100	
	 * 	@since Ver 1.0
	 */
	public static final long DELTA_100 = 100 ;

	/**
	 * 	连击时差200毫秒
	 * 	long			:		DELTA_200	
	 * 	@since Ver 1.0
	 */
	public static final long DELTA_200 = 200 ;

	/**
	 * 	连击时差300毫秒
	 * 	long			:		DELTA_300	
	 * 	@since Ver 1.0
	 */
	public static final long DELTA_300 = 300 ;

	/**
	 * 	连击时差400毫秒
	 * 	long			:		DELTA_400	
	 * 	@since Ver 1.0
	 */
	public static final long DELTA_400 = 400 ;

	/**
	 * 	连击时差500毫秒
	 * 	long			:		DELTA_500	
	 * 	@since Ver 1.0
	 */
	public static final long DELTA_500 = 500 ;

	/**
	 * 	连击时差600毫秒
	 * 	long			:		DELTA_600	
	 * 	@since Ver 1.0
	 */
	public static final long DELTA_600 = 600 ;

	/**
	 * 	连击时差700毫秒
	 * 	long			:		DELTA_700	
	 * 	@since Ver 1.0
	 */
	public static final long DELTA_700 = 700 ;

	/**
	 * 	连击时差800毫秒
	 * 	long			:		DELTA_800	
	 * 	@since Ver 1.0
	 */
	public static final long DELTA_800 = 800 ;

	/**
	 * 	最后点击时间
	 * 	long			:		mLastClickMillis	
	 * 	@since Ver 1.0
	 */
	private static long mLastClickMillis ;

	/**
	 * 	isFastDoubleClick:(默认判断连续点击，默认延时100毫秒)
	 *  ──────────────────────────────────
	 * 	@return	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 * 	@see		{@link #isFastDoubleClick(long)}
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	AlaricNorris		 2014-11-9下午10:34:19
	 *	Modifications:	modified
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public static boolean isFastDoubleClick() {
		long mCurrentMillis = System.currentTimeMillis() ;
		long mDeltaMillis = mCurrentMillis - mLastClickMillis ;
		mLastClickMillis = mCurrentMillis ;
		if(0 < mDeltaMillis && mDeltaMillis < DELTA_100) {
			return true ;
		}
		return false ;
	}

	/**
	 * 	isFastDoubleClick:(自定义判断连击  根据传入延时参数来决定有无连击)
	 *  ──────────────────────────────────
	 * 	@param 		inDeltaTime
	 * 	@return	
	 *	@version	Ver 1.0	
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　
	 * 	@see		
	 * 	{@link #DELTA_100}
	 * 	{@link #DELTA_200}
	 * 	{@link #DELTA_300}
	 * 	{@link #DELTA_400}
	 * 	{@link #DELTA_500}
	 * 	{@link #DELTA_600}
	 * 	{@link #DELTA_700}
	 * 	{@link #DELTA_800}
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 * 	Modified By 	AlaricNorris		 2014-11-9下午10:36:52
	 *	Modifications:	 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public static boolean isFastDoubleClick(long inDeltaTime) {
		long mCurrentMillis = System.currentTimeMillis() ;
		long mDeltaMillis = mCurrentMillis - mLastClickMillis ;
		mLastClickMillis = mCurrentMillis ;
		if(0 < mDeltaMillis && mDeltaMillis < inDeltaTime) {
			return true ;
		}
		return false ;
	}
}