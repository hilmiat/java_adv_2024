package id.my.hilmiat.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    //jadikan konstruktor private
    private Database(){}
    //obj Database
    public static Database db;

    //method untuk mendapatkan obj database
    public static Database getDatabase(){
        //jika belu pernah dibuat obj database, maka buat
        if(db==null){
            db = new Database();
        }
        return db;
    }

    private Connection conn;

    public Connection getKoneksi() throws ClassNotFoundException, SQLException{
            //load class driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Sukses load driver");
            //buat koneksi
            String url = "jdbc:mysql://localhost:3306/service_demo";
            String user = "service_user";
            String pass = "service_password";
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Sukses koneksi");
            return conn;
    }    
}
