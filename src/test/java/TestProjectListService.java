import es.Application;
import es.domain.TVdProptProject;
import es.http.HttpUtil;
import es.service.IIndexProjectListService;
import es.service.IProjectListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author joels
 * @create 2017-05-18 11:44
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestProjectListService {
    @Autowired
    private IProjectListService projectListService;
    @Autowired
    private IIndexProjectListService indexProjectListService;

    @Test
    public void test() {
        List<TVdProptProject> projectList =
                projectListService.getProjectList();
        System.out.println(projectList.size());
    }

    @Test
    public void index(){
        System.out.println(indexProjectListService.indexProjectList());
    }

    @Test
    public void search(){
        String result = HttpUtil.post("/property/project/_search","{\n" +
                "  \"aggs\": {\n" +
                "    \"all_interests\": {\n" +
                "      \"terms\": { \"field\": \"interests\" }\n" +
                "    }\n" +
                "  }\n" +
                "}");
        System.out.println(result);
    }
}
