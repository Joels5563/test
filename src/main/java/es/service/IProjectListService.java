package es.service;

import es.domain.TVdProptProject;

import java.util.List;

/**
 * 项目列表服务
 *
 * @author joels
 * @create 2017-05-18 11:36
 **/
public interface IProjectListService {

    /**
     * 获取所有的不动产项目数据
     *
     * @return 项目列表
     */
    List<TVdProptProject> getProjectList();
}
