package org.tpwk.analyser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.tpwk.analysis.AnalysisResult;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PatternAnalysersTest
{
    @Parameterized.Parameters
    public static Collection<Object[]> data()
    {
        return Arrays.asList(new Object[][]{
                {LowerAlphaAnalyzer.class, new String[]{"", "1", "ab", "abc", "a b", "abc3", "ABC", "Abc"}, 2},
                {LowerAlphaNumAnalyser.class, new String[]{"", "1", "ab", "abc", "abc3", "ABC", "Abc", "123b", "a 5b"}, 2},
                {MixedAlphaAnalyser.class, new String[]{"", "123", "abc", "ab2", "AB2", "Abc", "Ab2"}, 1},
                {MixedAlphaNumAnalyser.class, new String[]{"", "123", "abc", "ab2", "AB2", "Abc", "Ab2"}, 1},
                {NumericAnalyser.class, new String[]{"", "123", "abc", "ab2", "AB2", "Abc", "Ab2"}, 1},
                {SpecialAnalyser.class, new String[]{"", "123", "abc", "ABC", "AB2,", "Abc.", "($%)"}, 1},
                {SpecialNumAnalyser.class, new String[]{"", "123", "abc", "ABC", "Abc.", "($%)", "(2$%)"}, 1},
                {UpperAlphaAnalyser.class, new String[]{"", "123", "abc", "ABC", "Abc2", "($%)", "(2$%)"}, 1},
                {UpperAlphaNumAnalyser.class, new String[]{"", "123", "abc", "ABC", "Abc2", "ABC2", "(2$%)"}, 1},
                {UpperAlphaSpecialAnalyser.class, new String[]{"", "123", "abc", "ABC", "Abc2", "ABC2", "(A$%)"}, 1},
                {UpperAlphaSpecialNumAnalyser.class, new String[]{"", "123", "abc", "ABC", "Abc2.", "ABC2", "(A2$%)"}, 1},
        });
    }

    @Parameterized.Parameter
    public Class<PatternAnalyser> analyserClass;

    @Parameterized.Parameter(1)
    public String[] strings;

    @Parameterized.Parameter(2)
    public int matches;

    public PatternAnalyser analyser;

    @Before
    public void setupAnalyser() throws IllegalAccessException, InstantiationException
    {
        analyser = analyserClass.newInstance();
    }

    @Test
    public void analyseWithNoStrings()
    {
        final AnalysisResult result = analyser.getAnalysis();

        assertNotNull(result);
        assertEquals(0, result.getTotal());

        final Map<String, Integer> values = result.getResults();

        assertNotNull(values);
        assertEquals(1, values.size());
        assertTrue(values.containsKey(analyser.getName()));
    }

    @Test
    public void analyse()
    {
        Arrays.stream(strings).forEach(analyser::analyse);

        final AnalysisResult result = analyser.getAnalysis();

        assertNotNull(result);
        assertEquals(strings.length, result.getTotal());

        final Map<String, Integer> values = result.getResults();

        assertNotNull(values);
        assertEquals(1, values.size());
        assertEquals(matches, values.get(analyser.getName()).intValue());
    }

    @Test(expected = NullPointerException.class)
    public void analyseNull()
    {
        analyser.analyse(null);
    }
}
