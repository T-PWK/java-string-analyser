package org.tpwk.analyser;

public class SpecialAnalyser extends PatternAnalyser
{
    public SpecialAnalyser()
    {
        super("special", "^[\\p{Punct}]+$");
    }
}
