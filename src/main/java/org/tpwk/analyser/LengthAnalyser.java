package org.tpwk.analyser;

import org.tpwk.analysis.AnalysisResult;
import org.tpwk.analysis.SimpleAnalysisResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LengthAnalyser implements Analyser
{
    private final Map<String, Integer> results = new HashMap<>();

    private int total;

    @Override
    public void analyse(final String input)
    {
        final int len = Objects.requireNonNull(input).length();
        final String key = Integer.toString(len);

        total++;
        results.put(key, results.containsKey(key) ? results.get(key) + 1 : 1);
    }

    @Override
    public AnalysisResult getAnalysis()
    {
        return new SimpleAnalysisResult("length", total, results);
    }
}