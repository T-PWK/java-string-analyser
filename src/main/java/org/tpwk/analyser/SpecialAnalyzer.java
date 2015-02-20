package org.tpwk.analyser;

public class SpecialAnalyzer extends PatternAnalyser
{
    public SpecialAnalyzer()
    {
        super("special", "^[\\p{Punct}]+$");
    }
}
