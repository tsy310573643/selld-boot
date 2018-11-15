package com.neuedu.sell.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neuedu.sell.enums.ProductStatusEnum;
import com.neuedu.sell.utils.EnumUtils;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;

    /* 商品名 */
    private String productName;

    /* 商品单价 */
    private BigDecimal productPrice;

    /* 库存 */
    private Integer productStock;

    /* 商品描述 */
    private String productDescription;

    /* 商品小图 */

    private String productIcon;

    /* 商品状态， UP"在架"，DOME“下架”*/
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /* 类目编号 */
    private Integer categoryType;

    /* 创建时间 */
    private Date createTime;
    /* 更新时间 */
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtils.getEnumByCode(productStatus,ProductStatusEnum.class);
    }


}
