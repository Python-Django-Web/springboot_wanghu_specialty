package com.cwh.springbootMybatis.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author:wanghu
 * @Description:
 * @Date:Create in 17:35 2018/3/12
 * @Modified By:
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

	/**
	 * 注册 拦截器
	 * @see "addPathPatterns()" 添加拦截路径，【/**】默认拦截所有
	 * @see "excludePathPatterns()" 添加通过的路劲，可以和上面的方法连用
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 设置拦截的路径、不拦截的路径、优先级等等
		//registry.addInterceptor(new SessionInterceptor())
				//.addPathPatterns("/getUserInfo/**")
				//.excludePathPatterns("/**");
		//拦截器配置“/**”意思拦截所有的路径
		registry.addInterceptor(new SessionInterceptor())
		.addPathPatterns("/**");
	}
}
