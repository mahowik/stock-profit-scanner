package com.mahowik.scanner.service;

import com.mahowik.scanner.data.DayRecord;
import com.mahowik.scanner.data.Portfolio;
import com.mahowik.scanner.util.MathUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockProfitScannerService {

	private static final Logger log = LogManager.getLogger(StockProfitScannerService.class);

	@Value("${min.threshold}")
	private double minThreshold;
	@Value("${max.threshold}")
	private double maxThreshold;
	@Value("${threshold.step}")
	private double thresholdStep;

	private final TradeStockSimulatorService tradeStockSimulatorService;
	private final DataProviderService dataProviderService;


	@Autowired
	public StockProfitScannerService(TradeStockSimulatorService tradeStockSimulatorService,
									 DataProviderService dataProviderService) {
		this.tradeStockSimulatorService = tradeStockSimulatorService;
		this.dataProviderService = dataProviderService;
	}


	@EventListener(ApplicationReadyEvent.class)
	public void scanForMaxProfit() {

		List<DayRecord> historicData = dataProviderService.getHistoricData();

		double maxPerformance = 0;
		double bestThresholdToBuy = 0;
		double bestThresholdToSell = 0;

		List<String[]> performanceData = new ArrayList<>();

		for (double thresholdToBuy = minThreshold; thresholdToBuy <= maxThreshold; thresholdToBuy += thresholdStep) {
			for (double thresholdToSell = minThreshold; thresholdToSell <= maxThreshold; thresholdToSell += thresholdStep) {

				Portfolio portfolio = tradeStockSimulatorService.simulateTrading(
						thresholdToBuy / 100.0,
						thresholdToSell / 100.0,
						historicData, false);

				double performance = portfolio.getPerformance();
				if (maxPerformance < performance) {
					maxPerformance = performance;
					bestThresholdToBuy = thresholdToBuy;
					bestThresholdToSell = thresholdToSell;
				}

				performanceData.add(new String[]{
						String.valueOf(thresholdToBuy),
						String.valueOf(thresholdToSell),
						String.valueOf(performance)});
			}
		}

		log.info("\n\n");
		log.info("### Historical stock data resource: " + dataProviderService.getStockResourceName());

		Portfolio portfolio = tradeStockSimulatorService.simulateTrading(
				bestThresholdToBuy / 100.0,
				bestThresholdToSell / 100.0,
				historicData, true);

		DayRecord firstDayRecord = getFirstDayRecord(historicData);
		log.info("### Portfolio started at: " + firstDayRecord.getDate());
		DayRecord lastDayRecord = getLastDayRecord(historicData);
		log.info("### Portfolio closed at: " + lastDayRecord.getDate());

		log.info("### Market performance: " +
				MathUtils.roundUp((lastDayRecord.getAveragePrice() / firstDayRecord.getAveragePrice()) * 100.0) + "%");
		log.info("### Portfolio MAX performance: " + portfolio.getPerformance() + "%, " +
				"to buy at: " + bestThresholdToBuy + "% from local MIN, " +
				"to sell at: " + bestThresholdToSell + "% from local MAX");

		dataProviderService.writeCSVFile(new String[]{"% threshold to buy", "% threshold to sell", "% performance"},
				performanceData, firstDayRecord.getDate(), lastDayRecord.getDate());
	}

	private DayRecord getFirstDayRecord(List<DayRecord> historicData) {
		return historicData.get(0);
	}

	private DayRecord getLastDayRecord(List<DayRecord> historicData) {
		return historicData.get(historicData.size() - 1);
	}

}
