package org.tpwk.source;

import java.io.IOException;
import java.util.stream.Stream;

public interface StringSource extends AutoCloseable
{
    Stream<String> strings() throws IOException;
}
