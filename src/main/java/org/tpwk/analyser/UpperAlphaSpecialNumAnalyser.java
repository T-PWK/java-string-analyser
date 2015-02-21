package org.tpwk.analyser;

public class UpperAlphaSpecialNumAnalyser extends PatternAnalyser
{
    public UpperAlphaSpecialNumAnalyser()
    {
        super("upperalphaspecialnum", "^[\\p{Upper}\\p{Digit}\\p{Punct}]+$", "\\p{Upper}+", "\\p{Digit}+", "\\p{Punct}+");
    }
}
