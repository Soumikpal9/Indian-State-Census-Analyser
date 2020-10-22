package com.StateCensusAnalyser;

import java.nio.file.Paths;

import org.junit.Test;

import junit.framework.Assert;
import com.CSVBuilder.*;
import com.google.gson.Gson;

public class StateCodeAnalyserTest {
	public static final String STATE_CODE_DATA = "StateCode.csv";
	public static final String WRONG_STATE_CODE_DATA = "src/main/java/com/StateCensusAnalyser/StateCode.csv";
	public static final String WRONG_STATE_CODE_DATA_HEADER = "StateCensus.csv";
	public static final String WRONG_STATE_CODE_DATA_TYPE = "StateCode.txt";
	
	StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
	
	@Test
    public void ensureNoOfRecordMatches() throws CensusException, CSVException{
        int records = stateCodeAnalyser.loadCSVFile(Paths.get(STATE_CODE_DATA));
        Assert.assertEquals(36, records);
    }
	
	@Test
    public void checkWrongPath() throws CensusException, CSVException{
    	try {
    		stateCodeAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CODE_DATA));
    	}
    	catch(CensusException e) {
    		Assert.assertEquals(CensusException.ExceptionType.WRONG_CSV, e.type);
    	}
    }
	
	@Test
    public void checkWrongHeader() throws CensusException, CSVException{
    	try {
    		stateCodeAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CODE_DATA_HEADER));
    	}
    	catch(CensusException e) {
    		Assert.assertEquals(CensusException.ExceptionType.WRONG_HEADER, e.type);;
    	}
    }
	
	@Test
    public void checkWrongType() throws CensusException, CSVException{
    	try {
    		stateCodeAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CODE_DATA_TYPE));
    	}
    	catch(CensusException e) {
    		Assert.assertEquals(CensusException.ExceptionType.WRONG_TYPE, e.type);;
    	}
    }
	
	@Test
    public void checkSorted() throws CensusException, CSVException{
    	try {
    		String sortedCodeData = stateCodeAnalyser.getStateWiseSortedCodeData(Paths.get(STATE_CODE_DATA));
    		StateCode[] codeList = new Gson().fromJson(sortedCodeData, StateCode[].class);
    		Assert.assertEquals(codeList[28].stateName, "West Bengal");
    	}
    	catch(CensusException e) {}
    }
}
