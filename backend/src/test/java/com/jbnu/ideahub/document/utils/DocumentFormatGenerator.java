package com.jbnu.ideahub.document.utils;

import org.springframework.restdocs.snippet.Attributes;

import static org.springframework.restdocs.snippet.Attributes.key;

public interface DocumentFormatGenerator {

    static Attributes.Attribute getDatetimeFormat() {
        return key("format").value("yyyy-MM-dd HH:mm:ss");
    }

    static Attributes.Attribute getEnumFormat() {
        return key("format").value("아래 Enumeration Constants 참고");
    }
}
