package com.code.dom.java.render;

import com.code.dom.java.Parameter;
import com.code.dom.java.utils.CustomCollectors;
import com.code.dom.java.CompilationUnit;
import com.code.dom.java.JavaDomUtils;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:10
 */
public class ParameterRenderer {
    public String render(Parameter parameter, CompilationUnit compilationUnit) {
        return renderAnnotations(parameter)
                + JavaDomUtils.calculateTypeName(compilationUnit, parameter.getType())
                + " " //$NON-NLS-1$
                + (parameter.isVarargs() ? "... " : "") //$NON-NLS-1$ //$NON-NLS-2$
                + parameter.getName();
    }

    // should return empty string if no annotations
    private String renderAnnotations(Parameter parameter) {
        return parameter.getAnnotations().stream()
                .collect(CustomCollectors.joining(" ", "", " ")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
}
