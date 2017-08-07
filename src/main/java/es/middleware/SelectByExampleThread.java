package es.middleware;

import com.github.pagehelper.PageHelper;
import es.config.MybatisMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * ${DESCRIPTION}
 *
 * @author joels
 * @create 2017-07-06 16:59
 **/
public class SelectByExampleThread<V> implements Callable<List<V>> {
    private MybatisMapper mapper;
    private Example example;
    private Map<String, Object> params;

    public SelectByExampleThread(MybatisMapper mapper, Example example, Map<String, Object> params) {
        this.mapper = mapper;
        this.example = example;
        this.params = params;
    }

    @Override
    public List<V> call() throws Exception {
        for(Map.Entry<String,Object> entry : params.entrySet()){
            example.createCriteria().andEqualTo(entry.getKey(), entry.getValue());
        }
        return mapper.selectByExample(example);
    }
}
