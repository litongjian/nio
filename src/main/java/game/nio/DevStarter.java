package game.nio;

import game.nio.client.ClientBoot;

/**
 * 所属项目：nio
 * 创建时间：2017/2/25.
 * 路径：game.nio
 * 概要：
 */
public final class DevStarter {
    public static void main(String[] args) throws InterruptedException {
        ClientBoot cb = new ClientBoot(9999,"localhost");
        cb.connect();
    }
}
