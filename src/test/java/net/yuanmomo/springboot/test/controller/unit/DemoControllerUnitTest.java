package net.yuanmomo.springboot.test.controller.unit;

/**
 * Created by Hongbin.Yuan on 2017-04-03 21:34.
 */


import net.yuanmomo.springboot.bean.Demo;
import net.yuanmomo.springboot.util.AjaxResponseBean;
import net.yuanmomo.springboot.util.PaginationBean;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

public class DemoControllerUnitTest extends BaseUnitTest {

    private static final String BASE_MAPPING = "/backend/demo/";

    String insert = "insert.do";
    String getDemoByKey = "getDemoByKey.do";
    String selectDemoList = "selectDemoList.do";
    String updateSaveDemo = "updateSaveDemo.do";


    @Test
    public void testInsert() throws Exception {

        /**********************************************************************************************************
         ***   1.  insert
         **********************************************************************************************************/
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_MAPPING + insert)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        Demo insertDemo = getDemo(9999999L, 8888881, 7777771L);

        // set valid parameter
        requestBuilder.param("number", insertDemo.getNumber() + "");
        requestBuilder.param("version", insertDemo.getVersion() + "");

        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();

        // get expected value
        AjaxResponseBean expected = AjaxResponseBean.getReturnValueResponseBean(null);

        // check return value
        JSONAssert.assertEquals(gson.toJson(expected), response.getContentAsString(), false);

        /**********************************************************************************************************
         ***   2. get and check
         **********************************************************************************************************/
        insertDemo.setId(getLongFromReturnValue(response));

        response = getById(BASE_MAPPING + getDemoByKey, insertDemo.getId());
        // get expected value
        expected = AjaxResponseBean.getReturnValueResponseBean(insertDemo);

        // check return value
        JSONAssert.assertEquals(gson.toJson(expected), response.getContentAsString(), false);
    }

    @Test
    @Sql("classpath:sql/demo.sql")
    public void testGetDemoByKey() throws Exception {

        // get expected value
        Demo expectedDemo = getDemo(9999999L, 888888, 777777L);

        MockHttpServletResponse response = getById(BASE_MAPPING + getDemoByKey, expectedDemo.getId());

        // check response
        AjaxResponseBean expected = AjaxResponseBean.getReturnValueResponseBean(expectedDemo);
        JSONAssert.assertEquals(gson.toJson(expected), response.getContentAsString(), false);
    }

    @Test
    @Sql("classpath:sql/demo.sql")
    public void testSelectDemoList() throws Exception {

        /**********************************************************************************************************
         ***   1. pass invalid parameter
         **********************************************************************************************************/
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_MAPPING + selectDemoList)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        MockHttpServletResponse response = mvc.perform(requestBuilder)
                .andReturn().getResponse();

        // check response
        AjaxResponseBean expected = AjaxResponseBean.Const.PARAMETER_INVALID_ERROR_RESPONSE_BEAN;
        JSONAssert.assertEquals(gson.toJson(expected), response.getContentAsString(), false);

        /**********************************************************************************************************
         ***   2. pass valid parameter
         **********************************************************************************************************/
        PaginationBean<Demo> expectedPaginationBean = getPaginationBean(1, 20, 2, 1);
        List<Demo> expectedDemoList = new ArrayList<>();
        expectedDemoList.add(getDemo(9999999L, 888888, 777777L));
        expectedDemoList.add(getDemo(99999999L, 8888888, 7777777L));
        expectedPaginationBean.setResult(expectedDemoList);

        // set valid parameter
        requestBuilder.param("pageNum", expectedPaginationBean.getPageNum() + "");
        requestBuilder.param("numPerPage", expectedPaginationBean.getNumPerPage() + "");

        response = mvc.perform(requestBuilder).andReturn().getResponse();

        // get expected value
        expected = AjaxResponseBean.getReturnValueResponseBean(null);

        // check return value
        JSONAssert.assertEquals(gson.toJson(expected), response.getContentAsString(), false);

    }


    @Test
    @Sql("classpath:sql/demo.sql")
    public void testUpdateSaveDemo() throws Exception {

        /**********************************************************************************************************
         ***   1. pass invalid parameter
         **********************************************************************************************************/
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_MAPPING + updateSaveDemo)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        MockHttpServletResponse response = mvc.perform(requestBuilder)
                .andReturn().getResponse();

        // check response
        AjaxResponseBean expected = AjaxResponseBean.Const.PARAMETER_INVALID_ERROR_RESPONSE_BEAN;
        JSONAssert.assertEquals(gson.toJson(expected), response.getContentAsString(), false);

        /**********************************************************************************************************
         ***   2. pass valid parameter to update
         **********************************************************************************************************/
        Demo updatedDemo = getDemo(9999999L, 8888881, 7777771L);

        // set valid parameter
        requestBuilder.param("id", updatedDemo.getId() + "");
        requestBuilder.param("number", updatedDemo.getNumber() + "");
        requestBuilder.param("version", updatedDemo.getVersion() + "");

        response = mvc.perform(requestBuilder).andReturn().getResponse();

        // get expected value
        expected = AjaxResponseBean.Const.SUCCESS_RESPONSE_BEAN;

        // check return value
        JSONAssert.assertEquals(gson.toJson(expected), response.getContentAsString(), false);

        /**********************************************************************************************************
         ***   3. get and check
         **********************************************************************************************************/
        response = getById(BASE_MAPPING + getDemoByKey, updatedDemo.getId());
        // get expected value
        expected = AjaxResponseBean.getReturnValueResponseBean(updatedDemo);

        // check return value
        JSONAssert.assertEquals(gson.toJson(expected), response.getContentAsString(), false);
    }

    /**
     * get instance.
     *
     * @param id
     * @param number
     * @param version
     * @return
     */
    protected Demo getDemo(Long id, Integer number, Long version) {
        Demo demo = new Demo();
        demo.setId(id);
        demo.setNumber(number);
        demo.setVersion(version);
        return demo;
    }
}

