package test;

import builder.SqlSessionFactory;
import builder.SqlSessionFactoryBuilder;
import builder.XmlConfigBuilder;
import config.Configuration;
import resource.Resource;
import sqlsession.SqlSession;
import test.domain.User;

import java.io.InputStream;

/**
 * @author lgq
 * @date 2019/10/29
 */
public class Test {


    public void build() throws Exception {
        // 指定全局配置文件的类路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resource.getResourceAsStream(resource);

        XmlConfigBuilder builder = new XmlConfigBuilder(inputStream);
        Configuration configuration = builder.parse();
        System.out.println(configuration);
    }

    public void execute() throws Exception {
        // 指定全局配置文件的类路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resource.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSqlSession();

        User user = sqlSession.selectOne("findUserById", 1);

        System.out.println(user);
    }

    public static void main(String[] args) {
        Test test = new Test();
        try {
            test.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
