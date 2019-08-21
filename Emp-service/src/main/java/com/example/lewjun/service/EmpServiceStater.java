package com.example.lewjun.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * EmpService服务启动类
 *
 * @author LewJun
 */
public class EmpServiceStater {
    public static void main(String[] args) throws IOException {
        // 因为是引用的不同几个模块，所以这里使用classpath*
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath*:spring/spring-*.xml");
        context.start();
        System.in.read();
    }
}
