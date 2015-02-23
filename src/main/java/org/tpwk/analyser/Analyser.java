package org.tpwk.analyser;

import org.tpwk.analysis.AnalysisResult;

public interface Analyser extends Analysable
{
    AnalysisResult getAnalysis();
}
