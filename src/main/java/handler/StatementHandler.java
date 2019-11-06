package handler;

import statement.BoundSql;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author lgq
 * @date 2019/10/29
 */
public interface StatementHandler {

    /**
     * 获取sql陈述对象
     * @param sql sql语句
     * @return 陈述封装对象
     */
    Statement getStatement(String sql);

    /**
     * 真正执行sql
     * @param statement 表达式
     * @return 处理结果集
     */
    ResultSet doExecute(Statement statement);

    /**
     * 设置参数
     * @param statement sql陈述独享
     * @param boundSql 封账sql对象
     */
    void setParameter(Statement statement, BoundSql boundSql);


}
