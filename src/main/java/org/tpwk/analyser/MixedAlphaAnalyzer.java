package org.tpwk.analyser;

public class MixedAlphaAnalyzer extends PatternAnalyser
{
    public MixedAlphaAnalyzer()
    {
        super("mixedalpha", "^[\\p{Alpha}]+$", "\\p{Lower}", "\\p{Upper}");
    }
}
