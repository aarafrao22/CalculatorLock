package com.aarafrao.calculatorapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aarafrao.calculatorapp.ClickListener;
import com.aarafrao.calculatorapp.Model.ProductModel;
import com.aarafrao.calculatorapp.R;

import java.util.List;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> {

    private List<ProductModel> ProductModelList;
    private ClickListener clickListener;
    private Context context;


    public GridViewAdapter(List<ProductModel> ProductModelList, ClickListener clickListener, Context context) {
        this.ProductModelList = ProductModelList;
        this.clickListener = clickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public GridViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                         int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewAdapter.ViewHolder holder, int position) {
        int resource = ProductModelList.get(position).getProductImage();
        String title = ProductModelList.get(position).getProductTitle();
        String description = ProductModelList.get(position).getProductDescription();
        String price = ProductModelList.get(position).getProductPrice();

        holder.setProductDesc(description);
        holder.setProductTitle(title);
        holder.setProductPrice(price);
        holder.setProductImage(resource);

    }

    @Override
    public int getItemCount() {
        if (ProductModelList.size() > 8) {
            return 8;
        } else {
            return ProductModelList.size();
        }
    }

    public class ViewHolder extends
            RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView productImage;
        private TextView productTitle;
        private TextView productDesc;
        private TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.hs_product_Image);
            productTitle = itemView.findViewById(R.id.hs_product_title);
            productDesc = itemView.findViewById(R.id.hs_product_description);
            productPrice = itemView.findViewById(R.id.hs_product_price);

            itemView.setOnClickListener(this);



        }

        private void setProductImage(int resource) {
            productImage.setImageResource(resource);
        }

        private void setProductTitle(String title) {
            productTitle.setText(title);
        }

        private void setProductDesc(String desc) {
            productDesc.setText(desc);
        }

        private void setProductPrice(String price) {
            productPrice.setText(price);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClicked(getAdapterPosition());
        }
    }
}