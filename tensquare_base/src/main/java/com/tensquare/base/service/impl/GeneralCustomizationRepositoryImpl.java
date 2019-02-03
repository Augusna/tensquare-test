package com.tensquare.base.service.impl;

import com.tensquare.base.dao.GeneralCustomizationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

import static com.tensquare.base.utils.GeneralCustomizationSpecification.byAuto;

/**
 * 自定义JPA分页以及搜索条件的实现
 * @param <T>
 * @param <ID>
 */
public class GeneralCustomizationRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements GeneralCustomizationRepository<T, ID> {
    private final EntityManager entityManager;

    public GeneralCustomizationRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List<T> findByAuto(T example) {
        return findAll(byAuto(entityManager, example));
    }

    @Override
    public Page<T> findByPage(T example, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return findAll(byAuto(entityManager, example), pageable);
    }
}
