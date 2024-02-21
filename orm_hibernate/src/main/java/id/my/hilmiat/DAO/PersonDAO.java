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
        
    }

    @Override
    public List getAll() {
        Session sesi = HibernateUtility.buatSession().openSession();
        return sesi.createQuery("from Person", Person.class).list();
    }

    @Override
    public Object addData(Object newData) {
        Session sesi = HibernateUtility.buatSession().openSession();
        Transaction transaksi = sesi.beginTransaction();
        sesi.save((Person) newData);
        transaksi.commit();
        return (Person) newData;
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
