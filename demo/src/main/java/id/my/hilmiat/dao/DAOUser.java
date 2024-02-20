package id.my.hilmiat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import id.my.hilmiat.db.Database;
import id.my.hilmiat.entity.User;

public class DAOUser implements DAO{

    @Override
    public Optional get(Long pk) {
        Connection koneksi;
        try {
            koneksi = Database.getDatabase().getKoneksi();
            String query = "select * from user where id=?";
            PreparedStatement ps = koneksi.prepareStatement(query);
            //set parameter (?)
            ps.setLong(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                User user = new User(username, email, password);
                return Optional.of(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();  
    }

    @Override
    public List getAll() {
        ArrayList<User> dataUser = new ArrayList<>();
        Connection koneksi;
        try {
            koneksi = Database.getDatabase().getKoneksi();
            String query = "select * from user";
            PreparedStatement ps = koneksi.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                User user = new User(username, email, password);
                dataUser.add(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataUser;
    }

    @Override
    public Object addData(Object newData) {
        //siapkan query
        String sql = "insert into user (username,email,password) values (?,?,?)";
        Connection koneksi;
        try {
            koneksi = Database.getDatabase().getKoneksi();
            PreparedStatement ps = koneksi.prepareStatement(sql);
            //set parameter
            User data = (User) newData;
            ps.setString(1, data.getUsername());//?1
            ps.setString(2, data.getEmail());//?2
            ps.setString(3, data.getPassword());//?3
            //eksekusi
            ps.executeUpdate();
            return data;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object update(Long pk, Object updatedData) {
        //cast updatedData menjadi user
        User userUpdate = (User) updatedData;
        //siapkan query
        String sql = "UPDATE user set username=?, email=?, password=? where id=?";
        Connection koneksi;
        try{
            koneksi = Database.getDatabase().getKoneksi();
            PreparedStatement ps = koneksi.prepareStatement(sql);
            //isi ?
            ps.setString(1, userUpdate.getUsername());
            ps.setString(2, userUpdate.getEmail());
            ps.setString(3, userUpdate.getPassword());
            ps.setLong(4, pk);
            //execute
            ps.executeUpdate();
            return userUpdate;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Long pk) {
       //siapkan query
       String sql = "delete from user where id=?";
       Connection koneksi;
       try{
            koneksi = Database.getDatabase().getKoneksi();
            PreparedStatement ps = koneksi.prepareStatement(sql);
            //set parameter id
            ps.setLong(1, pk);
            //execute
            ps.executeUpdate();
            return true;
       }catch(Exception e){
            e.printStackTrace();
       }
       return false;
    }
    
}
