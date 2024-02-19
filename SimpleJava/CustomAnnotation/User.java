package CustomAnnotation;

public class User {
    private Long id;
    @Field
    private String nama;
    @Field
    private String email;

    public User(Long id, String name){
        this.id = id;
        this.nama = name;
    }

    public Long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", nama=" + nama + "]";
    }


}
