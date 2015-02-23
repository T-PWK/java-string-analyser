package org.tpwk.analyser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnalyserBuilder
{
    private final List<String> patterns = new ArrayList<>();
    private String name;

    public static AnalyserBuilder create()
    {
        return new AnalyserBuilder();
    }

    public AnalyserBuilder withName(final String name)
    {
        this.name = name;
        return this;
    }

    public Analyser build()
    {
        return new PatternAnalyser(
                Objects.requireNonNull(name), (String[]) patterns.stream().toArray(size -> new String[size]));
    }

    public AnalyserBuilder hasDigits()
    {
        patterns.add("\\p{Digits}+");
        return this;
    }

    public AnalyserBuilder hasDigits(final int count)
    {
        patterns.add(String.format("\\p{Digits}{%d}", count));
        return this;
    }

    public AnalyserBuilder hasDigits(final int from, final int to)
    {
        patterns.add(String.format("\\p{Digits}{%d,%d}", from, to));
        return this;
    }
}
