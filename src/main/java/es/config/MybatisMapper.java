package es.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * IpxMapper
 *
 * @author liuzh_3nofxnp
 * @since 2015-09-06 21:53
 */
public interface MybatisMapper<T> extends Mapper<T>, MySqlMapper<T> {

}