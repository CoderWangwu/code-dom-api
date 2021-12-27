package com.code.dom.java.render;

import com.code.dom.java.define.Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:13
 */
public class TopLevelInterfaceRenderer {
    public String render(Interface topLevelInterface) {
        List<String> lines = new ArrayList<>();

        lines.addAll(topLevelInterface.getFileCommentLines());
        lines.addAll(RenderingUtilities.renderPackage(topLevelInterface));
        lines.addAll(RenderingUtilities.renderStaticImports(topLevelInterface));
        lines.addAll(RenderingUtilities.renderImports(topLevelInterface));
        lines.addAll(RenderingUtilities.renderInnerInterfaceNoIndent(topLevelInterface, topLevelInterface));

        return lines.stream()
                .collect(Collectors.joining(System.getProperty("line.separator"))); //$NON-NLS-1$
    }
}
