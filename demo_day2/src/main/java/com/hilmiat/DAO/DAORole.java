package com.hilmiat.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hilmiat.db.Database;
import com.hilmiat.entity.Role;
import com.hilmiat.entity.User;

public class DAORole implements DAO {

    @Override
    public Optional get(Long pk) {
        Connection koneksi;
        try {
            koneksi = Database.getDatabase().getKoneksi();
            String query = "select * from role where id=?";
            PreparedStatement ps = koneksi.prepareStatement(query);
            //set parameter (?)
            ps.setLong(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String role_name = rs.getString("role_name");
                Role role = new Role(id,role_name);
                return Optional.of(role);
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
         ArrayList<Role> dataRoles = new ArrayList<>();
        Connection koneksi;
        try {
            koneksi = Database.getDatabase().getKoneksi();
            String query = "select * from role";
            PreparedStatement ps = koneksi.prepareStatement(query);
            //Membatasi ukuran/size dari resultset
            ps.setFetchSize(2);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String role_name = rs.getString("role_name");
                Role role = new Role(id, role_name);
                dataRoles.add(role);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataRoles;
    }

    @Override
    public Object addData(Object newData) {
        //siapkan query
        String sql = "insert into role (role_name) values (?)";
        Connection koneksi;
        try {
            koneksi = Database.getDatabase().getKoneksi();
            PreparedStatement ps = koneksi.prepareStatement(sql);
            //set parameter
            Role data = (Role) newData;
            ps.setString(1, data.getRole_name());//?1
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
    public Object update(Long pk, Object dataUpdate) {
        //cast updatedData menjadi role
        Role roleUpdate = (Role) dataUpdate;
        //siapkan query
        String sql = "UPDATE role set role_name=? where id=?";
        Connection koneksi;
        try{
            koneksi = Database.getDatabase().getKoneksi();
            PreparedStatement ps = koneksi.prepareStatement(sql);
            //isi ?
            ps.setString(1, roleUpdate.getRole_name());
            ps.setLong(2, pk);
            //execute
            ps.executeUpdate();
            return roleUpdate;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Long pk) {
       //siapkan query
       String sql = "delete from role where id=?";
       Connection koneksi;
        try{
            koneksi = Database.getDatabase().getKoneksi();
            PreparedStatement ps = koneksi.prepareStatement(sql);
            //set parameter id
            ps.setLong(1, pk);
            //execute
            int deletedRow = ps.executeUpdate();
            if(deletedRow>0) return true;
        }catch(Exception e){
            e.printStackTrace();
        }
       return false;
    }
}
