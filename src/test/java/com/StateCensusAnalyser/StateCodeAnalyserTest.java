package com.StateCensusAnalyser;

import java.nio.file.Paths;

import org.junit.Test;

import junit.framework.Assert;

public class StateCodeAnalyserTest {
	public static final String STATE_CODE_DATA = "StateCode.csv";
	
	StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
	
	@Test
    public void ensureNoOfRecordMatches() throws CensusException{
        int records = stateCodeAnalyser.loadCSVFile(Paths.get(STATE_CODE_DATA));
        Assert.assertEquals(36, records);
    }
}
