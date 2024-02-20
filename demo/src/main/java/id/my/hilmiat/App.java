package id.my.hilmiat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import id.my.hilmiat.db.Database;
import id.my.hilmiat.entity.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        User user1 = new User();
        user1.setUsername("hilmiat");
        user1.setEmail("hilmiat@gmail.com");
        user1.setPassword("Rahasia");
        // user1.setId(1);
        // user1.getPassword();
        testKoneksi();
    }

    public static void testKoneksi(){
        try {
            Connection koneksi = Database.getDatabase().getKoneksi();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    }

}
