import java.util.concurrent.*;

/**
 * @author Mr.xiao
 * @create 2021-02-04 18:30
 */
public class Homewor1 {
    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        ExecutorService exector=Executors.newSingleThreadExecutor();
        FutureTask<Integer> task=new FutureTask<Integer>(new Callable<Integer>() {
            public Integer call() throws Exception {
                return sum();
            }
        });
        // 异步执行 下面方法
        exector.submit(task);
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
        exector.shutdown();


    }
}
