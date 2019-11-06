package sqlsession;

import config.Configuration;
import excutor.Executor;
import excutor.SimpleExecutor;
import lombok.AllArgsConstructor;
import statement.MappedStatement;

import java.util.List;

/**
 * @author lgq
 * @date 2019/10/28
 */
@AllArgsConstructor
public class DefaultSqlSession implements SqlSession{

    private Configuration configuration;

    public <T> T selectOne(String statementId, Object args) {
        List<T> list = this.select(statementId, args);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public <T> List<T> select(String statementId, Object args) {
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        if (mappedStatement == null) {
            return null;
        }
        Executor executor = new SimpleExecutor();
        return executor.query(mappedStatement, configuration, args);
    }
}
