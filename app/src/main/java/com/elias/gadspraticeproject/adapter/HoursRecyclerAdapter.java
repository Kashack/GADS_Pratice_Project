package com.elias.gadspraticeproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elias.gadspraticeproject.R;
import com.elias.gadspraticeproject.model.Hours;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.List;

public class HoursRecyclerAdapter extends RecyclerView.Adapter<HoursRecyclerAdapter.HoursHolders>{

    private Context context;
    private List<Hours> leaderBoard;

    public HoursRecyclerAdapter(Context context, List<Hours> leaderBoard) {
        this.context = context;
        this.leaderBoard = leaderBoard;
    }

    @NonNull
    @Override
    public HoursRecyclerAdapter.HoursHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_list_view, parent,false);
        return new HoursRecyclerAdapter.HoursHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursRecyclerAdapter.HoursHolders holder, int position) {
        Hours leaderBoards = leaderBoard.get(position);
        holder.name.setText(leaderBoards.getName());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(leaderBoard.get(position).getBadgeUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.badge);
        holder.description.setText(MessageFormat.format("{0} learning hours, {1}.", leaderBoards.getHours(), leaderBoards.getCountry()));
        }

    @Override
    public int getItemCount() {
        return leaderBoard.size();
    }

    public class HoursHolders extends RecyclerView.ViewHolder {
        public TextView name, description;
        public ImageView badge;

        public HoursHolders(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_txt);
            description = itemView.findViewById(R.id.description_txt);
            badge = itemView.findViewById(R.id.badgeImage);

        }
    }
}
