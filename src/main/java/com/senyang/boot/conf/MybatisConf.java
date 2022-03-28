package com.senyang.boot.conf;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConf {
    @Bean
    public MybatisPlusInterceptor interceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //这是分页拦截器
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor();
        //pageInterceptor.setMaxLimit(500L);这是设置最大单页限制数量，默认500条，-1不受限制
        //pageInterceptor.setOverflow(false);这是设置请求页面大于最大页后操作，true调回首页，false则继续请求，默认为false

        interceptor.addInnerInterceptor(pageInterceptor);
        return interceptor;
    }
}
