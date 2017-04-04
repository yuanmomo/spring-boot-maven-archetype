package net.yuanmomo.springboot.test.controller.unit;

import com.google.gson.Gson;
import net.yuanmomo.springboot.Application;
import net.yuanmomo.springboot.util.AjaxResponseBean;
import net.yuanmomo.springboot.util.PaginationBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Hongbin.Yuan on 2017-04-04 05:22.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
@Transactional
public class BaseUnitTest {

    @Autowired protected MockMvc mvc;

    protected static Gson gson = new Gson();

    /**
     * fix Druid WebAppStat NullPointerException.
     */
    @Autowired private FilterRegistrationBean filterRegistrationBean;

    @Before
    public void before() {
        try {
            MockFilterConfig mockFilterConfig = new MockFilterConfig();
            filterRegistrationBean.getFilter().init(mockFilterConfig);
        } catch (ServletException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    /**
     *
     * @param pageNum
     * @param numPerPage
     * @param totalCount
     * @param totalPages
     * @return
     */
    protected PaginationBean getPaginationBean(long pageNum, long numPerPage, long totalCount, long totalPages) {
        PaginationBean paginationBean = new PaginationBean();
        paginationBean.setPageNum(pageNum);
        paginationBean.setNumPerPage(numPerPage);
        paginationBean.setTotalCount(totalCount);
        paginationBean.setTotalPages(totalPages);
        return paginationBean;
    }


    /**
     *  get by id.
     *
     * @param url
     * @param id
     * @return
     * @throws Exception
     */
    protected MockHttpServletResponse getById(String url,Object id) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(url)
                .param("id", String.valueOf(id))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse();
    }

    protected long getLongFromReturnValue(MockHttpServletResponse response) throws UnsupportedEncodingException {
        Object value = gson.fromJson(response.getContentAsString(),AjaxResponseBean.class).getReturnValue();
        if(value instanceof Double){
            Double doubleValue = (Double) value;
            return doubleValue.longValue();
        }
        return 0L;
    }

}
