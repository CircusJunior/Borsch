package com.borsch.shift.cft.features.borsch.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.borsch.shift.cft.R;
import com.borsch.shift.cft.features.borsch.domain.model.Recipe;
import com.borsch.shift.cft.features.borsch.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public final class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ProductHolder> {

    private final List<Product> products = new ArrayList<>();
    private final LayoutInflater inflater;
    private final SelectProductListener selectProductListener;

    public RecipeAdapter(Context context, SelectProductListener selectProductListener) {
        inflater = LayoutInflater.from(context);
        this.selectProductListener = selectProductListener;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.product_item, parent, false);
        return new ProductHolder(itemView, selectProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setRecipe(ArrayList<Product> result) {
        products.clear();
        products.addAll(result);
        notifyDataSetChanged();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private final TextView  productNameView;
        private final TextView productWeightView;
        private final SelectProductListener selectProductListener;

        ProductHolder(View view, SelectProductListener selectBookListener) {
            super(view);
            this.selectProductListener = selectBookListener;
            productNameView = view.findViewById(R.id.product_item_name);
            productWeightView = view.findViewById(R.id.product_item_weight);
        }

        void bind(final Product product) {
            productNameView.setText(product.getFoodId());
            productWeightView.setText(String.valueOf(product.getAllWeight()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectProductListener.onProductSelect(product);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectProductListener.onProductLongClick(product);
                    return true;
                }
            });

        }
    }

    public interface SelectProductListener {

        void onProductSelect(Product product);

        void onProductLongClick(Product product);

        void onProductClickDelete(Product product);

    }

}
