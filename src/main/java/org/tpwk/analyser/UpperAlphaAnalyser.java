package org.tpwk.analyser;

public class UpperAlphaAnalyser extends PatternAnalyser
{
    public UpperAlphaAnalyser()
    {
        super("upperalpha", MatchingType.ONE, "^\\p{Upper}+$");
    }
}
