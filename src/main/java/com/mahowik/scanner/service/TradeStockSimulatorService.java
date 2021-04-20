package com.mahowik.scanner.service;

import com.mahowik.scanner.data.DayRecord;
import com.mahowik.scanner.data.Portfolio;
import com.mahowik.scanner.util.MathUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeStockSimulatorService {

	private static final Logger log = LogManager.getLogger(TradeStockSimulatorService.class);

	private Double localMin = null;
	private Double localMax = null;


	public Portfolio simulateTrading(double thresholdToBuy, double thresholdToSell,
									 List<DayRecord> historicData, boolean printLog) {

		Portfolio portfolio = new Portfolio(1000000.0d);

		resetLocalExtrema();

		historicData.forEach(dayRecord -> {

			if (portfolio.isTurnToBuy()) {
				updateLocalMin(dayRecord);
				buyByThreshold(dayRecord, thresholdToBuy, portfolio, printLog);

			} else {
				updateLocalMax(dayRecord);
				sellByThreshold(dayRecord, thresholdToSell, portfolio, printLog);
			}

			portfolio.setSharePrice(dayRecord.getAveragePrice());
		});

		return portfolio;
	}

	private void buyByThreshold(DayRecord dayRecord, double thresholdToBuy, Portfolio portfolio, boolean printLog) {
		if (localMin < dayRecord.getHighPrice()) {
			double changeInPercent = (dayRecord.getHighPrice() / localMin) - 1.0d;
			if (changeInPercent >= thresholdToBuy) {
				double price = localMin + localMin * thresholdToBuy;
				if (price < dayRecord.getLowPrice()) {
					// if threshold price is between days market hours
					price = dayRecord.getLowPrice();
				} else if (price > dayRecord.getHighPrice()) {
					log.error("Threshold price should not be bigger than day local!");
					throw new IllegalArgumentException("Threshold price should not be bigger than day local!");
				}

				if (printLog) {
					log.info("Threshold price to BUY was reached (" + dayRecord.getDate() + "): " +
							MathUtils.roundUp(price) + "$, from local MIN of: " + MathUtils.roundUp(localMin) + "$");
				}

				portfolio.setSharePrice(price);
				portfolio.buySharesForAvailableCache();

				localMax = price;
			}
		}
	}

	private void sellByThreshold(DayRecord dayRecord, double thresholdToSell, Portfolio portfolio, boolean printLog) {
		if (localMax > dayRecord.getLowPrice()) {
			double changeInPercent = 1.0d - (dayRecord.getLowPrice() / localMax);
			if (changeInPercent >= thresholdToSell) {
				double price = localMax - localMax * thresholdToSell;
				if (price > dayRecord.getHighPrice()) {
					// if threshold price is between days market hours
					price = dayRecord.getHighPrice();
				} else if (price < dayRecord.getLowPrice()) {
					log.error("Threshold price should not be lower than day local!");
					throw new IllegalArgumentException("Threshold price should not be lower than day local!");
				}

				if (printLog) {
					log.info("Threshold price to SELL was reached (" + dayRecord.getDate() + "): " +
							MathUtils.roundUp(price) + "$ from local MAX of: " + MathUtils.roundUp(localMax) + "$");
				}

				portfolio.setSharePrice(price);
				portfolio.sellAllShares();

				localMin = price;
			}
		}
	}

	private void resetLocalExtrema() {
		localMin = null;
		localMax = null;
	}

	private void updateLocalMin(DayRecord dayRecord) {
		if (localMin == null || localMin > dayRecord.getLowPrice()) {
			localMin = dayRecord.getLowPrice();
		}
	}

	private void updateLocalMax(DayRecord dayRecord) {
		if (localMax == null || localMax < dayRecord.getHighPrice()) {
			localMax = dayRecord.getHighPrice();
		}
	}
}
