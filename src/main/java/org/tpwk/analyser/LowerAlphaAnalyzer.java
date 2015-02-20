package org.tpwk.analyser;

public class LowerAlphaAnalyzer extends PatternAnalyser
{
    public LowerAlphaAnalyzer()
    {
        super("loweralpha", MatchingType.ONE, "^[\\p{Lower}]+$");
    }
}
