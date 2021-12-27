package com.code.dom.java;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午9:39
 */
public interface CompilationUnitVisitor<R> {
    R visit(TopLevelClass topLevelClass);

    R visit(TopLevelEnumeration topLevelEnumeration);

    R visit(Interface topLevelInterface);
}
