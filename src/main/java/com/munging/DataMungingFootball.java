package com.munging;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class DataMungingFootball extends Munger {

    public Stream<String> readFileLines() throws IOException {
        return super.readFileLines("football.dat");
    }

    public List<Data> cleanUpStream(Stream<String> dirtyStream) throws IOException {
        return super.cleanUpStream(dirtyStream, '-', gatherFootballData);
    }

    public Data returnMinimumDifference(List<Data> footballDatas) {
        Data footBallData = footballDatas.stream().min(Comparator.comparing(Data::getDifference)).get();
        return footBallData;
    }

    public Function<String, Data> gatherFootballData = line -> {
        String[] fields = line.split("\\s+");
        String teamName = fields[1];
        String score = fields[6];
        String against = fields[7];
        return new FootballData (
                teamName,
                Integer.parseInt(against),
                Integer.parseInt(score));
    };
}
