package com.example.lewjun.controller;

import com.example.lewjun.utils.ModelAndViewUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 父Controller
 *
 * @author LewJun
 */
public class BaseController {
    static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /**
     * 渲染页面并返回数据
     *
     * @param viewName 视图名称
     * @param modelMap 要返回的数据
     * @return ModelAndView
     */
    static ModelAndView render(String viewName, Map<String, ?> modelMap) {
        return ModelAndViewUtils.render(viewName, modelMap);
    }
}
