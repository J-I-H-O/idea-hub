package com.jbnu.ideahub.document.enumDocs;

import com.jbnu.ideahub.common.domain.EnumType;
import com.jbnu.ideahub.competition.domain.CompetitionStatus;
import com.jbnu.ideahub.document.common.EnumSnippet;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.util.Arrays;
import java.util.Map;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public interface EnumSnippetGenerator {

    static EnumSnippet generateEnumSnippet(String type, PayloadSubsectionExtractor<?> subsectionExtractor,
                                            Map<String, Object> attributes, FieldDescriptor... descriptors) {
        return new EnumSnippet(type, subsectionExtractor, Arrays.asList(descriptors), attributes, true);
    }

    static FieldDescriptor[] enumConvertFieldDescriptor(EnumType[] enumTypes) {
        return Arrays.stream(enumTypes)
                .map(enumType -> fieldWithPath(enumType.getName()).description(enumType.getDescription()))
                .toArray(FieldDescriptor[]::new);
    }
}
