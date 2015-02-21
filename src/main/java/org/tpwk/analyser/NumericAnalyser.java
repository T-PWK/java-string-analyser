package org.tpwk.analyser;

public class NumericAnalyser extends PatternAnalyser
{
    public NumericAnalyser()
    {
        super("numeric", "^\\p{Digit}+$");
    }
}
