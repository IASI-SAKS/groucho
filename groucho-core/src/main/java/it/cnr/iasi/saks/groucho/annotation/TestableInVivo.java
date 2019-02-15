package it.cnr.iasi.saks.groucho.annotation;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestableInVivo {
	String invivoTestClass();
	String invivoTest();
}
