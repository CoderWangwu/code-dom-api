package com.code.dom.java.utils;

import com.code.dom.java.define.TopLevelClass;
import com.code.dom.java.define.TopLevelEnumeration;
import com.code.dom.java.define.CompilationUnit;
import com.code.dom.java.define.CompilationUnitVisitor;
import com.code.dom.java.define.Interface;
import com.code.dom.java.render.TopLevelClassRenderer;
import com.code.dom.java.render.TopLevelEnumerationRenderer;
import com.code.dom.java.render.TopLevelInterfaceRenderer;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:00
 */
public class DefaultJavaFormatter implements JavaFormatter, CompilationUnitVisitor<String> {


    @Override
    public String getFormattedContent(CompilationUnit compilationUnit) {
        return compilationUnit.accept(this);
    }


    @Override
    public String visit(TopLevelClass topLevelClass) {
        return new TopLevelClassRenderer().render(topLevelClass);
    }

    @Override
    public String visit(TopLevelEnumeration topLevelEnumeration) {
        return new TopLevelEnumerationRenderer().render(topLevelEnumeration);
    }

    @Override
    public String visit(Interface topLevelInterface) {
        return new TopLevelInterfaceRenderer().render(topLevelInterface);
    }
}