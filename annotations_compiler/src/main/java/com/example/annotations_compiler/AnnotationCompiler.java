package com.example.annotations_compiler;

import com.example.annotations.BindPath;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * @author jiazhu
 */
@AutoService(Processor.class)
public class AnnotationCompiler extends AbstractProcessor {
    Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindPath.class);
        HashMap<String, String> map = new HashMap<>();
        for (Element element : elements) {
            if (element instanceof TypeElement) {
                TypeElement typeElement = (TypeElement) element;
                BindPath annotation = typeElement.getAnnotation(BindPath.class);
                String path = annotation.value();
                String activityName = typeElement.getQualifiedName().toString();
                map.put(path, activityName);
            }
        }

        if (map.size() > 0) {
            Writer writer = null;
            String utilName = "ActivityUtil" + System.currentTimeMillis();
            try {
                JavaFileObject javaFileObject = mFiler.createSourceFile("com.example.util." + utilName);
                writer = javaFileObject.openWriter();
                Iterator<String> iterator = map.keySet().iterator();
                writer.write("package com.example.util;\n" +
                        "\n" +
                        "import com.example.route.IRoute;\n" +
                        "import com.example.route.Route;\n" +
                        "\n" +
                        "public class " + utilName + " implements IRoute {\n" +
                        "    @Override\n" +
                        "    public void putActivity() {\n");
                while (iterator.hasNext()) {
                    String path = iterator.next();
                    String value = map.get(path);
                    writer.write("Route.getInstance().putActivity(\"" + path + "\"," + value + ".class);\n");
                }

                writer.write("    }\n" +
                        "}\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return false;
    }
}
