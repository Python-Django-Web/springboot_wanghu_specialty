package com.cwh.springbootMybatis;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@ServletComponentScan("com.cwh.springbootMybatis")//使用注解的方式注册servlet需要在SpringbootHelloApplication.java中添加@ServletComponentScan注解
//@ComponentScan("com.cwh.springbootMybatis")
@EnableScheduling //开启定时器
public class Application implements CommandLineRunner{

	/*@Autowired
	private MultipartConfigElement multipartConfigElement; */
	
	@Autowired
    DataSource dataSource;
	
	/**
     * 修改DispatcherServlet默认配置
     *
     * @param dispatcherServlet
     * @author LingDu
     */
  /*  @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        registration.getUrlMappings().clear();
        //这里需要将附件配置设置进去，否则请求不过来 报异常：Required request part 'file' is not present
        registration.setMultipartConfig(multipartConfigElement); 
        registration.addUrlMappings("*.action"); //只有*.action 的请求能通过
        return registration;
    }*/
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * 查看用了什么数据库连接池
	 */
	public void run(String... args) throws Exception {
        System.out.println("DATASOURCE = " + dataSource);
    }
}
