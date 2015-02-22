package org.tpwk;

import org.tpwk.analyser.*;
import org.tpwk.analysis.AnalysisFormatter;
import org.tpwk.analysis.OutputStreamFormatter;
import org.tpwk.source.FileSource;
import org.tpwk.source.StringSource;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class App
{
    public static void main(final String... args) throws Exception
    {
        final AnalysisFormatter formatter = new OutputStreamFormatter();

        final Analyser[] analysers = new Analyser[]{
                new NumericAnalyser(), new LowerAlphaAnalyzer(), new LengthAnalyser()
        };

        final GroupAnalyser group = GroupAnalyser.withName("Test Group")
                .withAnalyzers(NumericAnalyser.class, LowerAlphaAnalyzer.class)
                .withAnalyzers(new LengthAnalyser())
                .build();

        try (final StringSource source = new FileSource(Paths.get("passwords.txt"));
             final Stream<String> strings = source.strings())
        {
            strings.forEach((input) -> {
                Arrays.stream(analysers).forEach(a -> a.analyse(input));
                group.analyse(input);
            });
        }

        for (final Analyser analyser : analysers)
        {
            formatter.format(analyser.getAnalysis());
        }

        System.out.println();

        group.getAnalysis();
    }
}
