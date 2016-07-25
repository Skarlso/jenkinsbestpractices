package com.munging;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;

public class DataMungingWeatherTest {

    private DataMungingWeather dataMungingWeather;

    @Before
    public void setUp() throws Exception {
        dataMungingWeather = new DataMungingWeather();
    }

    @Test
    public void testCanReadFileFromResources() throws Exception {
        Assert.assertThat(dataMungingWeather.cleanUpStream(dataMungingWeather.readFileLines()), is(not(nullValue())));
    }

    @Test
    public void testCanCompareAListOfSpacedNumbers() throws Exception {
        WeatherData w1 = new WeatherData("1", 33, 44);
        WeatherData w2 = new WeatherData("2", 98, 99);
        List<Data> myTestList = Arrays.asList(w1, w2);
        Assert.assertThat(((WeatherData)dataMungingWeather.returnMinimumDifference(myTestList)).getDayNumber(), is("2"));
    }

    @Test
    public void testCanReadAllTheLinesFromFile() throws Exception {
        Assert.assertThat(dataMungingWeather.cleanUpStream(dataMungingWeather.readFileLines()).isEmpty(), is(false));
    }

    @Test
    public void testCanCompareTheLinesReadFromTheFile() throws Exception {
        Assert.assertThat(((WeatherData)dataMungingWeather.returnMinimumDifference(
                dataMungingWeather.cleanUpStream(
                        dataMungingWeather.readFileLines()))).getDayNumber(), is("14"));
    }
}