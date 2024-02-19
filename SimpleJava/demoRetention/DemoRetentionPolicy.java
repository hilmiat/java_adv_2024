// package demoRetention;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;


@Retention(RetentionPolicy.RUNTIME)
@interface RetentionRuntime{
    String nama() default "runtime";
}

@Retention(RetentionPolicy.CLASS)
@interface RetentionClass{
    String nama() default "class";
}

@Retention(RetentionPolicy.SOURCE)
@interface RetentionSource{
    String nama() default "source";
}

public class DemoRetentionPolicy {
    @RetentionClass
    @RetentionRuntime
    @RetentionSource
    String test;

    public static void main(String[] args) {
        DemoRetentionPolicy demo = new DemoRetentionPolicy();
        Field[] fields = demo.getClass().getDeclaredFields();
        for(Field f:fields){
           RetentionRuntime runtime = f.getAnnotation(RetentionRuntime.class);
           System.out.println(runtime.nama());

        //    RetentionClass retClass = f.getAnnotation(RetentionClass.class);
        //    System.out.println(retClass.nama());

        //    RetentionSource retSource = f.getAnnotation(RetentionSource.class);
        //    System.out.println(retSource.nama());
        }
    }
}
