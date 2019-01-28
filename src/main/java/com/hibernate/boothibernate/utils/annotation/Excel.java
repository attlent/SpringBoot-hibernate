package com.hibernate.boothibernate.utils.annotation;

import java.lang.annotation.*;

//指明修饰的注解，可以被例如javadoc此类的工具文档化
@Documented
// 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Retention(RetentionPolicy.RUNTIME)
// 可作用在接口、类、枚举、注解
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Excel {

    //字段顺序
    String[] value() default "";

    //中文表头顺序
    String[] title() default "";

    //字段名称
    String column() default "";

    int columnIndex() default 0;

    String databaseFormat() default "yyyyMMddHHmmss";

    String exportFormat() default "";

    String format() default "";

    double height() default 10.0D;

    int imageType() default 1;

    String importFormat() default "";

    String suffix() default "";

    boolean isWrap() default true;

    int[] mergeRely() default {};

    boolean mergeVertical() default false;

    String name() default "";

    boolean needMerge() default false;

    String orderNum() default "0";

    String[] replace() default {};

    String savePath() default "upload";

    int type() default 1;

    double width() default 10.0D;

    boolean isStatistics() default false;

    String dictTable() default "";

    String dicCode() default "";

    String dicText() default "";

}