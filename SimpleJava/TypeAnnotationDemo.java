import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE_USE)

@interface TypeAnnoDemo{



};


public class TypeAnnotationDemo {
    public static void main(String[] args) {
        @TypeAnnoDemo
        String teks = "Saya menggunakan type annotation";
    }

    public @TypeAnnoDemo int tambah(int a, int b){
        return a+b;
    }
}
