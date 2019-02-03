package com.tensquare.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import static com.google.common.collect.Iterables.toArray;

/**
 * 自定义实现模糊查询
 * 即对于任意的实体对象进行查询，对象里有几个值我们就查几个，当值为字符型就自动like查询
 * 没有值就查询全部
 */
public class GeneralCustomizationSpecification {
    public static <T> Specification byAuto(final EntityManager entityManager, final T example) {  //1  定义一个返回值为Specification的方法byAuto
        final Class<T> type = (Class<T>) example.getClass();   //2   获得当前实体类对象类的类型
        return new Specification() {
            /**
             *
             * @param root  根对象，也就是要把条件封装到哪个对象中。 where  类名=label.getid
             * @param query  封装的都是查询的关键字，比如group by，order by
             * @param criteriaBuilder  用来封装条件对象的
             * @return
             */
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();  //3   新建Predicate列表存储构造的查询条件
                EntityType<T> entity = entityManager.getMetamodel().entity(type);  //4  从EntityType获得实体类的属性
                for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {   //5 对实体类的所有属性做循环
                    Object attrValue = getValue(example, attr);  //6  获得实体类对象某一个属性的值，例如name=tom。则attrValue的值就是Tom
                    if (attrValue != null && !attrValue.equals("")) {
                        if (attr.getJavaType() == String.class) {   //7  当前属性值为字符类型的时候
                            if (!StringUtils.isEmpty(attrValue)) {  //8   若当前字符不为空的情况下
                                predicates.add(
                                        criteriaBuilder.like(criteriaBuilder.upper(
                                                root.get(attribute(entity, attr.getName(), String.class))), "%" +
                                                ((String) attrValue).toUpperCase() + "%"));//构造当前属性like属性值查询条件"%" +
                            }
                        } else {
                            predicates.add(criteriaBuilder.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())),
                                    attrValue));  //10
                        }
                    }
                }
                return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(toArray(predicates, Predicate.class));
            }

            /**
             * 12
             */
            private <T> Object getValue(T example, Attribute<T, ?> attr) {
                return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
            }

            /**
             * 13
             * SingularAttribute  包含的是实体类的某个单独属性
             */
            private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName, Class<E> fieldClass) {
                return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
            }
        };
    }
    /**
     * 14
     */
    static private String pattern(String str){
        return "%"+str.toUpperCase()+"%";
    }
}
