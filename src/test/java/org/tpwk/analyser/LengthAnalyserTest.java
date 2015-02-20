package org.tpwk.analyser;

import org.junit.Before;
import org.junit.Test;
import org.tpwk.analysis.AnalysisResult;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * JUnit test class for {@link org.tpwk.analyser.LengthAnalyser}.
 */
public class LengthAnalyserTest
{
    private static final String[] STRINGS = new String[]{ "", "1", "2", "ab", "abc" };

    private Analyser analyser;

    @Before
    public void setupAnalyser()
    {
        analyser = new LengthAnalyser();
    }

    @Test
    public void analyseWithNoStrings()
    {
        final AnalysisResult result = analyser.getAnalysis();

        assertNotNull(result);
        assertEquals(0, result.getTotal());

        final Map<String, Integer> values = result.getResults();

        assertNotNull(values);
        assertEquals(0, values.size());
    }

    @Test
    public void analyse()
    {
        Arrays.stream(STRINGS).forEach(analyser::analyse);

        final AnalysisResult result = analyser.getAnalysis();

        assertNotNull(result);
        assertEquals(5, result.getTotal());

        final Map<String, Integer> values = result.getResults();

        assertNotNull(values);

        assertTrue(values.containsKey("0"));
        assertTrue(values.containsKey("1"));
        assertTrue(values.containsKey("2"));
        assertTrue(values.containsKey("3"));

        assertEquals(4, values.size());

        assertEquals(1, values.get("0").intValue());
        assertEquals(2, values.get("1").intValue());
        assertEquals(1, values.get("2").intValue());
        assertEquals(1, values.get("3").intValue());
    }

    @Test(expected = NullPointerException.class)
    public void analyseNull()
    {
        analyser.analyse(null);
    }
}
