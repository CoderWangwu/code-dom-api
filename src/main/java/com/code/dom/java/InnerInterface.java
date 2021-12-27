package com.code.dom.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午9:47
 */
public class InnerInterface extends AbstractJavaType {
    private final List<TypeParameter> typeParameters = new ArrayList<>();

    public InnerInterface(FullyQualifiedJavaType type) {
        super(type);
    }

    public InnerInterface(String type) {
        super(type);
    }

    public List<TypeParameter> getTypeParameters() {
        return this.typeParameters;
    }

    public void addTypeParameter(TypeParameter typeParameter) {
        this.typeParameters.add(typeParameter);
    }
}
