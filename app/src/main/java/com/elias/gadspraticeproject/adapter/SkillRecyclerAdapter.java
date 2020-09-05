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
import com.elias.gadspraticeproject.model.Skill;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.List;

public class SkillRecyclerAdapter extends RecyclerView.Adapter<SkillRecyclerAdapter.ViewHolders> {

    private Context context;
    private List<Skill> leaderBoard;

    public SkillRecyclerAdapter(Context context, List<Skill> leaderBoard) {
        this.context = context;
        this.leaderBoard = leaderBoard;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_list_view, parent,false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        Skill leaderBoards = leaderBoard.get(position);
        holder.name.setText(leaderBoards.getName());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(leaderBoard.get(position).getBadgeUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.badge);
        holder.description.setText(MessageFormat.format("{0} skill IQ Score, {1}.", leaderBoards.getScore(), leaderBoards.getCountry()));
    }

    @Override
    public int getItemCount() {
        return leaderBoard.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        public TextView name, description;
        public ImageView badge;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_txt);
            description = itemView.findViewById(R.id.description_txt);
            badge = itemView.findViewById(R.id.badgeImage);

        }
    }
}
