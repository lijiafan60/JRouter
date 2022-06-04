package com.ljf.router.config;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    private Map<String,Map<String,Object>> datasourceMap = new HashMap<>();

    private int databaseCount;

    private int tableCount;


}
