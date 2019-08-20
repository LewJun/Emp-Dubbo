package com.example.lewjun;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LewJun
 * @version $Id: SpringJunitTest.java, v 0.1 2017年4月13日 下午2:26:50 LewJun Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 配置了@ContextConfiguration注解并使用该注解的locations属性指明spring和配置文件之后，
// 因为是引用的不同几个模块，所以这里使用classpath*
@ContextConfiguration(locations = {"classpath*:spring/spring-*.xml"})
public class SpringJunitTest {
    static final Logger LOGGER = LoggerFactory.getLogger(SpringJunitTest.class);
}
