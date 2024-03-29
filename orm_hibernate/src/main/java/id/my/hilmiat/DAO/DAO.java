import java.util.List;
import java.util.Optional;

public interface DAO<E> {
    Optional<E> get(Long pk);
    List<E> getAll();
    E addData(E newData);
    E update(E dataUpdate);
    boolean delete(Long pk);
}