package com.mybatis.demo.test;

import com.mybatis.demo.MyBatisUtil;
import com.mybatis.demo.domain.Scholar;
import com.mybatis.demo.mapping.ScholarMapperI;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MybatisAnnotationTest {

    @Test
    public void testSelect() {
        SqlSession session = MyBatisUtil.getSqlSession();
        ScholarMapperI mapper = session.getMapper(ScholarMapperI.class);
        Scholar scholar = mapper.getById(1191392);
        session.close();
        System.out.println(scholar);
    }

    @Test
    public void testGetUserCount() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUserCount是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.mybatis.demo.mapping.userMapper.getUserCount";//映射sql的标识字符串
        Map<String, Integer> parameterMap = new HashMap<String, Integer>();
        parameterMap.put("sexid", 1);
        parameterMap.put("usercount", -1);
        sqlSession.selectOne(statement, parameterMap);
        Integer result = parameterMap.get("usercount");
        System.out.println(result);
        sqlSession.close();
    }
}
