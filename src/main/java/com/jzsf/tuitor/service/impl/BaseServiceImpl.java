package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
public abstract class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    /**
     * 子类实现，返回各自的DAO操作对象
     *
     * @return
     */
    @SuppressWarnings("none")
    @Autowired
    protected abstract JpaRepository getRepository();

    @Override
    public <S extends T> S save(S entity) {
        return (S) getRepository().save(entity);
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        return getRepository().saveAll(entities);
    }

    @Override
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    @Override
    public Iterable<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> var1) {
        return getRepository().findAll();
    }

    @Override
    public Long count() {
        return getRepository().count();
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends T> allID) {
        getRepository().deleteAll(allID);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }
}

