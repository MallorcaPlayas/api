package org.example.apirest.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

@Configuration
public class SAXConfi {

    @Bean
    @SneakyThrows
    public SAXParser saxParser() {
        return SAXParserFactory.newInstance().newSAXParser();
    }
}
