package com.code.dom.java.define;

import com.code.dom.java.utils.JavaBeansUtil;

import java.text.MessageFormat;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午9:53
 */
public class TopCodeLevelEnumeration extends TopLevelEnumeration {

    private final Method constructorMethod;

    public TopCodeLevelEnumeration(FullyQualifiedJavaType type) {
        super(type);
    }

    public TopCodeLevelEnumeration(String type) {
        super(type);
    }

    {

        this.constructorMethod = new Method(this.getType().getShortName());
        constructorMethod.setVisibility(JavaVisibility.PRIVATE);
        constructorMethod.setConstructor(true);
        this.addMethod(constructorMethod);

        // 直接code
        Field codeField = new Field("code", new FullyQualifiedJavaType("java.lang.Integer"));
        addStaticMappingCodeFieldAndMethod(codeField);
        addFieldAndConstructorMethodParameter(codeField);


    }

    public void addFieldAndConstructorMethodParameter(Field field) {
        // step 1.增加field
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setFinal(true);
        this.addField(field);

        // step 2.增加构造方法参数
        Parameter parameter = new Parameter(field.getType(), field.getName());
        constructorMethod.addParameter(parameter);
        constructorMethod.addBodyLine("this." + field.getName() + " = " + parameter.getName() + ";");

        // step 3.增加getter 方法
        this.addMethod(JavaBeansUtil.getBasicJavaBeansGetter(field));
    }


    public void addStaticMappingCodeFieldAndMethod(Field field) {
        this.addImportedType(new FullyQualifiedJavaType("java.util.stream.Stream"));
        this.addImportedType(new FullyQualifiedJavaType("java.util.stream.Collectors"));
        FullyQualifiedJavaType mapJavaType = new FullyQualifiedJavaType("java.util.Map");
        this.addImportedType(mapJavaType);
        mapJavaType.addTypeArgument(field.getType());
        mapJavaType.addTypeArgument(new FullyQualifiedJavaType(this.getType().getShortName()));
        Field mapEnumField = new Field(field.getName().toUpperCase() + "_CODE_MAP", mapJavaType);
        mapEnumField.setStatic(true);
        mapEnumField.setVisibility(JavaVisibility.PRIVATE);
        mapEnumField.setFinal(true);
        mapEnumField.setInitializationString(MessageFormat.format("Stream.of({0}.values())\n" +
                "            .collect(Collectors.toMap(o -> o.{1}, o -> o))", this.getType().getShortName(), field.getName()));
        this.addField(mapEnumField);

        Method mapOfMethod = new Method(field.getName() + "Of");
        mapOfMethod.setStatic(true);
        Parameter parameter = new Parameter(field.getType(), field.getName());
        mapOfMethod.addParameter(parameter);
        mapOfMethod.setVisibility(JavaVisibility.PUBLIC);
        mapOfMethod.setReturnType(this.getType());
        mapOfMethod.addBodyLine("return " + mapEnumField.getName() + ".get(" + parameter.getName() + ");");
        this.addMethod(mapOfMethod);
    }

}

