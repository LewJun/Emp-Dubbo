package com.example.lewjun.controller;

import com.example.lewjun.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LewJun
 */
@Controller
@RequestMapping("emp")
public class EmpController extends BaseController {
    @Autowired
    private EmpService empService;

    @RequestMapping("index")
    public String index() {
        LOGGER.info("【empService.findAll() {}】", empService.findAll());
        return "emp/index";
    }
}
