package com.code.dom.java.define;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午9:51
 */
public enum JavaVisibility {
    PUBLIC("public "), //$NON-NLS-1$
    PRIVATE("private "), //$NON-NLS-1$
    PROTECTED("protected "), //$NON-NLS-1$
    DEFAULT(""); //$NON-NLS-1$

    private final String value;

    JavaVisibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
