
package com.kiran.portfolio;

import org.springframework.stereotype.Service;
import java.lang.annotation.Annotation;

@Service
class AnnotatedExample {
}

class PlainExample {
}

public class AnnotationDemo {
    public static void main(String[] args) {
        inspectClass(AnnotatedExample.class);
        inspectClass(PlainExample.class);
    }

    static void inspectClass(Class<?> clazz) {
        System.out.println("\nClass: " + clazz.getSimpleName());

        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            System.out.println("Annotation: " + annotation);
        }
    }
}
