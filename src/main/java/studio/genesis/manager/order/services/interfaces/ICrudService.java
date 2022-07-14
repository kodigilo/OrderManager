package studio.genesis.manager.order.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService <T, ID> {
    Page<T> pageable(Pageable pageable);
    List<T> all();
    T find(ID id);
    T save(T entity);
    void delete(ID id);
}
