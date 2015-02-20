package org.tpwk.analyser;

import org.tpwk.analysis.AnalysisResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GroupAnalyser implements Analyser
{
    public static class GroupAnalyserBuilder
    {
        private String name;
        private List<Analyser> analysers = new ArrayList<>();

        public GroupAnalyser build()
        {
            if (name == null || name.length() == 0)
            {
                throw new IllegalArgumentException("Missing group name");
            }

            if (analysers.size() == 0)
            {
                throw new IllegalArgumentException("Missing analyzers");
            }

            return new GroupAnalyser(name, analysers);
        }

        public GroupAnalyserBuilder setName(final String name)
        {
            this.name = name;

            return this;
        }

        public GroupAnalyserBuilder addAnalyzers(final Analyser... analyzers)
        {
            this.analysers.addAll(Arrays.asList(analyzers));

            return this;
        }

        public GroupAnalyserBuilder addAnalyzers(final Class<? extends Analyser>... analyzers)
        {
            this.analysers.addAll(
                    Arrays.stream(analyzers).map(GroupAnalyserBuilder::creteInstance).collect(Collectors.toList()));

            return this;
        }

        private static Analyser creteInstance(final Class<? extends Analyser> analyserClass)
        {
            try
            {
                return analyserClass.newInstance();
            }
            catch (InstantiationException | IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private final String name;

    private final List<Analyser> analysers;

    public GroupAnalyser(final String name, final List<Analyser> analysers)
    {
        this.name = name;
        this.analysers = new ArrayList<>(analysers);
    }

    public static GroupAnalyserBuilder withName(final String name)
    {
        return new GroupAnalyserBuilder().setName(name);
    }

    public static GroupAnalyserBuilder withAnalyzers(final Analyser... analyzers)
    {
        return new GroupAnalyserBuilder().addAnalyzers(analyzers);
    }

    public static GroupAnalyserBuilder withAnalyzers(final Class<Analyser>... analyzers)
    {
        return new GroupAnalyserBuilder().addAnalyzers(analyzers);
    }

    @Override
    public void analyse(final String input)
    {
        analysers.forEach(a -> a.analyse(input));
    }

    @Override
    public AnalysisResult getAnalysis()
    {
        // TODO: temporary implementation
        this.analysers.forEach(a -> System.out.printf("Analysis:%s%n\t%s - %d%n%n",
                this.name, a.getAnalysis().getResults(), a.getAnalysis().getTotal()));

        return null;
    }
}
