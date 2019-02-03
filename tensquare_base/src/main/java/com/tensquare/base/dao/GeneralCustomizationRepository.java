package com.tensquare.base.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * 提供自定义的JPA的分页以及条件搜索接口
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean  //为所有Repository添加自定义方法
//JpaRepository 和  CurdRepository是有区别的，但是继承哪个都可以；只不过返回的类型不一样
public interface GeneralCustomizationRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    List<T> findByAuto(T example);
    Page<T> findByPage(T example, Integer page, Integer size);
}
