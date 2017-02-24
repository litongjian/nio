package game.nio.commons;

import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;

/**
 * 所属项目：nio
 * 创建时间：2017/2/25.
 * 路径：game.nio.commons
 * 概要：
 */
public class UserObjectDecoder extends ObjectDecoder {
    public UserObjectDecoder(){
        super(ClassResolvers.cacheDisabled(null));
    }
}
