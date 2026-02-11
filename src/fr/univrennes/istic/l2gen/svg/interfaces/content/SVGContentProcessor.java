package fr.univrennes.istic.l2gen.svg.interfaces.content;

import java.util.Set;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes("fr.univrennes.istic.l2gen.svg.interfaces.content.SVGContent")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public final class SVGContentProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(SVGContent.class)) {

            if (element.getKind() != ElementKind.FIELD) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@SVGContent can only be applied to fields",
                        element);
                continue;
            }

            TypeMirror fieldType = element.asType();
            Elements elementUtils = processingEnv.getElementUtils();
            TypeMirror stringType = elementUtils.getTypeElement("java.lang.String").asType();

            if (!processingEnv.getTypeUtils().isSameType(fieldType, stringType)) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@SVGContent can only be applied to fields of type String",
                        element);
            }
        }

        return true;
    }

}
