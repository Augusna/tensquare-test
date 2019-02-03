package com.tensquare.base.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_label")
@Data
public class Label implements Serializable {
    @Id
    private String id;
    private String labelname;
    private String state;
    /**使用数量.*/
    private String count;
    /**关注数.*/
    private String fans;
    /**是否推荐.*/
    private String recommend;
}
