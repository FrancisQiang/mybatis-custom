package excutor;

import builder.SqlSource;
import config.Configuration;
import handler.PreparedStatementHandler;
import handler.StatementHandler;
import sqlsession.StatementType;
import statement.BoundSql;
import statement.MappedStatement;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lgq
 * @date 2019/10/28
 */
public class SimpleExecutor implements Executor  {

    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object args) {
        List<T> list = new ArrayList<T>();
        SqlSource sqlSource = mappedStatement.getSqlSource();
        BoundSql boundSql = sqlSource.getBoundSql(mappedStatement, args);

        StatementType statementType = mappedStatement.getStatementType();
        StatementHandler statementHandler = null;
        if (statementType == StatementType.PREPARED) {
            statementHandler = new PreparedStatementHandler(configuration, args);
            PreparedStatement preparedStatement =
                    (PreparedStatement) statementHandler.getStatement(boundSql.getSql());
            statementHandler.setParameter(preparedStatement, boundSql);
            ResultSet resultSet = statementHandler.doExecute(preparedStatement);
            list = handleResult(resultSet, mappedStatement);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> handleResult(ResultSet resultSet, MappedStatement mappedStatement) {
        List<T> results = new ArrayList<T>();
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
        try {
            while (resultSet.next()) {
                Object resultObject = resultTypeClass.newInstance();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    Field field = resultTypeClass.getDeclaredField(resultSetMetaData.getColumnLabel(i));
                    field.setAccessible(true);
                    field.set(resultObject, resultSet.getObject(i));
                }
                results.add((T)resultObject);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return results;
    }
}
