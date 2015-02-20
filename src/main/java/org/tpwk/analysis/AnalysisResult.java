package org.tpwk.analysis;

import java.util.Map;

public interface AnalysisResult
{
    Map<String, Integer> getResults();

    int getTotal();
}
