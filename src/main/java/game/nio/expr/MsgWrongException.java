package game.nio.expr;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.expr
 * 概要：消息错误异常
 */
public final class MsgWrongException extends Exception {
    @Override
    public String getMessage() {
        return "当前发送的消息非法";
    }

}
