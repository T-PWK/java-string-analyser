package org.tpwk.analysis;

import org.tpwk.analyser.Analyser;

import java.io.IOException;

public interface AnalysisFormatter
{
    void format(Analyser... analysers) throws IOException;
}
