package com.munging;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Munger {


    public Stream<String> readFileLines(String filename) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileUrl = classLoader.getResource(filename);
        assert fileUrl != null;
        Stream<String> dirtyStream = Files.lines(Paths.get(fileUrl.getFile()));
        return dirtyStream;
    }

    public List<Data> cleanUpStream(Stream<String> dirtyStream, Character character, Function<String, Data> mapper) throws IOException {
        List<Data> cleanList = dirtyStream
                .map(line -> line.trim().replaceAll(String.format("\\%s", character), ""))
                .filter(line -> line.length() > 0 && Character.isDigit(line.charAt(0)))
                .map(mapper)
                .collect(Collectors.toList());
        return cleanList;
    }

    public abstract Data returnMinimumDifference(List<Data> myTestList);
}
