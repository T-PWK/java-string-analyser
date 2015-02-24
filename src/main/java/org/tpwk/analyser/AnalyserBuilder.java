package org.tpwk.analyser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AnalyserBuilder
{
    private final List<String> patterns = new ArrayList<>();
    private PatternAnalyser.MatchingType matching = PatternAnalyser.MatchingType.ALL;
    private String name;

    public AnalyserBuilder()
    {
    }

    public AnalyserBuilder(final String name)
    {
        this.name = name;
    }

    public AnalyserBuilder withName(final String name)
    {
        this.name = name;
        return this;
    }

    public AnalyserBuilder matchAll()
    {
        this.matching = PatternAnalyser.MatchingType.ALL;
        return this;
    }

    public AnalyserBuilder matchOne()
    {
        this.matching = PatternAnalyser.MatchingType.ONE;
        return this;
    }

    public Analyser build()
    {
        if (name == null || patterns.size() == 0)
        {
            throw new IllegalArgumentException("Missing name or analysers");
        }

        return new PatternAnalyser(name, matching, (String[]) patterns.stream().toArray(String[]::new));
    }

    public AnalyserBuilder hasDigits()
    {
        patterns.add("\\p{Digit}+");
        return this;
    }

    public AnalyserBuilder hasDigits(final int count)
    {
        patterns.add(String.format("\\p{Digit}{%d}", count));
        return this;
    }

    public AnalyserBuilder hasDigits(final int from, final int to)
    {
        patterns.add(String.format("\\p{Digit}{%d,%d}", from, to));
        return this;
    }

    public AnalyserBuilder hasAlpha()
    {
        patterns.add("\\p{Alpha}");
        return this;
    }

    public AnalyserBuilder isEmpty()
    {
        patterns.add("^$");
        return this;
    }

    public AnalyserBuilder has(final String text)
    {
        patterns.add(Pattern.quote(text));
        return this;
    }

}
