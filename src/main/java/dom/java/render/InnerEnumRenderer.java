package dom.java.render;

import dom.java.CompilationUnit;
import dom.java.InnerEnum;
import dom.java.JavaDomUtils;
import dom.java.utils.CustomCollectors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:08
 */
public class InnerEnumRenderer {
    public List<String> render(InnerEnum innerEnum, CompilationUnit compilationUnit) {
        List<String> lines = new ArrayList<>();

        lines.addAll(innerEnum.getJavaDocLines());
        lines.addAll(innerEnum.getAnnotations());
        lines.add(renderFirstLine(innerEnum, compilationUnit));
        lines.addAll(renderEnumConstants(innerEnum));
        lines.addAll(RenderingUtilities.renderFields(innerEnum.getFields(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInitializationBlocks(innerEnum.getInitializationBlocks()));
        lines.addAll(RenderingUtilities.renderClassOrEnumMethods(innerEnum.getMethods(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInnerClasses(innerEnum.getInnerClasses(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInnerInterfaces(innerEnum.getInnerInterfaces(), compilationUnit));
        lines.addAll(RenderingUtilities.renderInnerEnums(innerEnum.getInnerEnums(), compilationUnit));

        lines = RenderingUtilities.removeLastEmptyLine(lines);

        lines.add("}"); //$NON-NLS-1$

        return lines;
    }

    private String renderFirstLine(InnerEnum innerEnum, CompilationUnit compilationUnit) {
        StringBuilder sb = new StringBuilder();

        sb.append(innerEnum.getVisibility().getValue());

        if (innerEnum.isStatic()) {
            sb.append("static "); //$NON-NLS-1$
        }

        sb.append("enum "); //$NON-NLS-1$
        sb.append(innerEnum.getType().getShortName());
        sb.append(renderSuperInterfaces(innerEnum, compilationUnit));
        sb.append(" {"); //$NON-NLS-1$

        return sb.toString();
    }

    private List<String> renderEnumConstants(InnerEnum innerEnum) {
        List<String> answer = new ArrayList<>();

        Iterator<String> iter = innerEnum.getEnumConstants().iterator();
        while (iter.hasNext()) {
            String enumConstant = iter.next();

            if (iter.hasNext()) {
                answer.add(RenderingUtilities.JAVA_INDENT + enumConstant + ","); //$NON-NLS-1$
            } else {
                answer.add(RenderingUtilities.JAVA_INDENT + enumConstant + ";"); //$NON-NLS-1$
            }
        }

        answer.add(""); //$NON-NLS-1$
        return answer;
    }

    // should return an empty string if no super interfaces
    private String renderSuperInterfaces(InnerEnum innerEnum, CompilationUnit compilationUnit) {
        return innerEnum.getSuperInterfaceTypes().stream()
                .map(tp -> JavaDomUtils.calculateTypeName(compilationUnit, tp))
                .collect(CustomCollectors.joining(", ", " implements ", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
}