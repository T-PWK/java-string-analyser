package org.tpwk.analyser;

import org.tpwk.analysis.AnalysisResult;

/**
 * The {@code Analyser} interface is implemented by a class performing input sting analysis and providing analysis results
 *
 * @author T-PWK
 */
public interface Analyser extends Analysable
{
    AnalysisResult getAnalysis();
}
