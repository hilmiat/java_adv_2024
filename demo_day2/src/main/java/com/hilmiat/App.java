package com.hilmiat;

import java.util.List;
import java.util.Optional;

import com.hilmiat.DAO.DAORole;
import com.hilmiat.entity.Role;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Test Add data role" );
        DAORole dao = new DAORole();
        dao.addData(new Role(1,"Admin"));
        dao.addData(new Role(2,"User"));
        dao.addData(new Role(3,"Operator"));
        dao.addData(new Role(4,"Guest"));
        System.out.println( "Test get data id-2" );
        Optional opt = dao.get(2L);
        if(opt.isPresent()){
            Role role2 = (Role) opt.get();
            System.out.println(role2.getRole_name());
        }
        System.out.println( "Test update data id-2" );
        dao.update(2L, new Role(2,"Regular User"));

        System.out.println( "Test delete data id-4" );
        if(dao.delete(4L)){
            System.out.println("Sukses hapus data");
        }else{
            System.out.println("Gagal hapus data");
        }
        System.out.println( "Test get all data" );
        List<Role> dataRoles = dao.getAll();
        for(Role role: dataRoles){
            System.out.println(role.getRole_name());
        }


    }
}
