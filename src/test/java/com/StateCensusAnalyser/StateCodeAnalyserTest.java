package com.StateCensusAnalyser;

import java.nio.file.Paths;

import org.junit.Test;

import junit.framework.Assert;

public class StateCodeAnalyserTest {
	public static final String STATE_CODE_DATA = "StateCode.csv";
	public static final String WRONG_STATE_CODE_DATA = "src/main/java/com/StateCensusAnalyser/StateCode.csv";
	public static final String WRONG_STATE_CODE_DATA_HEADER = "StateCensus.csv";
	
	StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
	
	@Test
    public void ensureNoOfRecordMatches() throws CensusException{
        int records = stateCodeAnalyser.loadCSVFile(Paths.get(STATE_CODE_DATA));
        Assert.assertEquals(36, records);
    }
	
	@Test
    public void checkWrongPath() throws CensusException{
    	try {
    		stateCodeAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CODE_DATA));
    	}
    	catch(CensusException e) {
    		Assert.assertEquals(CensusException.ExceptionType.WRONG_CSV, e.type);
    	}
    }
	
	@Test
    public void checkWrongHeader() throws CensusException{
    	try {
    		stateCodeAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CODE_DATA_HEADER));
    	}
    	catch(CensusException e) {
    		Assert.assertEquals(CensusException.ExceptionType.WRONG_HEADER, e.type);;
    	}
    }
}
