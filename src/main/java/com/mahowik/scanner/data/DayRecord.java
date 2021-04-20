package com.mahowik.scanner.data;

import java.util.Date;

public class DayRecord {
	private final Date date;
	private final double highPrice;
	private final double lowPrice;

	public DayRecord(Date date, double highPrice, double lowPrice) {
		this.date = date;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
	}

	public Date getDate() {
		return date;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public double getAveragePrice() {
		return (highPrice + lowPrice) / 2.0;
	}
}
