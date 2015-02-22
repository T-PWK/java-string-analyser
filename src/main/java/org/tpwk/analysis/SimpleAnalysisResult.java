package org.tpwk.analysis;

import java.util.Map;

public class SimpleAnalysisResult implements AnalysisResult
{
    private final String name;
    private final int total;
    private final Map<String, Integer> results;

    public SimpleAnalysisResult(final String name, int total, final Map<String, Integer> results)
    {
        this.name = name;
        this.total = total;
        this.results = results;
    }

    @Override
    public Map<String, Integer> getResults()
    {
        return results;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public int getTotal()
    {
        return total;
    }
}
