package dom.java.render;

import dom.java.Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static dom.java.render.RenderingUtilities.renderImports;
import static dom.java.render.RenderingUtilities.renderInnerInterfaceNoIndent;
import static dom.java.render.RenderingUtilities.renderPackage;
import static dom.java.render.RenderingUtilities.renderStaticImports;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:13
 */
public class TopLevelInterfaceRenderer {
    public String render(Interface topLevelInterface) {
        List<String> lines = new ArrayList<>();

        lines.addAll(topLevelInterface.getFileCommentLines());
        lines.addAll(renderPackage(topLevelInterface));
        lines.addAll(renderStaticImports(topLevelInterface));
        lines.addAll(renderImports(topLevelInterface));
        lines.addAll(renderInnerInterfaceNoIndent(topLevelInterface, topLevelInterface));

        return lines.stream()
                .collect(Collectors.joining(System.getProperty("line.separator"))); //$NON-NLS-1$
    }
}
