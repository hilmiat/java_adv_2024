package id.my.hilmiat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Object addData(Object newData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addData'");
    }

    @Override
    public Object update(Long pk, Object updatedData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Long pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
