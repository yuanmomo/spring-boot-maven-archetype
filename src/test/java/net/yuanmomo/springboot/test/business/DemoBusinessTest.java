package net.yuanmomo.springboot.test.business;

import net.yuanmomo.springboot.bean.Demo;
import net.yuanmomo.springboot.bean.DemoParam;
import net.yuanmomo.springboot.mybatis.mapper.DemoMapper;
import net.yuanmomo.springboot.test.BaseUnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

/**
 * Created by Hongbin.Yuan on 2017-06-08 16:47.
 */

public class DemoBusinessTest extends BaseUnitTest {

    @Autowired DemoMapper demoMapper;

    @Test
    @Sql("classpath:sql/demo-business-test.sql")
    public void testSelectOneByExample() throws Exception {
        DemoParam demoParam = new DemoParam();
        demoParam.createCriteria().andNumberEqualTo(8888888);
        demoParam.setOrderByClause(" id desc ");

        Demo demo = this.demoMapper.selectOneByExample(demoParam);
        Assert.assertTrue(demo.getVersion() == 1111111);

        demoParam.setOrderByClause(" id asc ");

        demo = this.demoMapper.selectOneByExample(demoParam);
        Assert.assertTrue(demo.getVersion() == 2222222);
    }

}
