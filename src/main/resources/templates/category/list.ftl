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
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>类目id</th>
                            <th>类目名</th>
                            <th>类目编号</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list productCategoryPage.getContent() as productCategory >
                <tr>
                    <td>${productCategory.categoryId}</td>
                    <td>${productCategory.categoryName}</td>
                    <td>${productCategory.categoryType}</td>
                    <td>
                        <a href="/sell/seller/category/index?categoryId=${productCategory.categoryId}">修改</a>
                    </td>
                    <#--<td>
                        <#if productCategory.getProductStatusEnum().getMessage() == "在架">
                            <a href="/sell/seller/category/off_sale?productId=${productInfo.productId}">下架</a>
                        <#else>
                            <a href="/sell/seller/category/on_sale?productId=${productInfo.productId}">上架</a>
                        </#if>
                    </td>-->
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-12 column">
                <ul class="pagination pull-right">
                <#--lt代表小于 gt代表大于 lte代表小于等于 gte代表大于等于-->
            <#if currentPage lte 1>
            <li class="disabled"><a href="javascript::">上一页</a></li>
            <#else>
            <li><a href="/sell/seller/category/list?page=${currentPage -1}&size=${size}">上一页</a></li>
            </#if>

            <#list 1..productCategoryPage.getTotalPages() as index >
                <#if currentPage == index>
            <li class="disabled"><a href="javascript::">${index}</a></li>
                <#else>
            <li><a href="/sell/seller/category/list?page=${index}&size=${size}">${index}</a></li>
                </#if>
            </#list>


            <#if currentPage gte productCategoryPage.getTotalPages()>
            <li class="disabled"><a href="javascript::">下一页</a></li>
            <#else>
            <li><a href="/sell/seller/category/list?page=${currentPage +1}&size=${size}">下一页</a></li>
            </#if>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>