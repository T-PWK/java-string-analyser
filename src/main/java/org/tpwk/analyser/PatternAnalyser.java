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
         * All patterns must match an input string
         */
        ALL,

        /**
         * At least one pattern much match an input string
         */
        ONE
    }

    private final String code;

    private final MatchingType type;

    private final Pattern[] patterns;

    private int total = 0;

    private int match = 0;

    public PatternAnalyser(final String code, final Pattern... patterns)
    {
        this(code, MatchingType.ALL, patterns);
    }

    public PatternAnalyser(final String code, final String... patterns)
    {
        this(code, MatchingType.ALL, patterns);
    }

    public PatternAnalyser(final String code, final MatchingType type, final String... patterns)
    {
        this.type = type;
        this.code = code;
        this.patterns = stringToPattern(patterns);
    }

    public PatternAnalyser(final String code, final MatchingType type, final Pattern... patterns)
    {
        this.patterns = patterns;
        this.type = type;
        this.code = code;
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

    protected void match()
    {
        match++;
    }

    @Override
    public void analyse(final String input)
    {
        Objects.requireNonNull(input);

        total++;
        int matches = 0;

        for (final Pattern pattern : patterns)
        {
            if (pattern.matcher(input).find())
            {
                if (type == MatchingType.ONE)
                {
                    match();
                    return;
                }

                matches++;
            }
            else if (type == MatchingType.ALL)
            {
                return;
            }
        }

        // Check if all patterns match the given input string
        if (type == MatchingType.ALL && patterns.length == matches)
        {
            match();
        }
    }

    @Override
    public AnalysisResult getAnalysis()
    {
        final Map<String, Integer> result = new HashMap<>();
        result.put(getCode(), match);

        return new SimpleAnalysisResult(total, result);
    }

    public String getCode()
    {
        return code;
    }
}
