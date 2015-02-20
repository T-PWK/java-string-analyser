package org.tpwk.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileSource implements StringSource
{
    private final Path input;

    private BufferedReader reader;

    private IOException lastException;

    public FileSource(Path input)
    {
        this.input = input;
    }

    @Override
    public Stream<String> strings() throws IOException
    {
        reader = Files.newBufferedReader(input);
        return reader.lines();
    }

    @Override
    public void close() throws Exception
    {
        if (reader != null)
        {
            reader.close();
        }
    }

    public IOException getLastException()
    {
        return lastException;
    }
}
