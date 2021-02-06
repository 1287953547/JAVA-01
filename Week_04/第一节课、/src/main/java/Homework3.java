import com.sun.jmx.snmp.tasks.Task;
import com.sun.org.apache.regexp.internal.REUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.xiao
 * @create 2021-02-06 14:54
 */
public class Homework3 {
    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
    static  class Task implements Runnable{
        public  Lock lock=new ReentrantLock();
        Condition ready=lock.newCondition();
        private  int result;

        @Override
        public void run() {
            int temp=sum();
            try {
                lock.lockInterruptibly();
                result=temp;
                ready.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
        public int getResult() throws  InterruptedException{
            try {
                lock.lockInterruptibly();
                if(result==0){
                    ready.await();}
                return result;

            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException{
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        Task task=new Task();
        Thread t1=new Thread(task);

        // 异步执行 下面方法
        t1.start();
        System.out.println("已经异步执行中，等待结果...");
        int result=task.getResult();
        System.out.println(result);
        // 然后退出main线程
    }
}
