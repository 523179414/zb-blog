package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @version V1.0
 * @date 2018年7月11日
 * @author superzheng
 */
@SpringBootApplication
@MapperScan(basePackages = "com.nbclass.mapper")
public class BlogApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
