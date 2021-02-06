import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Mr.xiao
 * @create 2021-02-04 20:43
 * future的高级版本
 */
public class Homework2 {
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
        CompletableFuture<Integer> task=CompletableFuture.supplyAsync(()->{return sum();});
        try {
            int res=task.get();
            System.out.println(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
