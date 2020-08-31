package com.example.Restfuljdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RestfulJdbcApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(RestfulJdbcApplication.class);
	public static void main(String args[]) {
		SpringApplication.run(RestfulJdbcApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {

		//log.info("Creating tables");
		DataSetup ds = new DataSetup(jdbcTemplate);
		ds.createAndDeleteTable();
	}
	@Bean
	public Docket swaggerDemoApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.Restfuljdbc")).build();
	}


}