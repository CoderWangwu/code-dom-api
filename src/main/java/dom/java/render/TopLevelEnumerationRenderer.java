package dom.java.render;

import dom.java.TopLevelEnumeration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static dom.java.render.RenderingUtilities.renderImports;
import static dom.java.render.RenderingUtilities.renderInnerEnumNoIndent;
import static dom.java.render.RenderingUtilities.renderPackage;
import static dom.java.render.RenderingUtilities.renderStaticImports;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:13
 */
public class TopLevelEnumerationRenderer {
    public String render(TopLevelEnumeration topLevelEnumeration) {
        List<String> lines = new ArrayList<>();

        lines.addAll(topLevelEnumeration.getFileCommentLines());
        lines.addAll(renderPackage(topLevelEnumeration));
        lines.addAll(renderStaticImports(topLevelEnumeration));
        lines.addAll(renderImports(topLevelEnumeration));
        lines.addAll(renderInnerEnumNoIndent(topLevelEnumeration, topLevelEnumeration));

        return lines.stream()
                .collect(Collectors.joining(System.getProperty("line.separator"))); //$NON-NLS-1$
    }

}
