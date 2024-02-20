package id.my.hilmiat.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<E> {
    // get one row by PK
    Optional<E> get(Long pk);

    // get all
    List<E> getAll();

    // add
    E addData(E newData);

    // update
    E update(Long pk,E updatedData);

    // delete
    boolean delete(Long pk);
}
