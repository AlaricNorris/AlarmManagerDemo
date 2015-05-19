/**
 * 	AlarmReminderEditFragment.java
 * 	com.example.alarmmanagerdemo.ui.fragments
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-19 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-19	下午3:06:28	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo.ui.fragments ;

import android.app.Fragment ;
import android.os.Bundle ;
import android.view.LayoutInflater ;
import android.view.View ;
import android.view.ViewGroup ;
import com.example.alarmmanagerdemo.R ;
import com.example.alarmmanagerdemo.entities.AlarmReminderEntity ;

/**
 *	ClassName:	AlarmReminderEditFragment
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	Norris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-19		下午3:06:28
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-19	下午3:06:28	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class AlarmReminderEditFragment extends Fragment {

	/**
	 * 	newInstance:(带参数新建Fragment)
	 *  ──────────────────────────────────
	 * 	@param 		inAlarmReminderEntity
	 * 	@return    
	 * 	@throws 
	 * 	@since  	I used to be a programmer like you, then I took an arrow in the knee　Ver 1.0
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 *	2015-5-19	下午3:09:49	Modified By Norris 
	 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
	 */
	public static Fragment newInstance(AlarmReminderEntity inAlarmReminderEntity) {
		AlarmReminderEditFragment mFragment = new AlarmReminderEditFragment() ;
		// Bundle up all of the data needed to create the fragment
		Bundle mBundle = new Bundle() ;
		mFragment.setArguments(mBundle) ;
		if(inAlarmReminderEntity == null) {
			return ErrorInfoFragment.getInstance(mBundle) ;
		}
		return mFragment ;
	}

	private AlarmReminderEntity mAlarmReminderEntity ;

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@ Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		try {
			mAlarmReminderEntity = (AlarmReminderEntity) getArguments().getSerializable(
					"AlarmReminderEntity") ;
		}
		catch(Exception e) {
		}
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@ Override
	public View onCreateView(LayoutInflater inflater , ViewGroup container ,
			Bundle savedInstanceState) {
		if(mAlarmReminderEntity == null) {
			return inflater.inflate(R.layout.fragment_page_error , container) ;
		}
		return super.onCreateView(inflater , container , savedInstanceState) ;
	}
}
