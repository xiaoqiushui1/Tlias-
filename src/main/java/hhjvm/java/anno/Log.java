package hhjvm.java.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//方法类注解
@Retention(RetentionPolicy.RUNTIME)//运行时生效的注解
public @interface Log {




}
