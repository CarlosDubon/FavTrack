package com.bonusteam.favtrack.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bonusteam.favtrack.R;
import com.bonusteam.favtrack.room.pojos.LibrosEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Esta clase representa muestra cada sugerencia dentro de la pantalla home.
 * */
public abstract class LibrosSuggestionsAdapter extends RecyclerView.Adapter<LibrosSuggestionsAdapter.HomeSuggestionsViewHolder> {
    private List<LibrosEntity> librosEntityList; // Cached copy of words
    private Context context;

/**
 * Constructor que posee el contexto del fragmento.
 * */
    public LibrosSuggestionsAdapter(Context context) {
        this.context = context;

    }
/**
 * Este metodo obtiene la lista de multimedia
 * */
    public List<LibrosEntity> getLibrosEntityList() {
        return librosEntityList;
    }
    /**
     * Este metodo modifica la lista de multimedia
     * */
    public void setLibrosEntityList(List<LibrosEntity> librosEntityList) {
        this.librosEntityList = librosEntityList;
    }
    /**
     * Este metodo crea una vista y coloca un cardview en ella
     * */
    @Override
    public HomeSuggestionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cardview, parent, false);

        return new HomeSuggestionsViewHolder(itemView);
    }
    /**
     * Este metodo define el contenido del cardview por cada vista que esta contiene
     * */
    @Override
    public void onBindViewHolder(final HomeSuggestionsViewHolder holder, final int position) {
        if (librosEntityList != null) {

            if (librosEntityList.get(position).getAvatar() != null) {
                Glide.with(context).load(librosEntityList.get(position).getAvatar())
                        .apply(RequestOptions.centerCropTransform()).into(holder.suggestionImage);
            }
            if (librosEntityList.get(position).getNombre() != null) {
                holder.suggestionTitle.setText(librosEntityList.get(position).getNombre());
            }
            if(librosEntityList.get(position).getVisto()>-1){
                if(librosEntityList.get(position).getVisto()==0){
                    holder.seenbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_visibility_off_black_24dp));
                }
                if(librosEntityList.get(position).getVisto()==1){
                    holder.seenbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_visibility_black_24dp));
                }
            }

            if(librosEntityList.get(position).getFavorite()>-1){
                if(librosEntityList.get(position).getFavorite()==0){
                    holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
                }
                if(librosEntityList.get(position).getFavorite()==1){
                    holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_black_24dp));
                }
            }



            holder.seenbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (librosEntityList.get(position).getVisto()==0) {
                        librosEntityList.get(position).setVisto(1);
                        holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_black_24dp));


                    } else {
                        librosEntityList.get(position).setVisto(0);
                        holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
                    }

                }
            });
            holder.favbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (librosEntityList.get(position).getFavorite()==0) {
                        librosEntityList.get(position).setFavorite(1);
                        holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_black_24dp));


                    } else {
                        librosEntityList.get(position).setFavorite(0);
                        holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
                    }

                }
            });
            holder.suggestionImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Fragment fragment = new FragmentoDeInfo();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", librosEntityList.get(position).getIdLibro());
                    bundle.putString("name", librosEntityList.get(position).getNombre());
                    bundle.putString("description", librosEntityList.get(position).getDescripcion());
                    bundle.putInt("favorite", librosEntityList.get(position).getFavorite());
                    bundle.putInt("read", librosEntityList.get(position).getVisto());
                    bundle.putSerializable("coverimage", librosEntityList.get(position).getAvatar());
//                    fragment.setArguments(bundle);
//                    FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.fragment_home, fragment).commit();
//                    transaction.addToBackStack(null);

                }
            });
//            }
        }

    }


    /**
     * Este metodo obtiene el tamaño de la lista
     * */
    @Override
    public int getItemCount() {
        if (librosEntityList != null)
            return librosEntityList.size();
        else return 0;
    }

    class HomeSuggestionsViewHolder extends RecyclerView.ViewHolder {
        ImageView suggestionImage;
        TextView suggestionTitle;
        ImageView seenbutton,favbutton;
        RelativeLayout cardviewcontainer;

        private HomeSuggestionsViewHolder(View itemView) {
            super(itemView);
            suggestionImage = itemView.findViewById(R.id.image_item);
            suggestionTitle = itemView.findViewById(R.id.title_item);
            seenbutton=itemView.findViewById(R.id.seen_imageview);
                    favbutton=itemView.findViewById(R.id.fav_imageView);

        }
    }





}