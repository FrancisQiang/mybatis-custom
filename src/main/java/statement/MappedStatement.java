package statement;

import builder.SqlSource;
import config.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import sqlsession.StatementType;

/**
 * @author lgq
 * @date 2019/10/28
 */
@Data
@AllArgsConstructor
public class MappedStatement {

    private Configuration configuration;

    private String id;

    private StatementType statementType;

    private SqlSource sqlSource;

    private Class<?> parameterTypeClass;

    private Class<?> resultTypeClass;

}
