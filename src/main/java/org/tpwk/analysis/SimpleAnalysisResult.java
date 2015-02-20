package org.tpwk.analysis;

import java.util.Map;

public class SimpleAnalysisResult implements AnalysisResult
{
    private final int total;
    private final Map<String, Integer> results;

    public SimpleAnalysisResult(int total, final Map<String, Integer> results)
    {
        this.total = total;
        this.results = results;
    }

    @Override
    public Map<String, Integer> getResults()
    {
        return results;
    }

    @Override
    public int getTotal()
    {
        return total;
    }
}
