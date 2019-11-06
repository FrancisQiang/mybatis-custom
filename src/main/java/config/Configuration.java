package config;

import lombok.Data;
import statement.MappedStatement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lgq
 * @date 2019/10/28
 */
@Data
public class Configuration {

    private DataSource dataSource;

    private Map<String, MappedStatement> mappedStatementMap = new HashMap<String, MappedStatement>();

    public void addMapStatement(MappedStatement mappedStatement) {
        this.mappedStatementMap.put(mappedStatement.getId(), mappedStatement);
    }

    public MappedStatement getMappedStatement(String statementId) {
        return mappedStatementMap.get(statementId);
    }

}
