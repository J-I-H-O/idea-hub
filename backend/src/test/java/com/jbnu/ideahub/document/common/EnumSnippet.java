package com.jbnu.ideahub.document.common;

import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.Operation;
import org.springframework.restdocs.payload.AbstractFieldsSnippet;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 기본 템플릿이 아닌 custom 템플릿을 사용하기 위한 클래스
 * type과 일치하는 template을 선택해서 동작
 **/
public class EnumSnippet extends AbstractFieldsSnippet {

    public EnumSnippet(String type, PayloadSubsectionExtractor<?> subsectionExtractor,
                       List<FieldDescriptor> descriptors, Map<String, Object> attributes,
                       boolean ignoreUndocumentedFields) {
        super(type, descriptors, attributes, ignoreUndocumentedFields, subsectionExtractor);
    }

    @Override
    protected MediaType getContentType(Operation operation) {
        return operation.getResponse().getHeaders().getContentType();
    }

    @Override
    protected byte[] getContent(Operation operation) throws IOException {
        return operation.getResponse().getContent();
    }
}
