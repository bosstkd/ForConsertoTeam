package com.jmag.projet.infrastructure.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableAutoConfiguration
@ComponentScan(value = { "com.jmag.projet" })
public class ApplicationConfiguration {
}
