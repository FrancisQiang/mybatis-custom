package handler;

import config.Configuration;
import lombok.AllArgsConstructor;
import statement.BoundSql;
import statement.ParameterMapping;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * @author lgq
 * @date 2019/10/29
 */
@AllArgsConstructor
public class PreparedStatementHandler implements StatementHandler {

    private Configuration configuration;
    private Object parameterObject;


    public ResultSet doExecute(Statement statement) {
        ResultSet resultSet = null;
        try {
            resultSet = ((PreparedStatement)statement).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Statement getStatement(String sql) {
        PreparedStatement preparedStatement = null;
        DataSource dataSource = configuration.getDataSource();
        try {
            Connection connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public void setParameter(Statement statement, BoundSql boundSql) {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        for (int i = 0; i < parameterMappings.size(); i++) {
            Class<?> parameterTypeClass = parameterMappings.get(i).getParameterTypeClass();
            TypeHandler typeHandler = getTypeHandler(parameterTypeClass);
            if (typeHandler != null) {
                typeHandler.setParameter(i + 1, preparedStatement, parameterObject);
            }
        }
    }

    private TypeHandler getTypeHandler(Class<?> parameterTypeClass) {
        if (parameterTypeClass.isAssignableFrom(Integer.class)) {
            return new IntegerTypeHandler();
        } else {
            System.out.println("暂不支持该类型");
            return null;
        }
    }
}
