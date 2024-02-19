package FieldAnnotations;

public class Demo {
    public static void main(String[] args) {
        User user1 = new User();
        Repository<User> repo = new Repository<>();
        repo.initialize(user1);

        Product p1 = new Product();
        Repository<Product> repo2 = new Repository<>();
        repo2.initialize(p1);
    }
}
