package demo.DAO;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import demo.db.HibernateUtility;
import demo.entity.Departement;
import demo.entity.Person;


public class DepartementDAO implements DAO {

    @Override
    public Optional get(Long pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List getAll() {
       Session sesi = HibernateUtility.buatSessionFactory().openSession();
        return sesi.createQuery("from Departement", Departement.class).list();
    }

    @Override
    public Object addData(Object newData) {
        Transaction transaksi = null;
        try{
            Session sesi = HibernateUtility.buatSessionFactory().openSession();
            transaksi = sesi.beginTransaction();
            sesi.save((Departement) newData);
            transaksi.commit();
        }catch(Exception e){
            if(transaksi!=null){
                transaksi.rollback();
            }
            System.err.println("Gagal insert");
        }
        
        return (Departement) newData;
    }

    @Override
    public Object update(Long pk, Object dataUpdate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Long pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}