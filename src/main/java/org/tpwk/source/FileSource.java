package org.tpwk.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileSource implements StringSource
{
    private final Path path;
    private final Charset charset;
    private final CharsetDecoder decoder;

    private BufferedReader reader;

    private IOException lastException;

    public FileSource(final Path path)
    {
        this(path, StandardCharsets.UTF_8);
    }

    public FileSource(final Path path, final Charset charset)
    {
        this.path = path;
        this.charset = charset;
        this.decoder = null;
    }

    public FileSource(final Path path, final CharsetDecoder decoder)
    {
        this.path = path;
        this.charset = null;
        this.decoder = decoder;
    }

    @Override
    public Stream<String> strings() throws IOException
    {
        reader = (charset == null)
                ? new BufferedReader(new InputStreamReader(Files.newInputStream(path), decoder))
                : Files.newBufferedReader(path, charset);

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
