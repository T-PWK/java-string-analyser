package org.tpwk.analyser;

public class UpperAlphaNumAnalyser extends PatternAnalyser
{
    public UpperAlphaNumAnalyser()
    {
        super("upperalphanum", "^[\\p{Upper}\\p{Digit}]+$", "\\p{Upper}+", "\\p{Digit}+");
    }
}
