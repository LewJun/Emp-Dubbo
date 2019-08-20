package com.example.lewjun;

import com.example.lewjun.service.EmpService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Unit test for simple App.
 */
public class AppTest extends SpringJunitTest {

    @Autowired
    private EmpService empService;

    @Test
    public void test1() {
        LOGGER.info("【empList:{}】", empService.findAll());
    }
}
