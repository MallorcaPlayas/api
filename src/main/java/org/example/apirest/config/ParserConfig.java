package org.example.apirest.config;

import lombok.SneakyThrows;
import org.example.apirest.utils.RouteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

@Configuration
public class ParserConfig {

    @SneakyThrows
    @Bean
    public SAXParser saxParser() {
        return SAXParserFactory.newInstance().newSAXParser();
    }
}
