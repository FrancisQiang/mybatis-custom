package statement;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lgq
 * @date 2019/10/29
 */
@AllArgsConstructor
@Data
public class BoundSql {

    private String sql;

    private List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();

    private Object parameterObject;

}
