import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@interface Identitas{
    String Creator() default "Hilmi";
    String masaBerlaku();
}


public class TestAnnotation {

  @Identitas(Creator="Saya", masaBerlaku="19-02-2024")  
  void fun1(){
    System.out.println("Test method 1");
  }

  @Identitas(Creator="Orang lain", masaBerlaku="29-02-2024")  
  void fun2(){
    System.out.println("Test method 2");
  }

  public static void main(String[] args) {
    
  }

}
