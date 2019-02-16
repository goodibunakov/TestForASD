package ru.goodibunakov.testforasd.adapters;

import android.annotation.SuppressLint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.goodibunakov.testforasd.OnClickListener;
import ru.goodibunakov.testforasd.R;
import ru.goodibunakov.testforasd.model.DbItem;
import ru.goodibunakov.testforasd.utils.DateUtils;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    protected OnClickListener onClickListener;
    List<DbItem> list = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DbItem dbItem = list.get(position);
        holder.title.setText(dbItem.getTitle());
        Glide.with(holder.photo.getContext())
                .load(dbItem.getImageLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .dontAnimate()
                .thumbnail(0.1f)
                .into(holder.photo);
        holder.content.setText(Html.fromHtml(dbItem.getDescription()));
        holder.category.setText("Категория: " + dbItem.getCategory());
        holder.date.setText(DateUtils.convertDateToFrandlyFormat(dbItem.getPubDate()));

        holder.itemView.setOnClickListener(v -> {
            int adapterPos = holder.getAdapterPosition();
            if (adapterPos != RecyclerView.NO_POSITION) {
                if (onClickListener != null) {
                    onClickListener.onItemClick(adapterPos, holder.itemView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.photo)
        ImageView photo;
        @BindView(R.id.category)
        TextView category;
        @BindView(R.id.date)
        TextView date;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public RecyclerAdapter() {
    }

    public void setItems(List<DbItem> items) {
        list = items;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public DbItem getItem(int position) {
        return list.get(position);
    }
}
