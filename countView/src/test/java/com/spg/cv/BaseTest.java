/**
 * 
 */

package com.spg.cv;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

/**
 * 项目名称：happylesson
 * 
 * @description:
 * 
 * @author Administrator
 * 
 * @create_time：2014年7月22日 上午11:42:21
 * 
 * @version V1.0.0
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
@TestExecutionListeners(
{ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class BaseTest
{

    protected static ApplicationContext context = null;

    /**
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        context = new ClassPathXmlApplicationContext("classpath:spring-*.xml");
    }

    /**
     * 
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    @Test
    public void test()
    {
        Assert.assertEquals(1, 1);
    }
}
