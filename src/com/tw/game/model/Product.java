/**
 * 
 */
package com.tw.game.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Shridhar
 *
 */
public class Product {

	@JsonProperty
	String name;

	@JsonProperty()
	long price;

	@JsonProperty
	String endDate;

	@JsonProperty
	String startDate;

	@JsonProperty
	String category;

	public boolean isActive() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = sdf.parse(sdf.format(new Date()));
			Date start = sdf.parse(startDate);
			if (today.compareTo(start) >= 0) {
				if (endDate == null) {
					return true;
				}
				Date end = sdf.parse(endDate);
				if (today.compareTo(end) <= 0) {
					return true;
				}

			}
		} catch (ParseException parseException) {
			parseException.printStackTrace();
		}
		return false;
	}

}
