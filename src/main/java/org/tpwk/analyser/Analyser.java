package org.tpwk.analyser;

import org.tpwk.analysis.AnalysisResult;

public interface Analyser
{
    void analyse(String input);

    AnalysisResult getAnalysis();
}
