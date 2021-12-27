package com.code.dom.java.render;

import com.code.dom.java.CompilationUnit;
import com.code.dom.java.utils.CustomCollectors;
import com.code.dom.java.InnerInterface;
import com.code.dom.java.JavaDomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:09
 */
public class InnerInterfaceRenderer {
    public List<String> render(InnerInterface innerInterface, CompilationUnit compilationUnit) {
        List<String> lines = new ArrayList<>();

        lines.addAll(innerInterface.getJavaDocLines());
        lines.addAll(innerInterface.getAnnotations());
        lines.add(renderFirstLine(innerInterface, compilationUnit));
        lines.addAll(RenderingUtilities.renderFields(innerInterface.getFields(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInterfaceMethods(innerInterface.getMethods(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInnerClasses(innerInterface.getInnerClasses(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInnerInterfaces(innerInterface.getInnerInterfaces(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInnerEnums(innerInterface.getInnerEnums(), compilationUnit));

        lines = RenderingUtilities.removeLastEmptyLine(lines);

        lines.add("}"); //$NON-NLS-1$

        return lines;
    }

    private String renderFirstLine(InnerInterface innerInterface, CompilationUnit compilationUnit) {
        StringBuilder sb = new StringBuilder();

        sb.append(innerInterface.getVisibility().getValue());

        if (innerInterface.isStatic()) {
            sb.append("static "); //$NON-NLS-1$
        }

        sb.append("interface "); //$NON-NLS-1$
        sb.append(innerInterface.getType().getShortName());
        sb.append(RenderingUtilities.renderTypeParameters(innerInterface.getTypeParameters(), compilationUnit));
        sb.append(renderSuperInterfaces(innerInterface, compilationUnit));
        sb.append(" {"); //$NON-NLS-1$

        return sb.toString();
    }

    // should return an empty string if no super interfaces
    private String renderSuperInterfaces(InnerInterface innerInterface, CompilationUnit compilationUnit) {
        return innerInterface.getSuperInterfaceTypes().stream()
                .map(tp -> JavaDomUtils.calculateTypeName(compilationUnit, tp))
                .collect(CustomCollectors.joining(", ", " extends ", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
}
