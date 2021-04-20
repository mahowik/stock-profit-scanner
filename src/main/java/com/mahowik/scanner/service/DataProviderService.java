package com.mahowik.scanner.service;

import com.mahowik.scanner.data.DayRecord;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DataProviderService {

	private static final Logger log = LogManager.getLogger(DataProviderService.class);

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Value("${resource.name}")
	private String stockResourceName;

	@Value("${start.date}")
	private String start;
	@Value("${end.date}")
	private String end;


	public List<DayRecord> getHistoricData() {
		List<String[]> rows = readCSVFile(stockResourceName);
		if (rows != null) {
			Date startDate = parseDate(start);
			Date endDate = parseDate(end);
			List<DayRecord> historicData = new ArrayList<>(rows.size() - 1);
			for (String[] row : rows) {
				Date date = parseDate(row[0]);
				if (date != null && date.after(startDate) && date.before(endDate)) {
					historicData.add(new DayRecord(date, Double.parseDouble(row[1]), Double.parseDouble(row[2])));
				}
			}
			return historicData;
		}
		return null;
	}

	public List<String[]> readCSVFile(String resource) {
		try {
			CSVReader csvReader = new CSVReader(new FileReader(new ClassPathResource(resource).getFile()));
			return csvReader.readAll();
		} catch (Exception e) {
			log.error("error to read CVS data file: ", e);
		}
		return null;
	}

	public void writeCSVFile(String[] headers, List<String[]> data, Date firstDay, Date lastDay) {
		try {

			String fileName = "output/performance_(" + dateFormat.format(firstDay) + " - " +
					dateFormat.format(lastDay) + ")_" + stockResourceName;

			CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName), ',', '"', '"', ",\n");
			csvWriter.writeNext(headers);
			csvWriter.writeAll(data);
			csvWriter.close();
		} catch (Exception e) {
			log.error("error to write CVS data file: ", e);
		}
	}

	public String getStockResourceName() {
		return stockResourceName;
	}

	private Date parseDate(String source) {
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
//			log.error("", e);
		}
		return null;
	}

}
