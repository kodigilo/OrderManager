package studio.genesis.manager.order.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import studio.genesis.manager.order.services.interfaces.ICrudService;

import java.util.List;

public abstract class AbstractCrudService<R extends JpaRepository<T,K>,T,K> implements ICrudService<T, K> {

    @Autowired
    private R repository;

    @Override
    public Page<T> pageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<T> all() {
        return repository.findAll();
    }

    @Override
    public T find(K k) {
        return repository.findById(k).orElse(null);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(K k) {
        repository.deleteById(k);
    }
}

