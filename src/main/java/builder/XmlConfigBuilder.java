package builder;

import com.alibaba.druid.pool.DruidDataSource;
import config.Configuration;
import org.dom4j.Document;
import org.dom4j.Element;
import reader.DocumentReader;
import resource.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author lgq
 * @date 2019/10/28
 */
public class XmlConfigBuilder {

    private InputStream inputStream;

    private Configuration configuration;

    public XmlConfigBuilder(InputStream inputStream) {
        this.inputStream = inputStream;
        this.configuration = new Configuration();
    }

    public Configuration parse() {
        Document document = DocumentReader.getDocument(this.inputStream);
        parseConfiguration(document.getRootElement());
        return configuration;
    }

    private void parseConfiguration(Element rootElement) {
        parseEnvironmentsElement(rootElement.element("environments"));
        parseMappersElement(rootElement.element("mappers"));
    }

    @SuppressWarnings("unchecked")
    private void parseMappersElement(Element mappers) {
        List<Element> mapperElements = mappers.elements();
        for (Element element : mapperElements) {
            String resource = element.attributeValue("resource");
            InputStream inputStream = Resource.getResourceAsStream(resource);
            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(inputStream, configuration);
            xmlMapperBuilder.parse();
        }

    }

    @SuppressWarnings("unchecked")
    private void parseEnvironmentsElement(Element element) {
        // 获取默认环境
        String defaultEnvironment = element.attributeValue("default");
        // 获取environment标签
        List<Element> environmentList = element.elements();
        for (Element environment : environmentList) {
            String environmentId = environment.attributeValue("id");
            if (defaultEnvironment.equals(environmentId)) {
                createDataSource(environment);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void createDataSource(Element element) {
        Element dataSource = element.element("dataSource");

        String dataSourceType = dataSource.attributeValue("type");
        List<Element> propertyElements = dataSource.elements();

        Properties properties = new Properties();

        for (Element property : propertyElements) {
            String name = property.attributeValue("name");
            String value = property.attributeValue("value");
            properties.setProperty(name, value);
        }

        DruidDataSource datasource = null;

        if ("Druid".equals(dataSourceType)) {
            datasource = new DruidDataSource();
            datasource.setDriverClassName(properties.getProperty("driver"));
            datasource.setUrl(properties.getProperty("url"));
            datasource.setUsername(properties.getProperty("username"));
            datasource.setPassword(properties.getProperty("password"));
        }
        configuration.setDataSource(datasource);

    }

}
