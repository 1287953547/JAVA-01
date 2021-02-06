import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Mr.xiao
 * @create 2021-02-04 17:52
 * 利用callable返回值
 */
public class Homework {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        FutureTask<Integer> task=new FutureTask<Integer>(new Callable<Integer>() {
            public Integer call() throws Exception {
                return sum();
            }
        });
        // 异步执行 下面方法
        new Thread(task).start();
        System.out.println("已经异步执行中，等待结果...");
        int result;
        try {
            result=task.get();
            System.out.println("异步计算结果为："+result);
            System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }

        // 确保  拿到result 并输出


        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
