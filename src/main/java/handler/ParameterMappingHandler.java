package handler;

import config.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import statement.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lgq
 * @date 2019/10/29
 */
@Data
public class ParameterMappingHandler implements TokenHandler {

    private List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();

    private Class<?> parameterTypeClass;

    public ParameterMappingHandler(Class<?> parameterTypeClass) {
        this.parameterTypeClass = parameterTypeClass;
    }

    public String handleToken(String content) {
        parameterMappings.add(buildParameterMapping(content));
        return "?";
    }

    private ParameterMapping buildParameterMapping(String content) {
        return new ParameterMapping(content, parameterTypeClass);
    }
}
