package com.neuedu.sell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
/**
 * 商品类别实体类
 */
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;
    /* 类别名字 */
    private String categoryName;
    /* 类别编号 */
    private Integer categoryType;
   /*  //创建时间
    private Date createTime;
     //修改时间
    private Date updateTime;*/
}
