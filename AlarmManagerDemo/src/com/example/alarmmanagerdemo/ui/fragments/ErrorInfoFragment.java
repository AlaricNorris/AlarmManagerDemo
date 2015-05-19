/**
 * 	ErrorInfoFragment.java
 * 	com.example.alarmmanagerdemo.ui.fragments
 * 	Function： 	TODO 
 *   ver     date      		author
 * 	──────────────────────────────────
 *   		 2015-5-19 		Norris
 *	Copyright (c) 2015, TNT All Rights Reserved.
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-19	下午3:14:52	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
package com.example.alarmmanagerdemo.ui.fragments ;

import com.example.alarmmanagerdemo.R ;
import android.app.Fragment ;
import android.os.Bundle ;
import android.view.LayoutInflater ;
import android.view.View ;
import android.view.ViewGroup ;
import android.widget.TextView ;

/**
 *	ClassName:	ErrorInfoFragment
 *	Function: 	TODO ADD FUNCTION
 *	Reason:	 	TODO ADD REASON
 *	@author   	Norris		Norris.sly@gmail.com
 *	@version  	
 *	@since   	Ver 1.0		I used to be a programmer like you, then I took an arrow in the knee 
 *	@Date	 	2015		2015-5-19		下午3:14:52
 *	@see 	 	
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Fields 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	@Methods 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 *	2015-5-19	下午3:14:52	Modified By Norris 
 *	──────────────────────────────────────────────────────────────────────────────────────────────────────
 */
public class ErrorInfoFragment extends Fragment {

	private static ErrorInfoFragment mInstance ;

	private ErrorInfoFragment() {
	}

	public static ErrorInfoFragment getInstance(Bundle inBundle) {
		if(mInstance == null) {
			synchronized(ErrorInfoFragment.class) {
				if(mInstance == null) {
					mInstance = new ErrorInfoFragment() ;
				}
			}
		}
		return mInstance ;
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@ Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
	}

	/**
	 * 	(non-Javadoc)
	 * 	@see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@ Override
	public View onCreateView(LayoutInflater inflater , ViewGroup container ,
			Bundle savedInstanceState) {
		View inView = inflater.inflate(R.layout.fragment_page_error , container) ;
		((TextView) inView.findViewById(R.id.text_errorinfo)).setText("Sorry! Page Error!") ;
		return inView ;
	}
	
}
