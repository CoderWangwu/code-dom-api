package com.code.dom.java.render;

import com.code.dom.java.define.TopLevelEnumeration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:13
 */
public class TopLevelEnumerationRenderer {
    public String render(TopLevelEnumeration topLevelEnumeration) {
        List<String> lines = new ArrayList<>();

        lines.addAll(topLevelEnumeration.getFileCommentLines());
        lines.addAll(RenderingUtilities.renderPackage(topLevelEnumeration));
        lines.addAll(RenderingUtilities.renderStaticImports(topLevelEnumeration));
        lines.addAll(RenderingUtilities.renderImports(topLevelEnumeration));
        lines.addAll(RenderingUtilities.renderInnerEnumNoIndent(topLevelEnumeration, topLevelEnumeration));

        return lines.stream()
                .collect(Collectors.joining(System.getProperty("line.separator"))); //$NON-NLS-1$
    }

}
