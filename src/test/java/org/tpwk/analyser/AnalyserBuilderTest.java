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
public class AnalyserBuilderTest
{
    @Parameterized.Parameters
    public static Collection<Object[]> data()
    {
        return Arrays.asList(new Object[][]{
                { new AnalyserBuilder("hasDigits").hasDigits().build(),
                        new String[]{ "", "1", "ab", "abc32", ".#", "1.#32" }, 3 },
                { new AnalyserBuilder("hasDigits").hasDigits(2).build(),
                        new String[]{ "", "1", "ab", "abc32", ".#", "1.#32" }, 2 },
                { new AnalyserBuilder("hasDigits").hasDigits(2,3).build(),
                        new String[]{ "", "1", "ab", "abc32", ".#", "1.#32" }, 2 },
                { new AnalyserBuilder("hasText").has("^$").build(),
                        new String[]{ "", "1", "ab", "a^$bc32", "^.$#", "1.#32" }, 1 },
                { new AnalyserBuilder("isEmpty").isEmpty().build(),
                        new String[]{ "", "1", "ab", "a^$bc32", "^.$#", "1.#32" }, 1 },
                { new AnalyserBuilder("isEmptyAndDigits").isEmpty().hasDigits().matchOne().build(),
                        new String[]{ "", "1", "ab", "a^$bc32", "^.$#", "1.#32" }, 4 },
                { new AnalyserBuilder("isEmptyAndDigits").has("bc").hasDigits().matchAll().build(),
                        new String[]{ "", "1", "ab", "a^$bc32", "^.$#", "1.#32" }, 1 },
                { new AnalyserBuilder().withName("empty&Digits").has("bc").hasDigits().build(),
                        new String[]{ "", "1", "ab", "a^$bc32", "^.$#", "1.#32" }, 1 },
        });
    }

    @Parameterized.Parameter
    public PatternAnalyser analyser;

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
