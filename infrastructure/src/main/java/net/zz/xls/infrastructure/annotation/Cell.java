package net.zz.xls.infrastructure.annotation;

/**
 * Created by ZaoSheng on 2015/7/21.
 */
public @interface Cell {
    String value();
    String field() default "";
}
