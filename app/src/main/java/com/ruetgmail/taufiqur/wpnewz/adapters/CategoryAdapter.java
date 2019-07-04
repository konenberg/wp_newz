package com.ruetgmail.taufiqur.wpnewz.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruetgmail.taufiqur.wpnewz.R;
import com.ruetgmail.taufiqur.wpnewz.listeners.ListItemClickListener;
import com.ruetgmail.taufiqur.wpnewz.models.category.Category;

import java.util.ArrayList;
import java.util.Random;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context mContext;

    private ArrayList<Category> mCategoryList;
    private ListItemClickListener mItemClickListener;

    public CategoryAdapter(Context mContext, ArrayList<Category> mCategoryList) {
        this.mContext = mContext;
        this.mCategoryList = mCategoryList;
    }

    public void setItemClickListener(ListItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_list, parent, false);
        return new ViewHolder(view, viewType, mItemClickListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvHashTag, tvCategoryTitle;
        private RelativeLayout lytContainer;
        private ListItemClickListener itemClickListener;


        public ViewHolder(View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener = itemClickListener;
            // Find all views ids
            tvHashTag = (TextView) itemView.findViewById(R.id.hash_tag);
            tvCategoryTitle = (TextView) itemView.findViewById(R.id.category_name);
            lytContainer = (RelativeLayout) itemView.findViewById(R.id.lyt_container);

            lytContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(getLayoutPosition(), view);
            }
        }
    }

    @Override
    public int getItemCount() {
        return (null != mCategoryList ? mCategoryList.size() : 0);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder mainHolder, int position) {
        final Category model = mCategoryList.get(position);

        // setting data over views
        mainHolder.tvCategoryTitle.setText(Html.fromHtml(model.getName()));

        Random rand = new Random();
        int i = rand.nextInt(4) + 1;


        switch (i) {
            case 1:
                mainHolder.tvHashTag.setTextColor(ContextCompat.getColor(mContext, R.color.red));
                break;
            case 2:
                mainHolder.tvHashTag.setTextColor(ContextCompat.getColor(mContext, R.color.orange));
                break;
            case 3:
                mainHolder.tvHashTag.setTextColor(ContextCompat.getColor(mContext, R.color.green));
                break;
            case 4:
                mainHolder.tvHashTag.setTextColor(ContextCompat.getColor(mContext, R.color.pink));
                break;
            default:
                break;
        }
    }
}