package statement;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lgq
 * @date 2019/10/29
 */
@Data
@AllArgsConstructor
public class ParameterMapping {

    private String propertyName;

    private Class<?> parameterTypeClass;

}
