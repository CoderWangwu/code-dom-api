package com.code.dom.java.render;

import com.code.dom.java.CompilationUnit;
import com.code.dom.java.Field;
import com.code.dom.java.JavaDomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:06
 */
public class FieldRenderer {
    public List<String> render(Field field, CompilationUnit compilationUnit) {
        List<String> lines = new ArrayList<>();

        lines.addAll(field.getJavaDocLines());
        lines.addAll(field.getAnnotations());
        lines.add(renderField(field, compilationUnit));

        return lines;
    }

    private String renderField(Field field, CompilationUnit compilationUnit) {
        StringBuilder sb = new StringBuilder();
        sb.append(field.getVisibility().getValue());

        if (field.isStatic()) {
            sb.append("static "); //$NON-NLS-1$
        }

        if (field.isFinal()) {
            sb.append("final "); //$NON-NLS-1$
        }

        if (field.isTransient()) {
            sb.append("transient "); //$NON-NLS-1$
        }

        if (field.isVolatile()) {
            sb.append("volatile "); //$NON-NLS-1$
        }

        sb.append(JavaDomUtils.calculateTypeName(compilationUnit, field.getType()));
        sb.append(' ');
        sb.append(field.getName());
        sb.append(renderInitializationString(field));
        sb.append(';');

        return sb.toString();
    }

    private String renderInitializationString(Field field) {
        return field.getInitializationString()
                .map(is -> " = " + is) //$NON-NLS-1$
                .orElse(""); //$NON-NLS-1$
    }
}
