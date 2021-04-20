package com.mahowik.scanner.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

	public static double roundDown(double value) {
		return round(value, RoundingMode.DOWN);
	}

	public static double roundUp(double value) {
		return round(value, RoundingMode.UP);
	}

	public static double round(double value, RoundingMode roundingMode) {
		return new BigDecimal(value).setScale(2, roundingMode).doubleValue();
	}
}
