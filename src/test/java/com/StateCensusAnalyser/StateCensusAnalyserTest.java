package com.StateCensusAnalyser;

import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;

import org.junit.Test;

import junit.framework.Assert;

public class StateCensusAnalyserTest {
	public static final String STATE_CENSUS_DATA = "./StateCensus.csv";
	
    @Test
    public void ensureNoOfRecordMatches(){
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int records = stateCensusAnalyser.loadCSVFile(Paths.get(STATE_CENSUS_DATA));
        Assert.assertEquals(29, records);
    }
}
