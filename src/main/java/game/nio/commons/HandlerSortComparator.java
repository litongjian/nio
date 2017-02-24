package game.nio.commons;

import game.nio.annotation.UserHandler;

import java.util.Comparator;

/**
 * 所属项目：nio
 * 创建时间：2017/2/25.
 * 路径：game.nio.commons
 * 概要：Handler比较器
 */
public final class HandlerSortComparator implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        UserHandler uh1 = o1.getClass().getAnnotation(UserHandler.class);
        UserHandler uh2 = o2.getClass().getAnnotation(UserHandler.class);
        if (uh1==null||uh2==null)return 0;
        return uh1.index() - uh2.index();
    }
}
