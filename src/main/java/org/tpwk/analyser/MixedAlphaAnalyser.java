package org.tpwk.analyser;

public class MixedAlphaAnalyser extends PatternAnalyser
{
    public MixedAlphaAnalyser()
    {
        super("mixedalpha", "^[\\p{Alpha}]+$", "\\p{Lower}", "\\p{Upper}");
    }
}
