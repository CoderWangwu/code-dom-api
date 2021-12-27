package dom.java.render;

import dom.java.CompilationUnit;
import dom.java.JavaDomUtils;
import dom.java.TypeParameter;
import dom.java.utils.CustomCollectors;

/**
 * @Author: wg
 * @Date: 2021/12/27 下午10:14
 */
public class TypeParameterRenderer {
    public String render(TypeParameter typeParameter, CompilationUnit compilationUnit) {
        return typeParameter.getName() + typeParameter.getExtendsTypes().stream()
                .map(t -> JavaDomUtils.calculateTypeName(compilationUnit, t))
                .collect(CustomCollectors.joining(" & ", " extends ", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
}
