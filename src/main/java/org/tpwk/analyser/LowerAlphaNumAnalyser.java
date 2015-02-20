package org.tpwk.analyser;

public class LowerAlphaNumAnalyser extends PatternAnalyser
{
    public LowerAlphaNumAnalyser()
    {
        super("loweralphanum", "^[\\p{Lower}\\p{Digit}]+$", "\\p{Digit}+", "\\p{Lower}+");
    }
}
