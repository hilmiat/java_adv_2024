import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = MyRepeatedAnnos.class)
@interface Word{
    String word();
    int value();
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyRepeatedAnnos{
    Word[] value();
}

public class RepeatAnnoDemo {

    @Word(word = "sdfdsf",value=2)
    String x = "Test";

    @Deprecated
    @Word(word = "Word 1",value = 1)
    @Word(word = "Word 2",value = 2)
    public static void newMethod(){

    }

    public static void main(String[] args) {
        RepeatAnnoDemo demo = new RepeatAnnoDemo();
        Class<? extends RepeatAnnoDemo> c = demo.getClass();
        try {
            Method m = c.getMethod("newMethod");
            Annotation[] a = m.getAnnotations();
            for(Annotation an:a){
                System.out.println(an);
               
            }
            // Annotation ano = m.getAnnotation(MyRepeatedAnnos.class);
            // System.out.println(ano);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}
