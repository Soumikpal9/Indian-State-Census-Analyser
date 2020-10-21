package com.StateCensusAnalyser;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;

import org.junit.Test;

import junit.framework.Assert;
import com.CSVBuilder.*;

public class StateCensusAnalyserTest {
	public static final String STATE_CENSUS_DATA = "StateCensus.csv";
	public static final String WRONG_STATE_CENSUS_DATA = "src/main/java/com/StateCensusAnalyser/StateCensus.csv";
	public static final String WRONG_STATE_CENSUS_DATA_HEADER = "StateCode.csv";
	public static final String WRONG_STATE_CENSUS_DATA_TYPE = "StateCensus.txt";
	
	StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
	
    @Test
    public void ensureNoOfRecordMatches() throws CensusException, CSVException{
        int records = stateCensusAnalyser.loadCSVFile(Paths.get(STATE_CENSUS_DATA));
        Assert.assertEquals(29, records);
    }
    
    @Test
    public void checkWrongPath() throws CensusException, CSVException{
    	try {
    		stateCensusAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CENSUS_DATA));
    	}
    	catch(CensusException e) {
    		Assert.assertEquals(CensusException.ExceptionType.WRONG_CSV, e.type);
    	}
    }
    
    @Test
    public void checkWrongHeader() throws CensusException, CSVException{
    	try {
    		stateCensusAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CENSUS_DATA_HEADER));
    	}
    	catch(CensusException e) {
    		Assert.assertEquals(CensusException.ExceptionType.WRONG_HEADER, e.type);;
    	}
    }
    
    @Test
    public void checkWrongType() throws CensusException, CSVException{
    	try {
    		stateCensusAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CENSUS_DATA_TYPE));
    	}
    	catch(CensusException e) {
    		Assert.assertEquals(CensusException.ExceptionType.WRONG_TYPE, e.type);;
    	}
    }
}
