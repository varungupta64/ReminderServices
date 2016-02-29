package com.defense.utils;

import java.util.Date;

/**
 * 
 * @author varungupta01
 *
 */
public class UtilityClass {
	
	/**
	 * Returns true if dateOfAction is one day previous of currentDate.
	 * 
	 * @param currentDate
	 * @param dateOfAction
	 * @return
	 */
	public static boolean isSuitableCandidate(Date currentDate,Date dateOfAction){
		if(currentDate.getYear() == dateOfAction.getYear()){
			if(currentDate.getMonth()==dateOfAction.getMonth()){
				if(currentDate.getDate() == dateOfAction.getDate() - 1){
					return true;
				}
			}
		}
		return false;
	}
}
