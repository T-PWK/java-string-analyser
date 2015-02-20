package org.tpwk.analyser;

import org.junit.Test;
import org.tpwk.analysis.AnalysisResult;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class PatternAnalyserTest
{
    @Test
    public void test()
    {
        final PatternAnalyser analyser = new PatternAnalyser("pattern", Pattern.compile("(t|T)est$"));

        analyser.analyse("Hello");
        analyser.analyse("Hello Test");
        analyser.analyse("Tomorrow test");
        analyser.analyse("My test tomorrow");

        AnalysisResult result = analyser.getAnalysis();

        assertEquals(4, result.getTotal());
        assertEquals(2, result.getResults().size());

    }
}
