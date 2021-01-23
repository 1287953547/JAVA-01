import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author Mr.xiao
 * @create 2021-01-23 21:08
 */
public class OkHttpDemo {
    private  final  String sUrl="http://localhost:8801";
    public  void runDemo(){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(sUrl).build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.toString());
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        OkHttpDemo okHttpDemo = new OkHttpDemo();
        okHttpDemo.runDemo();
    }
}
