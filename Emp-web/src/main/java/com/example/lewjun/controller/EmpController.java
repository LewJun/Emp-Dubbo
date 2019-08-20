package com.example.lewjun.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.lewjun.bean.po.Emp;
import com.example.lewjun.service.EmpService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author LewJun
 */
@Controller
@RequestMapping("emp")
public class EmpController extends BaseController {
    @Reference
    private EmpService empService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView index() {
        Map<String, List<Emp>> modelMap = Maps.newHashMap();
        List<Emp> empList = empService.findAll();
        LOGGER.info("【empList:{}】", empList);
        modelMap.put("empList", empList);
        return render("emp/index", modelMap);
    }
}
