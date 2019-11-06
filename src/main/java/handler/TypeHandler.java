package handler;

import java.sql.PreparedStatement;

/**
 * @author lgq
 * @date 2019/10/29
 */
public interface TypeHandler {

    /**
     * 设置参数
     * @param index 参数位置
     * @param preparedStatement 执行语句描述
     * @param parameterObject 参数
     */
    void setParameter(int index, PreparedStatement preparedStatement, Object parameterObject);

}
