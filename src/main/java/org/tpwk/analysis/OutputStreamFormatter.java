package org.tpwk.analysis;

import java.io.IOException;
import java.io.PrintStream;

public class OutputStreamFormatter implements AnalysisFormatter
{
    @Override
    public void format(final AnalysisResult result) throws IOException
    {
        final PrintStream out = new PrintStream(System.out);

        out.println("Analysis results");

        result.getResults().entrySet().forEach((entry) -> {
            out.printf("%s - %s (%.1f%%) %n", entry.getKey(), entry.getValue(), entry.getValue() * 100D / result.getTotal());
        });
    }
}