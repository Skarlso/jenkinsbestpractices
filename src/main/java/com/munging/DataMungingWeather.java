package com.munging;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class DataMungingWeather extends Munger {

    /**
    * Updated comment to trigger merge conflict.
    * This merge attempt should fail because of a merge conflict.
    * @return Stream of read file lines.
    */
    public Stream<String> readFileLines() throws IOException {
        return super.readFileLines("weather.dat");
    }

    public List<Data> cleanUpStream(Stream<String> dirtyStream) throws IOException {
        return super.cleanUpStream(dirtyStream, '*', gatherWeatherData);
    }

    /**
     * Give a way of adding a function here which determines how I would like to do a compare.
     * Gives the client the ability to decide on the compare method.
     * @param weatherDatas Weather data
     * @return Data that is returned
     */
    public Data returnMinimumDifference(List<Data> weatherDatas) {
        Data weatherData = weatherDatas.stream().min(Comparator.comparing(Data::getDifference)).get();
        return weatherData;
    }

    /**
    * Gather Weather Data.
    * TODO: Fixed!
    */
    public Function<String, Data> gatherWeatherData = line -> {
        String[] fields = line.split("\\s+");
        String day = fields[0];
        String max = fields[1];
        String min = fields[2];
        return new WeatherData(
                day,
                Integer.parseInt(min),
                Integer.parseInt(max));
    };
}
