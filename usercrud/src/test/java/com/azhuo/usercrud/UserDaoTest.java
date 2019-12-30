package com.azhuo.usercrud;

import com.azhuo.usercrud.dao.UserDao;
import com.azhuo.usercrud.pojo.UserInfo;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    /**
     * 根据添加组合查询
     */
    @Test
    public void test_where(){
        // 查询姓名包含“a”, 密码是6位
        List<UserInfo> userInfos = userDao.findAll(new Specification<UserInfo>() {
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                // 构造条件：查询username包含“a”的
                Predicate preUsername = criteriaBuilder.like(root.get("username"), "%a%");

                // 查询密码是6位
                Expression<Integer> expression = criteriaBuilder.length(root.get("password"));
                Predicate prePassword = criteriaBuilder.equal(expression, "3");

                // 组合条件
                query.where(preUsername, prePassword);

                return null;
            }
        });

        userInfos.stream().forEach(userInfo -> System.out.println(userInfo));

    }

    @Test
    public void test_findById(){
        Optional<UserInfo> opt = userDao.findById(1L);

        UserInfo userInfo = opt.get();
        System.out.println(userInfo);
    }

    @Test
    public void test_findAll(){
        List<UserInfo> userInfos = userDao.findAll();
        userInfos.stream().forEach(userInfo -> {
            System.out.println(ReflectionToStringBuilder.toString(userInfo));
        });
    }
}
