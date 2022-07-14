package studio.genesis.manager.order.support;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import studio.genesis.manager.order.services.interfaces.ICrudService;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Slf4j
public abstract class AbstractCrudService<R extends JpaRepository<T, K>, T, K> implements ICrudService<T, K> {

    @Autowired
    private R repository;

    final private String getNameTClass() {
        var clazz = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz.getSimpleName();
    }

    @Override
    public Page<T> pageable(Pageable pageable) {
        log.info("{}: service: pageable<{}>: {}", this.getNameTClass(),this.getNameTClass(), pageable);
        return repository.findAll(pageable);
    }

    @Override
    public List<T> all() {
        log.info("{}: service: all<{}>", this.getNameTClass(), this.getNameTClass());
        return repository.findAll();
    }

    @Override
    public T find(K k) {
        log.info("{}: service: find<{}>: {}", this.getNameTClass(), this.getNameTClass(), k);
        return repository.findById(k).orElse(null);
    }

    @Override
    public T save(T entity) {
        log.info("{}: service: save<{}>: {}", this.getNameTClass(), this.getNameTClass(), entity);
        return repository.save(entity);
    }

    @Override
    public void delete(K k) {
        log.info("{}: service: delete<{}>: {}", this.getNameTClass(), this.getNameTClass(), k);
        repository.deleteById(k);
    }
}

