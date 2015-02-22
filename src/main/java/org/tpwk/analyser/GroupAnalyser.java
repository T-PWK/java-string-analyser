package org.tpwk.analyser;

import org.tpwk.analysis.AnalysisResult;
import org.tpwk.analysis.SimpleAnalysisResult;

import java.util.*;
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

        public GroupAnalyserBuilder withName(final String name)
        {
            this.name = name;

            return this;
        }

        public GroupAnalyserBuilder withAnalyzers(final Analyser... analyzers)
        {
            this.analysers.addAll(Arrays.asList(analyzers));

            return this;
        }

        public GroupAnalyserBuilder withAnalyzers(final Class<? extends Analyser>... analyzers)
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

    private int total;

    private final String name;

    private final List<Analyser> analysers;

    public GroupAnalyser(final String name, final List<Analyser> analysers)
    {
        this.name = name;
        this.analysers = new ArrayList<>(analysers);
    }

    public static GroupAnalyserBuilder withName(final String name)
    {
        return new GroupAnalyserBuilder().withName(name);
    }

    public static GroupAnalyserBuilder withAnalyzers(final Analyser... analyzers)
    {
        return new GroupAnalyserBuilder().withAnalyzers(analyzers);
    }

    public static GroupAnalyserBuilder withAnalyzers(final Class<? extends Analyser>... analyzers)
    {
        return new GroupAnalyserBuilder().withAnalyzers(analyzers);
    }

    @Override
    public void analyse(final String input)
    {
        total++;
        analysers.forEach(a -> a.analyse(input));
    }

    @Override
    public AnalysisResult getAnalysis()
    {
        final Map<String, Integer> values = new HashMap<>();
        analysers.forEach(a -> values.putAll(a.getAnalysis().getResults()));

        return new SimpleAnalysisResult(name, total, values);
    }
}
