package org.tpwk.analysis;

import java.util.Map;

public interface AnalysisResult
{
    String getName();

    int getTotal();

    Map<String, Integer> getResults();
}
