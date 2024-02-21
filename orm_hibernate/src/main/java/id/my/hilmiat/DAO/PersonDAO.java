package id.my.hilmiat.DAO;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import id.my.hilmiat.db.HibernateUtility;
import id.my.hilmiat.entity.Person;

public class PersonDAO implements DAO{

    @Override
    public Optional get(Long pk) {
        try{
            Session sesi = HibernateUtility.buatSessionFactory().openSession();
            Person p = sesi.get(Person.class,pk);
            return Optional.of(p);
        }catch(Exception e){
            return Optional.empty();
        }
    }

    @Override
    public List getAll() {
        Session sesi = HibernateUtility.buatSessionFactory().openSession();
        return sesi.createQuery("from Person", Person.class).list();
    }

    @Override
    public Object addData(Object newData) {
        Transaction transaksi = null;
        try{
            Session sesi = HibernateUtility.buatSessionFactory().openSession();
            transaksi = sesi.beginTransaction();
            sesi.persist((Person) newData);
            transaksi.commit();
        }catch(Exception e){
            if(transaksi!=null){
                transaksi.rollback();
            }
            System.err.println("Gagal insert");
        }
        
        return (Person) newData;
    }

    @Override
    public Object update(Object dataUpdate) {
        Transaction trans = null;
        try{
            Session sesi = HibernateUtility.buatSessionFactory().openSession();
            trans = sesi.beginTransaction();
            Object updated = sesi.merge(dataUpdate);
            trans.commit();
            return updated;
        }catch(Exception e){
            // trans.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Long pk) {
        Transaction transaksi = null;
        try{
            Person personToDelete = new Person();
            personToDelete.setId(pk);
            Session sesi = HibernateUtility.buatSessionFactory().openSession();
            transaksi = sesi.beginTransaction();
            sesi.remove(personToDelete);
            transaksi.commit();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            transaksi.rollback();
            return false;
        }

    }
    
}
