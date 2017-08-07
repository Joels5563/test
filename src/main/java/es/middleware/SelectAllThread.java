package es.middleware;

import com.github.pagehelper.PageHelper;
import es.config.MybatisMapper;
import es.domain.TVdProptProject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 查询中间件线程
 *
 * @author joels
 * @create 2017-07-06 9:32
 **/
public class SelectAllThread<V> implements Callable<List<V>> {
    private MybatisMapper mapper;
    private Map<String, Object> params;

    public SelectAllThread(MybatisMapper mapper, Map<String, Object> params) {
        this.mapper = mapper;
        this.params = params;
    }

    @Override
    public List<V> call() throws Exception {
        PageHelper.clearPage();
        PageHelper.startPage(1, 10);
        return mapper.selectAll();
    }
}
