package builder;

import config.Configuration;
import handler.GenericTokenParse;
import handler.ParameterMappingHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import statement.BoundSql;
import statement.MappedStatement;

/**
 * @author lgq
 * @date 2019/10/28
 */
@AllArgsConstructor
@Data
public class SqlSource {

    private String text;

    public BoundSql getBoundSql(MappedStatement mappedStatement,
                                Object parameterObject) {
        ParameterMappingHandler handler = new ParameterMappingHandler(mappedStatement.getParameterTypeClass());
        GenericTokenParse genericTokenParse = new GenericTokenParse("#{", "}", handler);
        String sql = genericTokenParse.parse(text);
        return new BoundSql(sql, handler.getParameterMappings(), parameterObject);
    }

}
