package com.neuedu.sell.controller;

import com.neuedu.sell.entity.ProductCategory;
import com.neuedu.sell.from.CategoryForm;
import com.neuedu.sell.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "2") Integer size) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductCategory> productCategoryPage = productCategoryService.findAll(request);
        ModelAndView model = new ModelAndView("category/list");
        model.addObject("productCategoryPage",productCategoryPage);
        model.addObject("currentPage",page);
        model.addObject("size",size);
        return model;
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId){
        ModelAndView modelAndView = new ModelAndView("category/index");
        if (!StringUtils.isEmpty(categoryId)){
            ProductCategory productCategory = productCategoryService.findOne(categoryId);
            modelAndView.addObject("productCategory",productCategory);
        }
        /*List<ProductCategory> productCategoryList = productCategoryService.findAll();
        modelAndView.addObject("productCategoryList",productCategoryList);*/
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(CategoryForm categoryForm){
        //1.判断是新增还是修改
        if (StringUtils.isEmpty(categoryForm.getCategoryId())){
            //新增
            ProductCategory productCategory = new ProductCategory();
            BeanUtils.copyProperties(categoryForm,productCategory);
           /* productCategory.setCategoryId(KeyUtils.generateUniqueKey());*/
            productCategoryService.save(productCategory);
        }else {
            //修改
            ProductCategory productCategory = productCategoryService.findOne(categoryForm.getCategoryId());
            //把前台修改好的对象赋值过去
            BeanUtils.copyProperties(categoryForm,productCategory);
            productCategoryService.save(productCategory);
        }
        ModelAndView model = new ModelAndView("redirect:list");
        return model;
    }
}
