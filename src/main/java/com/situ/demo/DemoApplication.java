package com.situ.demo;	// 声明包

// 引入 两个类型
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @ 开头的, 这叫注解
// 给DemoApplication类, 冠名成一个SpringBoot的应用程序
@SpringBootApplication
public class DemoApplication {

	// 入口
    public static void main(String[] args) {
    	// 启动Spring项目, 
        SpringApplication.run(DemoApplication.class, args);
    }
}
