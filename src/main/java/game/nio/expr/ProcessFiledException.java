package game.nio.expr;

import com.sun.istack.internal.NotNull;

/**
 * 所属项目：nio
 * 创建时间：2017/2/24.
 * 路径：game.nio.expr
 * 概要：业务处理失败异常
 */
public final class ProcessFiledException extends Exception {
    private String msg ="";

    public ProcessFiledException(){
        super();
    }

    public ProcessFiledException(@NotNull String msg){
        super();
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return "["+ msg +"] 业务处理失败";
    }
}
