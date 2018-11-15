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
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label for="exampleInputEmail1">类目名</label>
                            <input type="text" class="form-control" id="categoryName" name="categoryName" value="${(productCategory.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">类目编号</label>
                            <input type="text" class="form-control" id="categoryType" name="categoryType" value="${(productCategory.categoryType)!''}"/>
                        </div>
                        <#--<div class="form-group">
                            <label for="categoryType">类目编号</label>
                            <select id="categoryType" name="categoryType" class="form-control">
                            <#list productCategoryList as category>
                                <option value="${category.categoryType}" <#if ((productInfo.categoryType)!1) == category.categoryType>selected</#if>>${category.categoryName}</option>
                            </#list>
                            </select>
                        </div>-->
                        <input type="hidden" value="${(productCategory.categoryId)!''}" name="categoryId">
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