package id.my.hilmiat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import id.my.hilmiat.dao.DAOUser;
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
        user1.setUsername("hilmiat2");
        user1.setEmail("hilmiat2@gmail.com");
        user1.setPassword("Rahasia");
        // user1.setId(1);
        // user1.getPassword();

        //cari user di database dengan id 2
        DAOUser dao = new DAOUser();
        Optional optional = dao.get(2L);
        
        if(optional.isPresent()){
            User userdb = (User) optional.get();
            System.out.println("Ketemu "+userdb.getUsername());
        }else{
            System.out.println("Not found");
        }

        //test insert data
        // dao.addData(user1);
        //test delete data
        // boolean status = dao.delete(1L);
        // if(status){
        //     System.out.println("Sukses delete data");
        // }else{
        //     System.out.println("gagal delete data");
        // }
        //test update
        // dao.update(2L, new User("baru banget", "Email@mail.com", "password"));

        //get all user
         List data = dao.getAll();
         System.out.println(data);
    }

}
