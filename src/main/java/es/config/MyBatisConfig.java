package es.config;

/**
 * spring boot集成mybatis的基本入口
 * 1）创建数据源
 * 2）创建SqlSessionFactory
 *
 * @author joels
 * @create 2017-04-20 14:23
 **/

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
//@MapperScan
@Deprecated
public class MyBatisConfig {

    @Autowired
    private Environment env;

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

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
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
