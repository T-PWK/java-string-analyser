package org.tpwk.analyser;

import java.util.*;

public class Suite implements Analysable
{
    public static class SuiteBuilder
    {
        private Map<String, GroupAnalyser.GroupAnalyserBuilder> groups = new LinkedHashMap<>();

        public SuiteBuilder setName(final String group, final String name)
        {
            getGroup(group).withName(name);
            return this;
        }

        public SuiteBuilder addAnalysers(final String code, final Analyser... analysers)
        {
            getGroup(code).withAnalysers(analysers);
            return this;
        }

        public SuiteBuilder addAnalysers(final String code, final Class<? extends Analyser>... analysers)
        {
            getGroup(code).withAnalysers(analysers);
            return this;
        }

        public SuiteBuilder group(final String code, final String name, final Analyser... analysers)
        {
            getGroup(code).withName(name).withAnalysers(analysers);
            return this;
        }

        public SuiteBuilder group(final String code, final String name, final Class<? extends Analyser>... analysers)
        {
            getGroup(code).withName(name).withAnalysers(analysers);
            return this;
        }


        public Suite build()
        {
            return new Suite(groups.entrySet().stream()
                    .map(entry -> entry.getValue().build()).toArray(size -> new Analyser[size]));
        }

        private GroupAnalyser.GroupAnalyserBuilder getGroup(final String code)
        {
            if (!groups.containsKey(code))
            {
                groups.put(code, new GroupAnalyser.GroupAnalyserBuilder());
            }
            return groups.get(code);
        }
    }

    final List<Analyser> analysers = new ArrayList<>();

    public Suite(final Analyser... analysers)
    {
        this.analysers.addAll(Arrays.asList(analysers));
    }

    public static SuiteBuilder group(final String code, final String name, final Analyser... analysers)
    {
        return new SuiteBuilder().setName(code, name).addAnalysers(code, analysers);
    }

    public static SuiteBuilder group(final String code, final String name, final Class<? extends Analyser>... analysers)
    {
        final SuiteBuilder builder = new SuiteBuilder();
        return builder.setName(code, name).addAnalysers(code, analysers);
    }

    public static SuiteBuilder setName(final String code, final String name)
    {
        final SuiteBuilder builder = new SuiteBuilder();
        return builder.setName(code, name);
    }

    public static SuiteBuilder addAnalysers(final String code, final Analyser... analysers)
    {
        final SuiteBuilder builder = new SuiteBuilder();
        return builder.addAnalysers(code, analysers);
    }

    public static SuiteBuilder addAnalysers(final String code, final Class<? extends Analyser>... analysers)
    {
        final SuiteBuilder builder = new SuiteBuilder();
        return builder.addAnalysers(code, analysers);
    }

    @Override
    public void analyse(final String input)
    {
        analysers.forEach(a -> a.analyse(input));
    }

    public Analyser[] analysers()
    {
        return analysers.stream().toArray(size -> new Analyser[size]);
    }
}
