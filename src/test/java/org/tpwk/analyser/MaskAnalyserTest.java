package org.tpwk.analyser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.tpwk.analysis.AnalysisResult;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class MaskAnalyserTest
{
    @Parameterized.Parameters
    public static Collection<Object[]> data()
    {
        return Arrays.asList(new Object[][]{
                { new MaskAnalyser("?d"), new String[]{ "", "1", "ab", "abc32", ".#", "1.#32" }, 1 },
                { new MaskAnalyser("?d?d?l"), new String[]{ "", "1", "21a", "abc32", ".#", "1.#32" }, 1 },
                { new MaskAnalyser("?u?u?u"), new String[]{ "", "1", "21a", "ABC", ".#", "1.#32" }, 1 },
                { new MaskAnalyser("?s?s?s"), new String[]{ "", "1", "21a", "ABC", ".#", "1.#32" }, 0 },
                { new MaskAnalyser("?s?u?s"), new String[]{ "", "1", "21a", "ABC", ".X#", "1.#32" }, 1 },
                { new MaskAnalyser("?a?a?a?a?a"), new String[]{ "", "1", "21a", "ABC", ".X#", "1.#32" }, 1 },
        });
    }

    @Parameterized.Parameter
    public MaskAnalyser analyser;

    @Parameterized.Parameter(1)
    public String[] strings;

    @Parameterized.Parameter(2)
    public int matches;

    @Test
    public void analyse()
    {
        Stream.of(strings).forEach(analyser::analyse);

        final AnalysisResult result = analyser.getAnalysis();

        assertNotNull(result);
        assertEquals(strings.length, result.getTotal());

        final Map<String, Integer> values = result.getResults();

        assertNotNull(values);
        assertEquals(1, values.size());
        assertEquals(matches, values.get(analyser.getName()).intValue());
    }
}
