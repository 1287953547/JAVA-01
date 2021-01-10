package homework1;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Mr.xiao
 * @create 2021-01-10 16:29
 */
public class XlassClassloader extends ClassLoader{
    private final static String fileName="F:\\Java_course1\\Homework\\JAVA-01\\Week_01\\Hello.xlass";

    public static void main(String[] args) {
        try {
            Class<?> helloClass = new XlassClassloader().findClass("Hello");
            Object object = helloClass.newInstance();
            Method hello = helloClass.getMethod("hello");
            hello.invoke(object);

        } catch (InstantiationException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes=loadXlass2Bytes(fileName);
        return defineClass(name, bytes, 0, bytes.length);
    }
    private byte[] loadXlass2Bytes(String fileName){
        File file=new File(fileName);
        InputStream in= null;
        byte[] xlassBytes=null;
        try {
            in = new FileInputStream(file);
            xlassBytes=new byte[in.available()];
            in.read(xlassBytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i=0;i<xlassBytes.length;i++){
            xlassBytes[i]=(byte)(255-xlassBytes[i]);
        }
        return xlassBytes;
    }
}
