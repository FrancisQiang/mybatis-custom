package sqlsession;

import java.util.List;

/**
 * @author lgq
 * @date 2019/10/28
 */
public interface SqlSession {

    /**
     * 选取一个
     * @param statementId sql语句id
     * @param args 参数
     * @param <T> 泛型
     * @return 列表第一个元素
     */
    <T> T selectOne(String statementId, Object args);

    /**
     * 获取列表
     * @param statementId sql语句Id
     * @param args 参数
     * @param <T> 泛型
     * @return 列表
     */
    <T> List<T> select(String statementId, Object args);


}
