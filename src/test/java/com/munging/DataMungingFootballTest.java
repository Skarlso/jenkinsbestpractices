package com.munging;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;

public class DataMungingFootballTest {

    private DataMungingFootball dataMungingFootball;

    @Before
    public void setUp() throws Exception {
        dataMungingFootball = new DataMungingFootball();
    }

    @Test
    public void testCanReadFileFromResources() throws Exception {
        Assert.assertThat(dataMungingFootball.cleanUpStream(dataMungingFootball.readFileLines()), is(not(nullValue())));
    }

    @Test
    public void testCanCompareAListOfSpacedNumbers() throws Exception {
        FootballData f1 = new FootballData("Arsenal", 36, 87);
        FootballData f2 = new FootballData("Liverpool", 30, 80);
        List<Data> myTestList = Arrays.asList(f1, f2);
        Assert.assertThat(((FootballData)dataMungingFootball.returnMinimumDifference(myTestList)).getTeamName(), is("Liverpool"));
    }

    @Test
    public void testCanReadAllTheLinesFromFile() throws Exception {
        Assert.assertThat(dataMungingFootball.cleanUpStream(dataMungingFootball.readFileLines()).isEmpty(), is(false));
    }

    @Test
    public void testCanCompareTheLinesReadFromTheFile() throws Exception {
        Assert.assertThat(((FootballData)dataMungingFootball.returnMinimumDifference(
                dataMungingFootball.cleanUpStream(
                        dataMungingFootball.readFileLines()))).getTeamName(), is("Aston_Villa"));
    }

}
