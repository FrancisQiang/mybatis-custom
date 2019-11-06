package resource;

import java.io.InputStream;

/**
 * @author lgq
 * @date 2019/10/28
 */
public class Resource {

    public static InputStream getResourceAsStream(String resource) {
        if (resource == null || "".equals(resource)) {
            return null;
        }
        return Resource.class.getClassLoader().getResourceAsStream(resource);
    }

}
