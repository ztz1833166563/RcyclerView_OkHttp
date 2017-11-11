package ztz.com.rcyclerview_okhttp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import ztz.com.rcyclerview_okhttp.bean.NewsBean;
import ztz.com.rcyclerview_okhttp.util.ImageLoaderUtil;

/**
 * Created by TR on 2017/11/11.
 */

class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<NewsBean.ResultBean.DataBean> list;
    public MyAdapter(Context context, List<NewsBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.text_01.setText(list.get(position).getTitle());
        holder1.text_02.setText(list.get(position).getAuthor_name());
        ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),holder1.img_01);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView text_01;
        private final TextView text_02;
        private final ImageView img_01;

        public ViewHolder(View itemView) {
            super(itemView);
            text_01 = itemView.findViewById(R.id.text_01);
            text_02 = itemView.findViewById(R.id.text_02);
            img_01 = itemView.findViewById(R.id.img_01);
        }
    }
}
