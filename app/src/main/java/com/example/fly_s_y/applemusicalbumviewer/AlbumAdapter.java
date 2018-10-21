package com.example.fly_s_y.applemusicalbumviewer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>{
    private AlbumList albumList;

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        public TextView albumName,
                        artistName;

        public AlbumViewHolder(View itemView) {
            super(itemView);

            albumName = itemView.findViewById(R.id.albumName);
            artistName = itemView.findViewById(R.id.artistName);
        }
    }

    public AlbumAdapter(AlbumList albumList){
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_list_item, parent, false);

        return new AlbumViewHolder(itemView);
    }

    public String getLastUpdated(){
        return albumList.getLastUpdated();
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        if(albumList != null && albumList.getAlbumList() != null){
            Album album = albumList.getAlbumList().get(position);

            holder.albumName.setText(album.getAlbumName());
            holder.artistName.setText("by " + album.getArtistName());
        }
    }

    @Override
    public int getItemCount() {
        return (albumList == null || albumList.getAlbumList() == null) ? 0 : albumList.getAlbumList().size();
    }
}
