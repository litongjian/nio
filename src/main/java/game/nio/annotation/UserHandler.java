package game.nio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 所属项目：nio
 * 创建时间：2017/2/25.
 * 路径：game.nio.annotation
 * 概要：用户Handler类注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserHandler {
    String name();
    int index();
}
