package org.tpwk.analyser;

public class UpperAlphaSpecialAnalyser extends PatternAnalyser
{
    public UpperAlphaSpecialAnalyser()
    {
        super("upperalphaspecial", "^[\\p{Upper}\\p{Punct}]+$", "\\p{Upper}+", "\\p{Punct}+");
    }
}
