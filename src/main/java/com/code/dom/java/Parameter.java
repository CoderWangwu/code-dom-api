package com.code.dom.java;

import com.code.dom.java.render.ParameterRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午9:52
 */
public class Parameter {
    private final String name;
    private final FullyQualifiedJavaType type;
    private final boolean isVarargs;

    private final List<String> annotations = new ArrayList<>();

    public Parameter(FullyQualifiedJavaType type, String name, boolean isVarargs) {
        this.name = name;
        this.type = type;
        this.isVarargs = isVarargs;
    }

    public Parameter(FullyQualifiedJavaType type, String name) {
        this(type, name, false);
    }

    public Parameter(FullyQualifiedJavaType type, String name, String annotation) {
        this(type, name, false);
        addAnnotation(annotation);
    }

    public Parameter(FullyQualifiedJavaType type, String name, String annotation, boolean isVarargs) {
        this(type, name, isVarargs);
        addAnnotation(annotation);
    }

    public String getName() {
        return name;
    }

    public FullyQualifiedJavaType getType() {
        return type;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void addAnnotation(String annotation) {
        annotations.add(annotation);
    }

    @Override
    public String toString() {
        return new ParameterRenderer().render(this, null);
    }

    public boolean isVarargs() {
        return isVarargs;
    }
}

