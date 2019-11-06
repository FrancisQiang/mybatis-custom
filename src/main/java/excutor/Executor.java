package excutor;

import config.Configuration;
import statement.MappedStatement;

import java.util.List;

/**
 * @author lgq
 * @date 2019/10/28
 */
public interface Executor {

    /**
     * 执行方法
     * @param mappedStatement 封装的语句
     * @param configuration 全局配置
     * @param args 参数
     * @param <T> 泛型
     * @return 执行结果集合
     */
    <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object args);

}
