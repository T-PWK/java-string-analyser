package org.tpwk.analyser;

public class NumericAnalyzer extends PatternAnalyser
{
    public NumericAnalyzer()
    {
        super("numeric", "^\\p{Digit}+$");
    }
}
