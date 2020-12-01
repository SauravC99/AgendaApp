package com.blackboxstudios.agendaplus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import static android.view.View.*;
import java.util.ArrayList;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mEvents = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> events, Context context) {
        mEvents = events;
        mContext = context;
    }
    
    // Inflates the layout for the RecyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.eventItem.setText(mEvents.get(position).toString());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mEvents.get(position).toString());
                Toast.makeText(mContext, mEvents.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Returns the number of items in the events list.
    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    // Creates the ViewHolder class.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView eventItem;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventItem = itemView.findViewById(R.id.eventItem);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}