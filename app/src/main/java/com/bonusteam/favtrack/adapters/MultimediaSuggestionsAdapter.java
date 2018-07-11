package com.bonusteam.favtrack.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bonusteam.favtrack.R;
import com.bonusteam.favtrack.room.pojos.Multimedia;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.util.List;

/**
 * Esta clase representa muestra cada sugerencia dentro de la pantalla home.
 * */
public class MultimediaSuggestionsAdapter extends RecyclerView.Adapter<MultimediaSuggestionsAdapter.HomeSuggestionsViewHolder> {
    private List<Multimedia> multimediaList; // Cached copy of words
    private Context context;

/**
 * Constructor que posee el contexto del fragmento.
 * */
    public MultimediaSuggestionsAdapter(Context context) {
        this.context = context;

    }
/**
 * Este metodo obtiene la lista de multimedia
 * */
    public List<Multimedia> getmultimediaList() {
        return multimediaList;
    }
    /**
     * Este metodo modifica la lista de multimedia
     * */
    public void setMultimediaList(List<Multimedia> multimediaList) {
        Log.d("Lista",multimediaList.size()+"");
        this.multimediaList = multimediaList;
    }
    /**
     * Este metodo crea una vista y coloca un cardview en ella
     * */
    @Override
    public HomeSuggestionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);

        return new HomeSuggestionsViewHolder(itemView);
    }
    /**
     * Este metodo define el contenido del cardview por cada vista que esta contiene
     * */
    @Override
    public void onBindViewHolder(final HomeSuggestionsViewHolder holder, final int position) {
        if (multimediaList != null) {

            if (multimediaList.get(position).getAvatar() != null) {
                /*Glide.with(context).load(multimediaList.get(position).getAvatar())
                        .apply(RequestOptions.centerCropTransform()).into(holder.suggestionImage);*/
            }
            if (multimediaList.get(position).getName() != null) {
                holder.suggestionTitle.setText(multimediaList.get(position).getName());
            }
            if(multimediaList.get(position).getIsRead()>-1){
                if(multimediaList.get(position).getIsRead()==0){
                    holder.seenbutton.setImageDrawable(context.getApplicationContext().getResources().getDrawable(R.drawable.ic_visibility_off_black_24dp));
                }
                if(multimediaList.get(position).getIsRead()==1){
                    holder.seenbutton.setImageDrawable(context.getApplicationContext().getResources().getDrawable(R.drawable.ic_visibility_black_24dp));
                }
            }

            if(multimediaList.get(position).getIsFavorite()>-1){
                if(multimediaList.get(position).getIsFavorite()==0){
                    holder.favbutton.setImageDrawable(context.getApplicationContext().getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
                }
                if(multimediaList.get(position).getIsFavorite()==1){
                    holder.favbutton.setImageDrawable(context.getApplicationContext().getResources().getDrawable(R.drawable.ic_star_black_24dp));
                }
            }



            holder.seenbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (multimediaList.get(position).getIsRead()==0) {
                        multimediaList.get(position).setIsRead(1);
                        holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_black_24dp));


                    } else {
                        multimediaList.get(position).setIsRead(0);
                        holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
                    }
                }
            });
            holder.favbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (multimediaList.get(position).getIsFavorite()==0) {
                        multimediaList.get(position).setIsFavorite(1);
                        holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_black_24dp));


                    } else {
                        multimediaList.get(position).setIsFavorite(0);
                        holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
                    }

                }
            });
            holder.suggestionImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(multimediaList.get(position).getId_multimedia_type().equals("pelicula"));
//                    Fragment fragment = new FragmentoDeInfo();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("id", multimediaList.get(position).getId());
//                    bundle.putString("name", multimediaList.get(position).getName());
//                    bundle.putString("description", multimediaList.get(position).getDescription());
//                    bundle.putString("genero", multimediaList.get(position).getIdGenero());
//                    bundle.putString("anio", multimediaList.get(position).getAnio());
//                    bundle.putString("multimediatype", multimediaList.get(position).getId_multimedia_type());
//                    bundle.putInt("favorite", multimediaList.get(position).getIsFavorite());
//                    bundle.putInt("read", multimediaList.get(position).getIsRead());
//                    bundle.putSerializable("coverimage", multimediaList.get(position).getAvatar());
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
        if (multimediaList != null)
            return multimediaList.size();
        else return 0;
    }

    public static  class HomeSuggestionsViewHolder extends RecyclerView.ViewHolder {
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