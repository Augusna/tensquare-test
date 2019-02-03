package com.tensquare.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
@Data
@Document(indexName = "tensquare_article_20190123", type = "article")
public class Article implements Serializable {
    @Id
    /**ID.*/
    private String id;

    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    /**标题.*/
    private String title;

    /**
     * 是否索引：就是看该域(字段)是否能被搜索
     * 是否分词：就是搜索的时候是整体匹配还是分词匹配   ------整体匹配是整句话去搜索
     * 是否存储： 就是是否在页面上显示        --------->
     *       比如说多个描述字段，内容里面的东西很多，我们在百度搜索显示的是描述；
     *      所以内容被所索引，被分词，不存储(不显示内容)；可以根据内容搜索到
     *
     *      存储也可以理解为就是在搜索时要显示
     */
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")   //@Field 对应数据库中的列
    //analyzer 存的时候是用这个分词器
    //searchAnalyzer搜索的时候用的这个分词器
    /**文章正文.*/
    private String content;

    /**审核状态.*/
    private String state;


    /**
     *
     * 写到这个类的都是要存储的；存储的不一定要搜索
     * id和状态呢，不用索引，不用分词；但是存储。
     *
     * 有个问题：若多个字段，描述；内容字段是索引的，分词的，但是不存储？应该怎么写
     * 现在写到 这个类的都是存储的，但是不一定分词和索引
     *
     */
}
