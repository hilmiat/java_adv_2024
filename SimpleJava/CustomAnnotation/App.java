package CustomAnnotation;

public class App {
    public static void main(String[] args) {
        User user1 = new User(1L, "hilmi");
        Repository repo = new Repository<>();
        repo.save(user1);
    }
}
