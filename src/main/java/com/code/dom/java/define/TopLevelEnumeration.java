package com.code.dom.java.define;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午9:56
 */
public class TopLevelEnumeration extends InnerEnum implements CompilationUnit {

    private final Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();

    private final Set<String> staticImports = new TreeSet<>();

    private final List<String> fileCommentLines = new ArrayList<>();

    public TopLevelEnumeration(FullyQualifiedJavaType type) {
        super(type);
    }

    public TopLevelEnumeration(String type) {
        super(type);
    }

    @Override
    public Set<FullyQualifiedJavaType> getImportedTypes() {
        return importedTypes;
    }

    @Override
    public void addImportedType(FullyQualifiedJavaType importedType) {
        if (importedType.isExplicitlyImported()
                && !importedType.getPackageName().equals(
                getType().getPackageName())) {
            importedTypes.add(importedType);
        }
    }

    @Override
    public void addFileCommentLine(String commentLine) {
        fileCommentLines.add(commentLine);
    }

    @Override
    public List<String> getFileCommentLines() {
        return fileCommentLines;
    }

    @Override
    public void addImportedTypes(Set<FullyQualifiedJavaType> importedTypes) {
        this.importedTypes.addAll(importedTypes);
    }

    @Override
    public Set<String> getStaticImports() {
        return staticImports;
    }

    @Override
    public void addStaticImport(String staticImport) {
        staticImports.add(staticImport);
    }

    @Override
    public void addStaticImports(Set<String> staticImports) {
        this.staticImports.addAll(staticImports);
    }

    @Override
    public <R> R accept(CompilationUnitVisitor<R> visitor) {
        return visitor.visit(this);
    }
}

