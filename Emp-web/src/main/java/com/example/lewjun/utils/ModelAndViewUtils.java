package com.example.lewjun.utils;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * ModelAndView工具类
 *
 * @author LewJun
 */
public class ModelAndViewUtils {

    /**
     * 渲染页面
     *
     * @param viewName 视图名称
     * @return ModelAndView
     */
    public static ModelAndView render(String viewName) {
        return new ModelAndView(viewName);
    }

    /**
     * 渲染页面并返回数据
     *
     * @param viewName 视图名称
     * @param modelMap 要返回的数据
     * @return ModelAndView
     */
    public static ModelAndView render(String viewName, Map<String, ?> modelMap) {
        ModelAndView mav = render(viewName);
        if (modelMap == null || modelMap.isEmpty()) {
            return mav;
        }
        mav.addAllObjects(modelMap);
        return mav;
    }
}
