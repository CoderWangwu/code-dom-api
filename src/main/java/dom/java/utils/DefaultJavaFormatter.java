package dom.java.utils;

import dom.java.CompilationUnit;
import dom.java.CompilationUnitVisitor;
import dom.java.Interface;
import dom.java.TopLevelClass;
import dom.java.TopLevelEnumeration;
import dom.java.render.TopLevelClassRenderer;
import dom.java.render.TopLevelEnumerationRenderer;
import dom.java.render.TopLevelInterfaceRenderer;

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