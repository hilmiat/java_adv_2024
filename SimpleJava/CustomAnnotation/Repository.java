package CustomAnnotation;

import java.lang.reflect.Field;

public class Repository<T> {
    public void save(T t){
        Class kelas = t.getClass();
        Field[] fields = kelas.getDeclaredFields();
        for(Field f: fields){
            CustomAnnotation.Field[] annotations = 
            f.getAnnotationsByType(CustomAnnotation.Field.class);
            if(annotations.length > 0 ){
                System.out.println(f);
            }
        }
    }
}
