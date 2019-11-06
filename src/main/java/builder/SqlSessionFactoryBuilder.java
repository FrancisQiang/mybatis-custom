package builder;

import java.io.InputStream;

/**
 * @author lgq
 * @date 2019/10/28
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) {
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(inputStream);
        return new DefaultSqlSessionFactory(xmlConfigBuilder.parse());
    }
}
