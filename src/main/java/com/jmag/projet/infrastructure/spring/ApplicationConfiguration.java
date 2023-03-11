package com.jmag.projet.infrastructure.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Slf4j
@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "com.jmag.projet.infrastructure.persistance")
@ComponentScan(value = { "com.jmag.projet" })
public class ApplicationConfiguration {
}
