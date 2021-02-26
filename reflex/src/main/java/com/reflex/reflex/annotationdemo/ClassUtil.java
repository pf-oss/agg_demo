package com.reflex.reflex.annotationdemo;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Description:  搜索报下的类
 * @author: pengfei_yao
 * @create: 2020/5/9 10:07
 */
public class ClassUtil {


    /**
     * @Description:
     * @param c:
     * @return: java.util.List<java.lang.Class>
     */
    public static List<Class> getAllClassByInterface(Class c){
        List<Class> returnClassList = null;
        if (c.isInterface()){
            // 获取包名
            String packageName = c.getPackage().getName();

            // 获取当前包下以及包下所有的类
            List<Class<?>> allClass = getClasses(packageName);
            if (allClass != null){
                returnClassList = new ArrayList<Class>();
                for (Class classes : allClass){
                    // 判断是否是同一个接口
                    if (c.isAssignableFrom(classes)){
                        // 本身不加进去
                        if (!c.equals(classes)){
                            returnClassList.add(classes);
                        }
                    }
                }
            }
        }
        return returnClassList;
    }


    /**
     * @Description:
     * @param classLocation:
     * @param packageName:
     * @return: java.lang.String[]
     */
    public static String[] getPackageAllClassName(String classLocation, String packageName){
        // 待packageName分解
        String[] packagePathSplit = packageName.split("[.]");
        String realClassLocation = classLocation;
        int packageLength = packagePathSplit.length;
        for (int i = 0; i < packageLength; i++){
            realClassLocation = realClassLocation + File.separator + packagePathSplit[i];
        }
        File packageDir = new File(realClassLocation);
        if (packageDir.isDirectory()){
            String[] allClassName = packageDir.list();
            return allClassName;
        }
        return null;
    }

    /**
     * @Description:
     * @param packageName:
     * @return: java.util.List<java.lang.Class<?>>
     */
    public static List<Class<?>> getClasses(String packageName){

        // 第一个class类的集合
        List<Class<?>> classes = new ArrayList<Class<?>>();

        // 是否迭代循环
        boolean recursive = true;

        // 获取包的名字并进行替换
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合，并进行循环处理这个目录下的things
        Enumeration<URL> dirs;

        try{
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 获取循环迭代下去
            URL url = dirs.nextElement();
            // 得到协议的名称
            String protocol = url.getProtocol();
            // 如果以文件的型是保存在服务器上
            if ("file".equals(protocol)){
                // 获取包路径
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                // 以文件的方式扫描整个包下的文件，并添加到集合中
                findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
            }else if ("jar".equals(protocol)){
                // 如果是jar包文件
                // 定义一个JarFile
                JarFile jar;
                try {
                    // 获取jar
                    jar = ((JarURLConnection) url.openConnection()).getJarFile();
                    // 从此jar包得到一个枚举类
                    Enumeration<JarEntry> entries = jar.entries();
                    // 同样的进行循环迭代
                    while (entries.hasMoreElements()){
                        // 获取jar里的一个实体，可以是目录和一些jar包里的其他文件和META-INF
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        // 如果是以/ 开头的
                        if (name.charAt(0) == '/'){
                            // 获取后面的字符串
                            name = name.substring(1);
                        }
                        // 如果前半部分定义的包名相同
                        if (name.startsWith(packageDirName)){
                            int idx = name.lastIndexOf("/");
                            // 如果以"/" 结尾是一个包
                            if (idx != -1){
                                // 获取包名 把"/" 替换成"."
                                packageName = name.substring(0, idx).replace('/', '.');
                            }
                            // 如果可以迭代下去，并且是一个包
                            if ((idx != -1) || recursive){
                                // 如果是一个.class文件，而且不是目录
                                if (name.endsWith(".class") && !entry.isDirectory()){
                                    // 去掉后面的".class" 获取真正的类名
                                    String className = name.substring(packageDirName.length() + 1, name.length() - 6);
                                    try {
                                        // 添加到Classess
                                        classes.add(Class.forName(packageName + "." + className));
                                    }catch (ClassNotFoundException e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return classes;
    }


    /**
     * @Description:
     * @param packageName:
     * @param filePath:
     * @param recursive:
     * @param classes:
     * @return: void
     */
    public static void findAndAddClassesInPackageByFile(String packageName, String filePath, boolean recursive, List<Class<?>> classes) {

        // 获取此包的一个目录，建立一个file
        File dir = new File(packageName);
        // 如果不存在或者也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()){
            return;
        }
        // 如果存在，就获取包下的所有文件，包括目录
        File[] dirFiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则， 如果可以循环（包含子目录）或者是以.class结尾的文件（编译好的Java类文件）
            @Override
            public boolean accept(File pathname) {
                return (recursive && pathname.isDirectory()) || (pathname.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirFiles){
            // 如果是目录则继续扫描
            if (file.isDirectory()){
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            }else {
                // 如果是Java类文件去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try{
                    // 添加到集合中去
                    classes.add(Class.forName(packageName + "." + className));
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        }


    }


}
