package org.tpwk.analyser;

public class SpecialNumAnalyser extends PatternAnalyser
{
    public SpecialNumAnalyser()
    {
        super("specialnum", "^[\\p{Punct}\\p{Digit}]+$", "\\p{Digit}+", "\\p{Punct}+");
    }

}
