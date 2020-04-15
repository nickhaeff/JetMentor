package com.example.jetmentor.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetmentor.R;
import com.example.jetmentor.connectInfo;

import java.util.List;

public class ConnectionsRVAdapter extends RecyclerView.Adapter<ConnectionsRVAdapter.ConnectionsRVVH> {

    Context connectionsContext;
    private List<connectInfo> connectionsList;
    private OnItemClickListener connectionClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public ConnectionsRVAdapter(Context ct, List<connectInfo> inConnectionsList)
    {
        connectionsContext = ct;
        connectionsList = inConnectionsList;
    }

    public void setOnItemClickListener(OnItemClickListener listener){ connectionClickListener = listener;}

    @NonNull
    @Override
    public ConnectionsRVAdapter.ConnectionsRVVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(connectionsContext);
        View view = inflater.inflate(R.layout.connection_row, parent, false);
        return new ConnectionsRVVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConnectionsRVVH holder, int position) {
        holder.user.setText(connectionsList.get(position).getMenteeId());
    }

    @Override
    public int getItemCount() {
        return connectionsList.size();
    }

    public class ConnectionsRVVH extends RecyclerView.ViewHolder {

        TextView user;

        public ConnectionsRVVH(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.connection_user);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(connectionClickListener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            connectionClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
