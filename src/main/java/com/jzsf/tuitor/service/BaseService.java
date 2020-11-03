package com.jzsf.tuitor.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author by plain yuan
 * @since 2020/04/12
 * 通用service
 */
@Service
public interface BaseService<T, ID> {

    /**
     * 保存单个实体
     *
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> S save(S entity);

    /**
     * 保存所有实体
     *
     * @param entitys
     * @param <S>
     * @return
     */
    <S extends T> Iterable<S> saveAll(Iterable<S> entitys);

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     */
    Optional<T> findById(ID id);

    /**
     * 查询当前id的实体是否存在
     *
     * @param id
     * @return
     */
    boolean existsById(ID id);

    /**
     * 获取所有实体
     *
     * @return
     */
    Iterable<T> findAll();

    /**
     * 根据ids，获取所有实体
     *
     * @param ids
     * @return
     */
    Iterable<T> findAllById(Iterable<ID> ids);

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);

    /**
     * @return 获得总数据条数
     */
    Long count();

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(ID id);

    /**
     * 根据entity删除
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * 根据entities删除所有
     *
     * @param entities
     */
    void deleteAll(Iterable<? extends T> entities);


}
