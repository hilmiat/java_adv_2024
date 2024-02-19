// package SimpleJava;

//Class DeprecatedDemo
//Isinya adalah tentang demonstrasi penggunaan annotation deprecated
//dibuat oleh saya


// @DefinisiClass(
//     name="DeprecatedDemo",
//     isi="Isinya adalah tentang demonstrasi penggunaan annotation deprecated",\
//     author="Saya"
// )
public class DeprecatedDemo {
    @Deprecated
    public void display(){
        System.out.println("Di dalam method display");
    }

    public void display2(){
        System.out.println("di dalam method display2");
    }

    public static void main(String[] args) {
        DeprecatedDemo d1 = new DeprecatedDemo();
        d1.display();
        X x1 = new X();
        x1.display2();
    }
}

class X extends DeprecatedDemo{
    @Override
    public void display2(){
        System.out.println("ini di dalam child display2");
    }
}