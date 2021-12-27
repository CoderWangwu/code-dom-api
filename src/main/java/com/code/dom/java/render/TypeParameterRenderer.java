package com.code.dom.java.render;

import com.code.dom.java.define.CompilationUnit;
import com.code.dom.java.define.TypeParameter;
import com.code.dom.java.utils.CustomCollectors;
import com.code.dom.java.define.JavaDomUtils;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:14
 */
public class TypeParameterRenderer {
    public String render(TypeParameter typeParameter, CompilationUnit compilationUnit) {
        return typeParameter.getName() + typeParameter.getExtendsTypes().stream()
                .map(t -> JavaDomUtils.calculateTypeName(compilationUnit, t))
                .collect(CustomCollectors.joining(" & ", " extends ", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
}
