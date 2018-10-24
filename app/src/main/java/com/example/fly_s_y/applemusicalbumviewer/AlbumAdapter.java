package com.example.fly_s_y.applemusicalbumviewer;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fly_s_y.applemusicalbumviewer.databinding.AlbumListItemBinding;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>{
    private AlbumList albumList;

    public class AlbumViewHolder extends RecyclerView.ViewHolder{
        private final AlbumListItemBinding binding;

        public ImageView albumArt;
        public TextView albumName, artistName;

        public AlbumViewHolder(AlbumListItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void setDetailsLauncherClickListener(View.OnClickListener listener){
            itemView.setOnClickListener(listener);
        }

        public void setBinding(Album album){
            binding.setAlbum(album);
        }
    }

    public class AlbumDetailsActivtyLauncher implements View.OnClickListener{
        private AlbumViewHolder itemView;

        public AlbumDetailsActivtyLauncher(AlbumViewHolder itemView){
            this.itemView = itemView;
        }

        @Override
        public void onClick(View view) {
            Log.d("INDEXXXXXXXXXXXXX", "" + itemView.getAdapterPosition());
        }
    }

    public AlbumAdapter(AlbumList albumList){
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlbumListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.album_list_item, parent, false);

        return new AlbumViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        if(albumList != null && albumList.getAlbumList() != null){
            Album album = albumList.getAlbumList().get(position);

            holder.setDetailsLauncherClickListener(new AlbumDetailsActivtyLauncher(holder));
            holder.setBinding(album);
        }
    }

    @Override
    public int getItemCount() {
        return (albumList == null || albumList.getAlbumList() == null) ? 0 : albumList.getAlbumList().size();
    }
}