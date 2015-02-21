package org.tpwk.analyser;

public class MixedAlphaNumAnalyser extends PatternAnalyser
{
    public MixedAlphaNumAnalyser()
    {
        super("mixedalphanum", "^[\\p{Alnum}]+$", "\\p{Lower}+", "\\p{Upper}+", "\\p{Digit}+");
    }
}
