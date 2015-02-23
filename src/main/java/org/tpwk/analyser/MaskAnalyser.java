package org.tpwk.analyser;

import java.util.regex.Pattern;

public class MaskAnalyser extends PatternAnalyser
{
    private final static Pattern MASK_PATTERN = Pattern.compile("^(\\?l|\\?u|\\?d|\\?s|\\?a)+$");

    public MaskAnalyser(final String mask)
    {
        super("mask", ".+");
    }
}
