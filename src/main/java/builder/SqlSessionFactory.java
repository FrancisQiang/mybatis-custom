package builder;

import sqlsession.SqlSession;

/**
 * @author lgq
 * @date 2019/10/29
 */
public interface SqlSessionFactory {

    /**
     * 创建sqlSession
     * @return SqlSession
     */
    SqlSession openSqlSession();

}
