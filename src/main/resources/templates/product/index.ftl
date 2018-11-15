<!DOCTYPE html>
<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/product/save">
                    <div class="form-group">
                        <label for="exampleInputEmail1">商品名</label>
                        <input type="text" class="form-control" id="productName" name="productName" value="${(productInfo.productName)!''}"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">商品单价</label>
                        <input type="number" class="form-control" id="productPrice" name="productPrice" value="${(productInfo.productPrice)!''}"/>
                    </div>
                    <div class="form-group">
                         <label for="exampleInputEmail1">商品库存</label>
                         <input type="number" class="form-control" id="productStock" name="productStock" value="${(productInfo.productStock)!''}"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">商品描述</label>
                        <input type="text" class="form-control" id="productDescription" name="productDescription" value="${(productInfo.productDescription)!''}"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">商品小图</label>
                        <input type="text" class="form-control" id="productIcon" name="productIcon" value="${(productInfo.productIcon)!''}"/>
                    </div>
                    <div class="form-group">
                        <label for="categoryType">商品类别</label>
                        <select id="categoryType" name="categoryType" class="form-control">
                            <#list productCategoryList as category>
                            <option value="${category.categoryType}" <#if ((productInfo.categoryType)!1) == category.categoryType>selected</#if>>${category.categoryName}</option>
                            </#list>
                        </select>
                    </div>
                    <input type="hidden" value="${(productInfo.productId)!''}" name="productId">
                    <div class="checkbox">
                    </div> <button type="submit" class="btn btn-primary">确定</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>