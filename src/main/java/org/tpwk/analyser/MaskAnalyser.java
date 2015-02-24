package org.tpwk.analyser;

import java.util.regex.Pattern;

public class MaskAnalyser extends PatternAnalyser
{
    private final static Pattern MASK_PATTERN = Pattern.compile("^(\\?l|\\?u|\\?d|\\?s|\\?a)+$");

    public MaskAnalyser(final String mask)
    {
        super("mask", MatchingType.ONE, prepareMask(mask));
    }

    private static String prepareMask(final String mask)
    {
        if (!MASK_PATTERN.matcher(mask).matches())
        {
            throw new IllegalArgumentException("Invalid mask");
        }

        return mask.replace("?l", "\\p{Lower}")
                .replace("?u", "\\p{Upper}")
                .replace("?d", "\\p{Digit}")
                .replace("?s", "\\p{Punct}")
                .replace("?a", "\\p{Graph}");
    }

    @Override
    protected boolean checkIfMatches(final Pattern pattern, final String input)
    {
        return pattern.matcher(input).matches();
    }
}
