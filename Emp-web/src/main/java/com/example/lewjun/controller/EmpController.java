package com.example.lewjun.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.lewjun.dto.EmpDTO;
import com.example.lewjun.service.EmpService;
import com.example.lewjun.vo.EmpVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author LewJun
 */
@Slf4j
@Controller
@RequestMapping("emp")
public class EmpController extends BaseController {
    @Reference
    private EmpService empService;

    @GetMapping("index")
    public ModelAndView index() {
        Map<String, List<EmpVO>> modelMap = Maps.newHashMap();
        List<EmpDTO> empDtoList = empService.findAll();
        List<EmpVO> empVOList = Lists.newArrayList();
        for (EmpDTO empDTO : empDtoList) {
            EmpVO empVO = new EmpVO();
            BeanUtils.copyProperties(empDTO, empVO);
            empVOList.add(empVO);
        }
        log.info("【empVOList:{}】", empVOList);
        modelMap.put("empList", empVOList);
        return render("emp/index", modelMap);
    }
}
