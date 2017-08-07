package es.service.impl;

import com.alibaba.fastjson.JSON;
import es.domain.TVdProptProject;
import es.http.HttpUtil;
import es.http.Put;
import es.service.IIndexProjectListService;
import es.service.IProjectListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 索引项目列表
 *
 * @author joels
 * @create 2017-05-26 11:14
 **/
@Service
public class IndexProjectListService implements IIndexProjectListService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IProjectListService projectListService;

    @Override
    public int indexProjectList() {
        List<TVdProptProject> projectList =
                projectListService.getProjectList();
        logger.info("获取项目数量为:{}", projectList.size());
        int putSuccess = 0;
        int putFailed = 0;
        for (TVdProptProject project : projectList){
            String uri = "/property/project/" + project.getProjectId();
            String result = HttpUtil.put(uri, JSON.toJSONString(project));
            if (1 == JSON.parseObject(result)
                    .getJSONObject("_shards")
                    .getInteger("successful")) {
                putSuccess++;
            } else {
                putFailed++;
            }
        }
        logger.info("索引项目列表,成功--{},失败--{}", putSuccess, putFailed);
        return putSuccess;
    }
}
