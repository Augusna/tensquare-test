package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
/**
 * JpaSpecificationExecutor<T>继承这个是可以加分页，加条件搜索的
 * 在oracle-xe项目中有自定义，可参考
 */
public interface LabelDao extends GeneralCustomizationRepository<Label,String>{
}
