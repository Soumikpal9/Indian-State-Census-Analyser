package com.StateCensusAnalyser;

import java.util.List;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.CSVBuilder.*;
import com.google.gson.Gson;

public class StateCensusAnalyser {
	public static final String STATE_CENSUS_DATA = "StateCensus.csv";
	
	public int loadCSVFile(Path path) throws CensusException, CSVException {
		try (Reader reader = Files.newBufferedReader(path)){
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCensus> stateCensusList = csvBuilder.getCSVFileList(reader, StateCensus.class);
			return stateCensusList.size();
		}
		catch(IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV); 
		}
		catch(RuntimeException e) {
			throw new CensusException("File internal data not valid", CensusException.ExceptionType.WRONG_HEADER);
		}
		catch(CSVException e) {
			throw new CensusException("Unable to parse", CensusException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	
	public String getStateWiseSortedCensusData(Path path) throws CensusException, CSVException {
		try (Reader reader = Files.newBufferedReader(path)){
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCensus> stateCensusList = csvBuilder.getCSVFileList(reader, StateCensus.class);
			if(stateCensusList == null || stateCensusList.size() == 0) {
				throw new CensusException("No census data", CensusException.ExceptionType.NO_CENSUS_DATA);
			}
			Comparator<StateCensus> censusComparator = Comparator.comparing(census -> census.state);
			this.sort(stateCensusList, censusComparator);
			String sortedStateCensus = new Gson().toJson(stateCensusList);
			return sortedStateCensus;
		}
		catch(IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV); 
		}
		catch(RuntimeException e) {
			throw new CensusException("File internal data not valid", CensusException.ExceptionType.WRONG_HEADER);
		}
		catch(CSVException e) {
			throw new CensusException("Unable to parse", CensusException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	
	public String getPopulationWiseSortedCensusData(Path path) throws CensusException, CSVException{
		try(Reader reader = Files.newBufferedReader(path)){
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCensus> stateCensusList = csvBuilder.getCSVFileList(reader, StateCensus.class);
			if(stateCensusList == null || stateCensusList.size() == 0) {
				throw new CensusException("No census data", CensusException.ExceptionType.NO_CENSUS_DATA);
			}
			Comparator<StateCensus> censusComparator = Comparator.comparing(census -> census.population);
			this.sortDesc(stateCensusList, censusComparator);
			String sortedStateCensus = new Gson().toJson(stateCensusList);
			return sortedStateCensus;
		}
		catch(IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV); 
		}
		catch(RuntimeException e) {
			throw new CensusException("File internal data not valid", CensusException.ExceptionType.WRONG_HEADER);
		}
		catch(CSVException e) {
			throw new CensusException("Unable to parse", CensusException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	
	public String getPopulationDensityWiseSortedCensusData(Path path) throws CensusException, CSVException{
		try(Reader reader = Files.newBufferedReader(path)){
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCensus> stateCensusList = csvBuilder.getCSVFileList(reader, StateCensus.class);
			if(stateCensusList == null || stateCensusList.size() == 0) {
				throw new CensusException("No census data", CensusException.ExceptionType.NO_CENSUS_DATA);
			}
			Comparator<StateCensus> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
			this.sortDesc(stateCensusList, censusComparator);
			String sortedStateCensus = new Gson().toJson(stateCensusList);
			return sortedStateCensus;
		}
		catch(IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV); 
		}
		catch(RuntimeException e) {
			throw new CensusException("File internal data not valid", CensusException.ExceptionType.WRONG_HEADER);
		}
		catch(CSVException e) {
			throw new CensusException("Unable to parse", CensusException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	
	public String getAreaWiseSortedCensusData(Path path) throws CensusException, CSVException{
		try(Reader reader = Files.newBufferedReader(path)){
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCensus> stateCensusList = csvBuilder.getCSVFileList(reader, StateCensus.class);
			if(stateCensusList == null || stateCensusList.size() == 0) {
				throw new CensusException("No census data", CensusException.ExceptionType.NO_CENSUS_DATA);
			}
			Comparator<StateCensus> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
			this.sortDesc(stateCensusList, censusComparator);
			String sortedStateCensus = new Gson().toJson(stateCensusList);
			return sortedStateCensus;
		}
		catch(IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV); 
		}
		catch(RuntimeException e) {
			throw new CensusException("File internal data not valid", CensusException.ExceptionType.WRONG_HEADER);
		}
		catch(CSVException e) {
			throw new CensusException("Unable to parse", CensusException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

	private void sort(List<StateCensus> stateCensusList, Comparator<StateCensus> censusComparator) {
		for(int i = 0; i < stateCensusList.size() - 1; i++) {
			for(int j = 0; j< stateCensusList.size() - 1 - i; j++) {
				StateCensus census1 = stateCensusList.get(j);
				StateCensus census2 = stateCensusList.get(j+1);
				if(censusComparator.compare(census1, census2) > 0) {
					stateCensusList.set(j, census2);
					stateCensusList.set(j+1, census1);
				}
			}
		}
	}
	
	private void sortDesc(List<StateCensus> stateCensusList, Comparator<StateCensus> censusComparator) {
		for(int i = 0; i < stateCensusList.size() - 1; i++) {
			for(int j = 0; j < stateCensusList.size() -1 - i; j++) {
				StateCensus census1 = stateCensusList.get(j);
				StateCensus census2 = stateCensusList.get(j+1);
				if(censusComparator.compare(census1, census2) < 0) {
					stateCensusList.set(j,  census2);
					stateCensusList.set(j+1, census1);
				}
			}
		}
	}	
}
