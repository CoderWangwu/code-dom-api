package com.code.dom.java.render;

import com.code.dom.java.define.TopLevelClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:12
 */
public class TopLevelClassRenderer {
    public String render(TopLevelClass topLevelClass) {
        List<String> lines = new ArrayList<>();

        lines.addAll(topLevelClass.getFileCommentLines());
        lines.addAll(RenderingUtilities.renderPackage(topLevelClass));
        lines.addAll(RenderingUtilities.renderStaticImports(topLevelClass));
        lines.addAll(RenderingUtilities.renderImports(topLevelClass));
        lines.addAll(RenderingUtilities.renderInnerClassNoIndent(topLevelClass, topLevelClass));

        return lines.stream()
                .collect(Collectors.joining(System.getProperty("line.separator"))); //$NON-NLS-1$
    }
}
