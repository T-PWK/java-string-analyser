package org.tpwk.analysis;

import org.tpwk.analyser.Analyser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public class OutputStreamFormatter implements AnalysisFormatter
{
    private final String entryFormat;
    private final PrintStream out = new PrintStream(System.out);

    public OutputStreamFormatter()
    {
        this("%5s : %,10d (%4.1f%%) %n");
    }

    public OutputStreamFormatter(final String format)
    {
        this.entryFormat = format;
    }

    public OutputStreamFormatter(final int keyLen, final int valueLen, final int percentLen, final String divider)
    {
        this.entryFormat = String.format("%%%ds %s %%,%dd (%%%d.1f%%%%) %%n", keyLen, divider, valueLen, percentLen);
    }

    protected String formatLine(final String key, final int value, final double percent)
    {
        return String.format(entryFormat, key, value, percent);
    }

    protected void formatAnalysisResult(final AnalysisResult result)
    {
        out.printf("%s (total: %,d)%n", result.getName(), result.getTotal());

        result.getResults().entrySet().forEach((entry) -> {
            out.print(formatLine(entry.getKey(), entry.getValue(), entry.getValue() * 100D / result.getTotal()));
        });
    }

    @Override
    public void format(final Analyser... analysers) throws IOException
    {
        Arrays.stream(analysers).map(analyser -> analyser.getAnalysis()).forEach(this::formatAnalysisResult);
    }
}