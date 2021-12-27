package com.code.dom.java.render;

import com.code.dom.java.define.Method;
import com.code.dom.java.define.TypeParameter;
import com.code.dom.java.utils.CustomCollectors;
import com.code.dom.java.utils.StringUtility;
import com.code.dom.java.define.CompilationUnit;
import com.code.dom.java.define.Field;
import com.code.dom.java.define.FullyQualifiedJavaType;
import com.code.dom.java.define.InitializationBlock;
import com.code.dom.java.define.InnerClass;
import com.code.dom.java.define.InnerEnum;
import com.code.dom.java.define.InnerInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:11
 */
public class RenderingUtilities {
    private RenderingUtilities() {
    }

    public static final String JAVA_INDENT = "    "; //$NON-NLS-1$
    private static final TypeParameterRenderer typeParameterRenderer = new TypeParameterRenderer();
    private static final FieldRenderer fieldRenderer = new FieldRenderer();
    private static final InitializationBlockRenderer initializationBlockRenderer = new InitializationBlockRenderer();
    private static final MethodRenderer methodRenderer = new MethodRenderer();
    private static final InnerClassRenderer innerClassRenderer = new InnerClassRenderer();
    private static final InnerInterfaceRenderer innerInterfaceRenderer = new InnerInterfaceRenderer();
    private static final InnerEnumRenderer innerEnumRenderer = new InnerEnumRenderer();

    // should return an empty string if no type parameters
    public static String renderTypeParameters(List<TypeParameter> typeParameters, CompilationUnit compilationUnit) {
        return typeParameters.stream()
                .map(tp -> typeParameterRenderer.render(tp, compilationUnit))
                .collect(CustomCollectors.joining(", ", "<", "> ")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    public static List<String> renderFields(List<Field> fields, CompilationUnit compilationUnit) {
        return fields.stream()
                .flatMap(f -> renderField(f, compilationUnit))
                .collect(Collectors.toList());
    }

    private static Stream<String> renderField(Field field, CompilationUnit compilationUnit) {
        return addEmptyLine(fieldRenderer.render(field, compilationUnit).stream()
                .map(RenderingUtilities::javaIndent));
    }

    public static List<String> renderInitializationBlocks(List<InitializationBlock> initializationBlocks) {
        return initializationBlocks.stream()
                .flatMap(RenderingUtilities::renderInitializationBlock)
                .collect(Collectors.toList());
    }

    private static Stream<String> renderInitializationBlock(InitializationBlock initializationBlock) {
        return addEmptyLine(initializationBlockRenderer.render(initializationBlock).stream()
                .map(RenderingUtilities::javaIndent));
    }

    public static List<String> renderClassOrEnumMethods(List<Method> methods, CompilationUnit compilationUnit) {
        return methods.stream()
                .flatMap(m -> renderMethod(m, false, compilationUnit))
                .collect(Collectors.toList());
    }

    public static List<String> renderInterfaceMethods(List<Method> methods, CompilationUnit compilationUnit) {
        return methods.stream()
                .flatMap(m -> renderMethod(m, true, compilationUnit))
                .collect(Collectors.toList());
    }

    private static Stream<String> renderMethod(Method method, boolean inInterface, CompilationUnit compilationUnit) {
        return addEmptyLine(methodRenderer.render(method, inInterface, compilationUnit).stream()
                .map(RenderingUtilities::javaIndent));
    }

    private static Stream<String> addEmptyLine(Stream<String> in) {
        return Stream.of(in, Stream.of("")) //$NON-NLS-1$
                .flatMap(Function.identity());
    }

    public static List<String> renderInnerClasses(List<InnerClass> innerClasses, CompilationUnit compilationUnit) {
        return innerClasses.stream()
                .flatMap(ic -> renderInnerClass(ic, compilationUnit))
                .collect(Collectors.toList());
    }

    public static List<String> renderInnerClassNoIndent(InnerClass innerClass, CompilationUnit compilationUnit) {
        return innerClassRenderer.render(innerClass, compilationUnit);
    }

    private static Stream<String> renderInnerClass(InnerClass innerClass, CompilationUnit compilationUnit) {
        return addEmptyLine(innerClassRenderer.render(innerClass, compilationUnit).stream()
                .map(RenderingUtilities::javaIndent));
    }

    public static List<String> renderInnerInterfaces(List<InnerInterface> innerInterfaces,
                                                     CompilationUnit compilationUnit) {
        return innerInterfaces.stream()
                .flatMap(ii -> renderInnerInterface(ii, compilationUnit))
                .collect(Collectors.toList());
    }

    public static List<String> renderInnerInterfaceNoIndent(InnerInterface innerInterface,
                                                            CompilationUnit compilationUnit) {
        return innerInterfaceRenderer.render(innerInterface, compilationUnit);
    }

    private static Stream<String> renderInnerInterface(InnerInterface innerInterface, CompilationUnit compilationUnit) {
        return addEmptyLine(innerInterfaceRenderer.render(innerInterface, compilationUnit).stream()
                .map(RenderingUtilities::javaIndent));
    }

    public static List<String> renderInnerEnums(List<InnerEnum> innerEnums, CompilationUnit compilationUnit) {
        return innerEnums.stream()
                .flatMap(ie -> renderInnerEnum(ie, compilationUnit))
                .collect(Collectors.toList());
    }

    public static List<String> renderInnerEnumNoIndent(InnerEnum innerEnum, CompilationUnit compilationUnit) {
        return innerEnumRenderer.render(innerEnum, compilationUnit);
    }

    private static Stream<String> renderInnerEnum(InnerEnum innerEnum, CompilationUnit compilationUnit) {
        return addEmptyLine(innerEnumRenderer.render(innerEnum, compilationUnit).stream()
                .map(RenderingUtilities::javaIndent));
    }

    public static List<String> renderPackage(CompilationUnit compilationUnit) {
        List<String> answer = new ArrayList<>();

        String pack = compilationUnit.getType().getPackageName();
        if (StringUtility.stringHasValue(pack)) {
            answer.add("package " + pack + ";"); //$NON-NLS-1$ //$NON-NLS-2$
            answer.add(""); //$NON-NLS-1$
        }
        return answer;
    }

    public static List<String> renderStaticImports(CompilationUnit compilationUnit) {
        if (compilationUnit.getStaticImports().isEmpty()) {
            return Collections.emptyList();
        }

        return addEmptyLine(compilationUnit.getStaticImports().stream()
                .map(s -> "import static " + s + ";")) //$NON-NLS-1$ //$NON-NLS-2$
                .collect(Collectors.toList());
    }

    public static List<String> renderImports(CompilationUnit compilationUnit) {
        Set<String> imports = renderImports(compilationUnit.getImportedTypes());

        if (imports.isEmpty()) {
            return Collections.emptyList();
        }

        return addEmptyLine(imports.stream()).collect(Collectors.toList());
    }

    private static Set<String> renderImports(Set<FullyQualifiedJavaType> imports) {
        return imports.stream()
                .map(FullyQualifiedJavaType::getImportList)
                .flatMap(List::stream)
                .map(RenderingUtilities::toFullImport)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private static String toFullImport(String s) {
        return "import " + s + ";"; //$NON-NLS-1$ //$NON-NLS-2$
    }


    private static String javaIndent(String in) {
        if (in.isEmpty()) {
            return in; // don't indent empty lines
        }

        return JAVA_INDENT + in;
    }

    public static List<String> removeLastEmptyLine(List<String> lines) {
        if (lines.get(lines.size() - 1).isEmpty()) {
            return lines.subList(0, lines.size() - 1);
        } else {
            return lines;
        }
    }
}
