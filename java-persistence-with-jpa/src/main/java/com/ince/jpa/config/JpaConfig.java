package com.ince.jpa.config;

import com.ince.jpa.entity.Employee;
import org.hibernate.cfg.AvailableSettings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JpaConfig {

    public static Map<String, Object> getProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(AvailableSettings.DRIVER, "org.postgresql.Driver");
        props.put(AvailableSettings.URL, "jdbc:postgresql://localhost:5432/postgres");
        props.put(AvailableSettings.USER, "sa");
        props.put(AvailableSettings.PASS, "s3cr3t");
        props.put(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        props.put(AvailableSettings.HBM2DDL_AUTO, "update");
        props.put(AvailableSettings.SHOW_SQL, "true");

        props.put(AvailableSettings.LOADED_CLASSES, List.of(Employee.class));
        return props;
    }
}
