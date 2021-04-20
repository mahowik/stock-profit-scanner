package com.mahowik.scanner;

import com.mahowik.scanner.data.DayRecord;
import com.mahowik.scanner.data.Portfolio;
import com.mahowik.scanner.service.DataProviderService;
import com.mahowik.scanner.service.TradeStockSimulatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
class StockProfitScannerTests {

	@Autowired
	private TradeStockSimulatorService tradeStockSimulatorService;
	@Autowired
	private DataProviderService dataProviderService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testReadCSVFile() {
		List<String[]> values = dataProviderService.readCSVFile("sp500.csv");
		Assertions.assertNotNull(values);
		Assertions.assertFalse(values.isEmpty());
	}

	@Test
	public void testGetHistoricData() {
		List<DayRecord> historicData = dataProviderService.getHistoricData();
		Assertions.assertNotNull(historicData);
		Assertions.assertFalse(historicData.isEmpty());
	}

	@Test
	public void testSimulateTrading() {
		List<DayRecord> historicData = dataProviderService.getHistoricData();
		Portfolio portfolio = tradeStockSimulatorService.simulateTrading(20.0 / 100.0,
				20.0 / 100.0, historicData, false);
		Assertions.assertTrue(portfolio.getPerformance() > 0);
	}

}
