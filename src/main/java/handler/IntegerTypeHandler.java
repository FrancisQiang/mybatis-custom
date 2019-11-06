package handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author lgq
 * @date 2019/10/29
 */
public class IntegerTypeHandler implements TypeHandler {

    public void setParameter(int index, PreparedStatement preparedStatement, Object parameterObject) {
        try {
            preparedStatement.setInt(index, (Integer) parameterObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
