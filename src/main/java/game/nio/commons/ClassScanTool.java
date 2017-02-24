package game.nio.commons;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 所属项目：nio
 * 创建时间：2017/2/25.
 * 路径：game.nio.commons
 * 概要：类搜索工具
 */
public final class ClassScanTool {

    /**
     * @param p 属性文件
     * @param key KEY
     * @param <T> 需要返回的类型
     * @return 该类型的对象
     * @throws ClassNotFoundException 找不到该类
     * @throws IllegalAccessException 异常
     * @throws InstantiationException 异常
     */
    public static <T> T scanClassByProperties(Properties p,String key) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String className = p.getProperty(key);
        Class clz = Class.forName(className);
        T instance = (T) clz.newInstance();
        return instance;
    }

    /**
     * @param p 属性文件
     * @param key KEY
     * @return 目标目录下的所有类文件
     * @throws UnsupportedEncodingException 编码异常
     */
    public static List<Class> scanClassListByProperties(Properties p,String key) throws UnsupportedEncodingException {
        String packageName = p.getProperty(key);
        if (packageName==null)return null;
        String path = p.getProperty(key).replaceAll("\\.","/");
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        if (url==null)return null;
        File dir = new File(URLDecoder.decode(url.getFile(),"utf-8"));
        if (!dir.isDirectory())return null;
        File[] classFiles = dir.listFiles(file -> file.isFile()&&file.getName().endsWith(".class"));
        List<Class> list = new LinkedList<>();
        assert classFiles != null;
        for (File clz:classFiles){
            String className = packageName+"."+clz.getName().replaceAll("\\.class","");
            try {
                list.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                System.out.println(className+"类不存在");
            }
        }
        return list;
    }
}
