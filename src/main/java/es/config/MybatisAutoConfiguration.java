package es.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * mybatis自动配置
 *
 * @author joels
 * @create 2017-07-06 15:37
 **/
@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MybatisAutoConfiguration {
    @Autowired
    private Environment env;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 创建数据源
     */
    @Bean
    public DataSource getDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("jdbc.driverClassName"));
        props.put("url", env.getProperty("jdbc.url"));
        props.put("username", env.getProperty("jdbc.username"));
        props.put("password", env.getProperty("jdbc.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);//指定数据源(这个必须有，否则报错)
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置
        Interceptor interceptor = new PageInterceptor();
        interceptor.setProperties(getPageProperties());
        fb.setPlugins(new Interceptor[]{interceptor, new PerformanceInterceptor()});
        return fb.getObject();
    }

    /**
     * 分页插件
     */
    @Bean
    public PageHelper pageHelper(DataSource dataSource) {
        logger.info("注册MyBatis分页插件PageHelper");
        PageHelper pageHelper = new PageHelper();
        pageHelper.setProperties(getPageProperties());
        return pageHelper;
    }

    /**
     * 获取pageInterceptor的配置信息
     *
     * @return 配置信息
     */
    private Properties getPageProperties() {
        Properties properties = new Properties();
        properties.setProperty("helperDialect", env.getProperty("helperDialect"));
        properties.setProperty("reasonable", env.getProperty("reasonable"));
        properties.setProperty("supportMethodsArguments", env.getProperty("supportMethodsArguments"));
        properties.setProperty("params", env.getProperty("params"));
        properties.setProperty("autoRuntimeDialect", env.getProperty("autoRuntimeDialect"));
        return properties;
    }
}
