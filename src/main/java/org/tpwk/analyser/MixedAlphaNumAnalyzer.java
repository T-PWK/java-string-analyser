package org.tpwk.analyser;

public class MixedAlphaNumAnalyzer extends PatternAnalyser
{
    public MixedAlphaNumAnalyzer()
    {
        super("mixedalphanum", "^[\\p{Alnum}]+$", "\\p{Lower}+", "\\p{Upper}+", "\\p{Digit}+");
    }
}
