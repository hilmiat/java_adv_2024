package FieldAnnotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Repository<E> {
    public void initialize(E e){
        //mendapatkan class real nya
        Class c = e.getClass();
        //mendapatkan semua field dari class tersebut
        Field[] fields = c.getDeclaredFields();
        //untuk tiap fields, cek apakah ada annotations ignored. Cetak semua fields kecuali yang ada annotation ignored
        for(Field f:fields){
            // Ignored ano = f.getAnnotation(Ignored.class);
            Annotation[] annotations = f.getAnnotations();
            for(Annotation a:annotations){
                // System.out.println(a);
                // if(a.getClass().getName().equals("PK"))
                // System.out.println(f);
                // System.out.println(a.getClass());
            }
        }
        //mendapatkan annotation pada class untuk membaca table_name
        Annotation classAnnotation = c.getAnnotation(Entity.class);
        Entity entity = (Entity) classAnnotation;
        System.out.println(entity.table_name());
    }
}
