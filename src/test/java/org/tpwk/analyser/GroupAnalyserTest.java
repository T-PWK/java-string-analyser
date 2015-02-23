package org.tpwk.analyser;

import org.junit.Test;
import org.tpwk.analysis.AnalysisResult;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GroupAnalyserTest
{
    @Test(expected = IllegalArgumentException.class)
    public void buildGroupWithNoName()
    {
        GroupAnalyser.withAnalysers(LengthAnalyser.class).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildGroupWithNoAnalysers()
    {
        GroupAnalyser.withName("Test").build();
    }

    @Test
    public void buildGroupAnalysers()
    {
        final GroupAnalyser group = GroupAnalyser.withName("Test").withAnalysers(LengthAnalyser.class).build();

        assertNotNull(group);
    }

    @Test
    public void buildGroupWithDifferentTypeAnalysers()
    {
        final GroupAnalyser group = GroupAnalyser
                .withAnalyzers(new LowerAlphaAnalyzer()).withAnalysers(LengthAnalyser.class)
                .withName("Test").build();

        assertNotNull(group);
    }

    @Test(expected = RuntimeException.class)
    public void buildGroupWithPatternAnalyser()
    {
        GroupAnalyser.withName("Pattern").withAnalysers(PatternAnalyser.class).build();
    }

    @Test
    public void analyseUsingGroup()
    {
        final GroupAnalyser group = GroupAnalyser.withName("Test")
                .withAnalysers(UpperAlphaAnalyser.class, NumericAnalyser.class).build();

        Arrays.stream(new String[]{ "", "123", "ABC", "0", "abc" }).forEach(group::analyse);

        final AnalysisResult result = group.getAnalysis();

        assertNotNull(result);
        assertNotNull(result.getResults());
        assertEquals(5, result.getTotal());
        assertEquals("Test", result.getName());

        final Map<String, Integer> values = result.getResults();

        assertEquals(2, values.size());
        assertEquals(2, values.get("numeric").intValue());
        assertEquals(1, values.get("upperalpha").intValue());
    }
}
