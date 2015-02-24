package org.tpwk.analyser;

import org.junit.Test;

public class MaskAnalyserExceptionTest
{
    @Test(expected = NullPointerException.class)
    public void testNullMask()
    {
        new MaskAnalyser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMask()
    {
        new MaskAnalyser("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMaskToken()
    {
        new MaskAnalyser("?l?l?L");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaskWithNoTokens()
    {
        new MaskAnalyser("ll");
    }
}
