package com.borsch.shift.cft.features.borsch.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.borsch.shift.cft.R;
import com.borsch.shift.cft.features.borsch.domain.model.Request;

import java.util.ArrayList;
import java.util.List;

public final class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestHolder> {

    private final List<Request> requests = new ArrayList<>();
    private final LayoutInflater inflater;
    private final SelectRequestListener selectRequestListener;

    public RequestAdapter(Context context, SelectRequestListener selectRequestListener) {
        inflater = LayoutInflater.from(context);
        this.selectRequestListener = selectRequestListener;
    }

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.request_item, parent, false);
        return new RequestHolder(itemView, selectRequestListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestHolder holder, int position) {
        holder.bind(requests.get(position));
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public void setRequest(ArrayList<Request> result) {

        requests.clear();
        requests.addAll(result);
        notifyDataSetChanged();
    }

    class RequestHolder extends RecyclerView.ViewHolder {

        private final TextView  requestNameView;
        private final TextView requestWeightView;
        private final Button requestDeleteView;
        private final SelectRequestListener selectRequestListener;

        RequestHolder(View view, SelectRequestListener selectRequestListener) {
            super(view);
            this.selectRequestListener = selectRequestListener;
            requestNameView = view.findViewById(R.id.request_name);
            requestWeightView = view.findViewById(R.id.request_product);
            requestDeleteView = view.findViewById(R.id.request_delete);
        }

        void bind(final Request request) {
            requestNameView.setText(request.getName());
            requestWeightView.setText(request.getProduct());


            requestDeleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectRequestListener.onRequestClickDelete(request);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectRequestListener.onRequestSelect(request);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectRequestListener.onRequestLongClick(request);
                    return true;
                }
            });

        }
    }

    public interface SelectRequestListener {

        void onRequestSelect(Request request);

        void onRequestLongClick(Request request);

        void onRequestClickDelete(Request request);
    }

}
