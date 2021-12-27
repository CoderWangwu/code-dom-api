package com.code.dom.java.define;

import com.code.dom.java.render.TypeParameterRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午9:57
 */
public class TypeParameter {

    private final String name;

    private final List<FullyQualifiedJavaType> extendsTypes = new ArrayList<>();

    public TypeParameter(String name) {
        super();
        this.name = name;
    }

    public TypeParameter(String name, List<FullyQualifiedJavaType> extendsTypes) {
        super();
        this.name = name;
        this.extendsTypes.addAll(extendsTypes);
    }

    public String getName() {
        return name;
    }

    public List<FullyQualifiedJavaType> getExtendsTypes() {
        return extendsTypes;
    }

    @Override
    public String toString() {
        return new TypeParameterRenderer().render(this, null);
    }
}
