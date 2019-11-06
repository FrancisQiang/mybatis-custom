package builder;

import config.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import sqlsession.DefaultSqlSession;
import sqlsession.SqlSession;

/**
 * @author lgq
 * @date 2019/10/29
 */
@Data
@AllArgsConstructor
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }
}
