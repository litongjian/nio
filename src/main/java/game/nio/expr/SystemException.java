package game.nio.expr;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.expr
 * 概要：系统处理异常
 */
public final class SystemException extends Exception {
    @Override
    public String getMessage() {
        return "系统级别异常！异常信息："+super.getMessage();
    }
}
