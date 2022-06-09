package com.ljf.router.config;

import com.ljf.router.util.PropertyUtil;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
public class DataSourcesAutoConfig implements EnvironmentAware {
    private Map<String,Map<String,Object>> datasourceMap = new HashMap<>();

    private Map<String, Object> defaultDataSource;

    private int databaseCount;

    private int tableCount;

    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "JRouter.jdbc.datasource";

        databaseCount = Integer.parseInt(Objects.requireNonNull(environment.getProperty(prefix + "dbCount")));
        tableCount = Integer.parseInt(Objects.requireNonNull(environment.getProperty(prefix + "tbCount")));

        String dataSources = environment.getProperty(prefix + "list");

        assert dataSources != null;

        for (String dbInfo : dataSources.split(",")) {
            HashMap<String,Object> dataSourcesProperty = PropertyUtil.handle(environment,prefix + dbInfo,HashMap.class);
            datasourceMap.put(dbInfo , dataSourcesProperty);
        }

        String defaultDB = environment.getProperty(prefix + "default");
        defaultDataSource = PropertyUtil.handle(environment,prefix + defaultDB,HashMap.class);
    }
}
