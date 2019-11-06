package handler;

/**
 * @author lgq
 * @date 2019/10/29
 */
public interface TokenHandler {

    /**
     * 处理
     * @param content 处理内容
     * @return 处理结果
     */
    String handleToken(String content);

}
