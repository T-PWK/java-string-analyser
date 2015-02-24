package org.tpwk.analyser;

import org.tpwk.analysis.AnalysisResult;
import org.tpwk.analysis.SimpleAnalysisResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class PatternAnalyser implements Analyser
{
    public static enum MatchingType
    {
        /**
         * All patterns must markAsMatch an input string
         */
        ALL,

        /**
         * At least one pattern much markAsMatch an input string
         */
        ONE
    }

    private final String name;

    private final MatchingType type;

    private final Pattern[] patterns;

    private int total = 0;

    private int match = 0;

    public PatternAnalyser(final String name, final Pattern... patterns)
    {
        this(name, MatchingType.ALL, patterns);
    }

    public PatternAnalyser(final String name, final String... patterns)
    {
        this(name, MatchingType.ALL, patterns);
    }

    public PatternAnalyser(final String name, final MatchingType type, final String... patterns)
    {
        this.type = type;
        this.name = name;
        this.patterns = stringToPattern(patterns);
    }

    public PatternAnalyser(final String name, final MatchingType type, final Pattern... patterns)
    {
        this.patterns = patterns;
        this.type = type;
        this.name = name;
    }

    private Pattern[] stringToPattern(String... patterns)
    {
        final Pattern[] patternSet = new Pattern[patterns.length];

        for (int i = 0; i < patterns.length; i++)
        {
            patternSet[i] = Pattern.compile(patterns[i]);
        }

        return patternSet;
    }


    public String getName()
    {
        return name;
    }

    @Override
    public AnalysisResult getAnalysis()
    {
        final Map<String, Integer> result = new HashMap<>();
        result.put(getName(), match);

        return new SimpleAnalysisResult(getName(), total, result);
    }


    @Override
    public void analyse(final String input)
    {
        Objects.requireNonNull(input);

        total++;
        int matches = 0;

        for (final Pattern pattern : patterns)
        {
            if (checkIfMatches(pattern, input))
            {
                if (type == MatchingType.ONE)
                {
                    markAsMatch();
                    return;
                }

                matches++;
            }
            else if (type == MatchingType.ALL)
            {
                return;
            }
        }

        // Check if all patterns markAsMatch the given input string
        if (type == MatchingType.ALL && patterns.length == matches)
        {
            markAsMatch();
        }
    }

    protected boolean checkIfMatches(final Pattern pattern, final String input)
    {
        return pattern.matcher(input).find();
    }

    private void markAsMatch()
    {
        match++;
    }


}
