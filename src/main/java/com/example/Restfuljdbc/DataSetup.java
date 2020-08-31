package com.example.Restfuljdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DataSetup {
    JdbcTemplate jdbcTemplate;

    DataSetup(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public void createAndDeleteTable() {
        jdbcTemplate.execute("DROP TABLE customers IF EXISTS"); // sql command
        jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
    }
}
