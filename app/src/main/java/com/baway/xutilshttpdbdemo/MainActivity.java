package com.baway.xutilshttpdbdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.tv)
    private TextView textView;

    @ViewInject(R.id.listview)
    private ListView listView;
    private String urlPath = "http://qhb.2dyt.com/Bwei/news";
    private List<Data.ListBean> list = new ArrayList<>();
    private myAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        ListBean bean = new ListBean();
        bean.setTitle("213");
        bean.setPic("http");

        initView();

        loadData();
    }

    private void loadData() {
        RequestParams params = new RequestParams(urlPath);
        params.addQueryStringParameter("page", "1");
        params.addQueryStringParameter("type", "5");
        params.addQueryStringParameter("postkey", "1503d");

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Data data = new Gson().fromJson(result, Data.class);
                list.addAll(data.getList());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(MainActivity.this, ex.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initView() {
        textView.setText("练习");
        adapter = new myAdapter();
        listView.setAdapter(adapter);
    }

    @Event(value = R.id.tv)
    private void tvEvent(View v) {
        Toast.makeText(MainActivity.this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
    }

    class myAdapter extends BaseAdapter {
        ImageOptions options = new ImageOptions.Builder()
                .setSize(300, 200)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .build();

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item, null);
                holder = new ViewHolder();
                x.view().inject(holder, convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv.setText(list.get(position).getTitle());

            String url = list.get(position).getPic().split("\\|")[0];

            x.image().bind(holder.image, url, options);
            return convertView;
        }
    }

    class ViewHolder {
        @ViewInject(R.id.title)
        TextView tv;
        @ViewInject(R.id.image)
        ImageView image;
    }

}
