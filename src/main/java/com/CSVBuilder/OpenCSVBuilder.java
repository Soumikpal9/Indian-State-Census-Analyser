package com.CSVBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder {
	@Override
	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			CsvToBean<E> csvToBean = csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
			return csvToBean.iterator();
		}
		catch(IllegalStateException e) {
			throw new CSVException(e.getMessage(), CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
		//return this.getCSVBean(reader, csvClass).iterator();
	}

	@Override
	public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			CsvToBean<E> csvToBean = csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
			return csvToBean.parse();
		}
		catch(IllegalStateException e) {
			throw new CSVException(e.getMessage(), CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
		//return this.getCSVBean(reader, csvClass).parse();
	}
	
	/*private CsvToBean<E> getCSVBean(Reader reader, Class<E> csvClass) throws CSVException{
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			CsvToBean<E> csvToBean = csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
			return csvToBean;
		}
		catch(IllegalStateException e) {
			throw new CSVException(e.getMessage(), CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
	}*/
}
