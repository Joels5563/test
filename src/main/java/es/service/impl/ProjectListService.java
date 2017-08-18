package es.service.impl;

import com.github.pagehelper.PageHelper;
import es.dao.TVdProptProjectMapper;
import es.domain.TVdProptProject;
import es.middleware.SelectAllThread;
import es.middleware.SelectByExampleThread;
import es.service.IProjectListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目列表服务
 *
 * @author joels
 * @create 2017-05-18 11:38
 **/
@Service
public class ProjectListService implements IProjectListService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TVdProptProjectMapper projectMapper;

    @Override
    public List<TVdProptProject> getProjectList() {
        try {
            return new SelectAllThread<TVdProptProject>(projectMapper,null).call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> params = new HashMap<>();
        params.put("projectNo","J1");

        try {
            return new SelectByExampleThread<TVdProptProject>(projectMapper, new Example(TVdProptProject.class),params).call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
