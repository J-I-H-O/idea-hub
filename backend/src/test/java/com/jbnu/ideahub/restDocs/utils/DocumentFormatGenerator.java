package com.jbnu.ideahub.restDocs.utils;

import org.springframework.restdocs.snippet.Attributes;

import static org.springframework.restdocs.snippet.Attributes.key;

public interface DocumentFormatGenerator {

    static Attributes.Attribute getDatetimeFormat() {
        return key("format").value("yyyy-MM-dd HH:mm:ss");
    }
}
