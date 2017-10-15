package cc.jasonos.mybatis.test;

import cc.jasonos.mybatis.mapper.UserMapper;
import cc.jasonos.mybatis.pojo.User;
import cc.jasonos.mybatis.pojo.UserExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Jason on 2017/10/15.
 */
public class JunitTest {

    @Test
    public void testMapper() throws Exception{
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper mapper = ac.getBean(UserMapper.class);

        //查找姓名带有明且性别男的用户
        UserExample userExample = new UserExample();
        String username = "明";
        userExample.createCriteria().andSexEqualTo("1").andUsernameLike("%" + username +"%");
        userExample.setOrderByClause("id desc");

        int i = mapper.countByExample(userExample);
        System.out.println(i);

        //根据id查询用户
        User user = mapper.selectByPrimaryKey(10);
        System.out.println(user);

        //按照条件查询
        List<User> userList = mapper.selectByExample(userExample);
        for (User user1:userList) {
            System.out.println(user1.getId());
        }

        int i1 = mapper.deleteByPrimaryKey(24);
        System.out.println(i1);


        User user1 = new User();
        user1.setUsername("js");
        user1.setSex("1");
        user1.setId(26);
        int i2 = mapper.updateByPrimaryKeySelective(user1);
        System.out.println(i2);

        User user2 = new User();
        user2.setUsername("jyf");
        user2.setSex("2");
        user2.setId(27);
        int insert = mapper.insertSelective(user2);
        System.out.println(insert);

    }

}
