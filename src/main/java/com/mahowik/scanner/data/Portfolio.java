package com.mahowik.scanner.data;

import com.mahowik.scanner.util.MathUtils;

public class Portfolio {

	private final double initCache;

	private double cache;
	private double sharePrice;
	private int numberOfShares;

	public Portfolio(double initCache) {
		this.initCache = initCache;
		this.cache = initCache;
		this.sharePrice = 0;
		this.numberOfShares = 0;
	}

	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}

	public void sellAllShares() {
		cache += (numberOfShares * sharePrice);
		numberOfShares = 0;
	}

	public void buySharesForAvailableCache() {
		numberOfShares = (int) (cache / sharePrice);
		cache -= numberOfShares * sharePrice;
	}

	public double getPortfolioValue() {
		return cache + (numberOfShares * sharePrice);
	}

	public double getPerformance() {
		double performance = (getPortfolioValue() / initCache) * 100.0;
		return MathUtils.roundUp(performance);
	}

	public boolean isTurnToBuy() {
		return numberOfShares == 0;
	}

}
