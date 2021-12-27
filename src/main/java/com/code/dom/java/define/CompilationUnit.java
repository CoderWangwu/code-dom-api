package com.code.dom.java.define;

import java.util.List;
import java.util.Set;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午9:39
 */
public interface CompilationUnit {
    Set<FullyQualifiedJavaType> getImportedTypes();

    Set<String> getStaticImports();

    FullyQualifiedJavaType getType();

    void addImportedType(FullyQualifiedJavaType importedType);

    void addImportedTypes(Set<FullyQualifiedJavaType> importedTypes);

    void addStaticImport(String staticImport);

    void addStaticImports(Set<String> staticImports);

    /**
     * @param commentLine the comment line
     */
    void addFileCommentLine(String commentLine);

    List<String> getFileCommentLines();

    <R> R accept(CompilationUnitVisitor<R> visitor);
}
