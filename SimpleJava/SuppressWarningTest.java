class DeprecatedTest  {
    @Deprecated
    void display(){
        System.out.println("Inside display method");
    }
    
}
public class SuppressWarningTest {
    @SuppressWarnings({"checked","deprecated"})
    public static void main(String[] args) {
        DeprecatedTest test = new DeprecatedTest();
        test.display();
    }
}
