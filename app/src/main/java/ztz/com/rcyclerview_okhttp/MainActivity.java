package ztz.com.rcyclerview_okhttp;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ztz.com.rcyclerview_okhttp.bean.NewsBean;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                if (msg.what == 0) {
                    List<NewsBean.ResultBean.DataBean> list = (List<NewsBean.ResultBean.DataBean>) msg.obj;
                    //Log.i("11111111",list.toString());
                    MyAdapter myAdapter = new MyAdapter(MainActivity.this, list);
                    recycler_view.setAdapter(myAdapter);
             }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_view = findViewById(R.id.recycler_view);
        OkHttp();
        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));
    }

    private void OkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("keep-alive","")
                .url("http://v.juhe.cn/toutiao/index?type=top&key=597b4f9dcb50e051fd725a9ec54d6653")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            //失败回调的方法
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("onFailure","请求失败");
            }

            //成功回调的方法
            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                        try {
                            Gson gson = new Gson();
                            String s = response.body().string();
                            NewsBean newsBean = gson.fromJson(s, NewsBean.class);
                            List<NewsBean.ResultBean.DataBean> list = newsBean.getResult().getData();
                            Message message = new Message();
                            message.what = 0;
                            message.obj = list;
                            handler.sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

            }
        });
    }
}
