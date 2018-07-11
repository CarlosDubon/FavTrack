package com.bonusteam.favtrack.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bonusteam.favtrack.R;
import com.bonusteam.favtrack.room.pojos.Rutina;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Esta clase representa muestra cada sugerencia dentro de la pantalla home.
 * */
public abstract class RutinaSuggestionsAdapter extends RecyclerView.Adapter<RutinaSuggestionsAdapter.HomeSuggestionsViewHolder> {
    private List<Rutina> rutinaList; // Cached copy of words
    private Context context;

/**
 * Constructor que posee el contexto del fragmento.
 * */
    public RutinaSuggestionsAdapter(Context context) {
        this.context = context;

    }
/**
 * Este metodo obtiene la lista de multimedia
 * */
    public List<Rutina> getRutinaList() {
        return rutinaList;
    }
    /**
     * Este metodo modifica la lista de multimedia
     * */
    public void setRutinaList(List<Rutina> rutinaList) {
        this.rutinaList = rutinaList;
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
        if (rutinaList != null) {

            if (rutinaList.get(position).getAvatar() != null) {
                Glide.with(context).load(rutinaList.get(position).getAvatar())
                        .apply(RequestOptions.centerCropTransform()).into(holder.suggestionImage);
            }
            if (rutinaList.get(position).getTitulo() != null) {
                holder.suggestionTitle.setText(rutinaList.get(position).getTitulo());
            }
            if(rutinaList.get(position).getIsRead()>-1){
                if(rutinaList.get(position).getIsRead()==0){
                    holder.seenbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_visibility_off_black_24dp));
                }
                if(rutinaList.get(position).getIsRead()==1){
                    holder.seenbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_visibility_black_24dp));
                }
            }

            if(rutinaList.get(position).getIsFavorite()>-1){
                if(rutinaList.get(position).getIsFavorite()==0){
                    holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
                }
                if(rutinaList.get(position).getIsFavorite()==1){
                    holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_black_24dp));
                }
            }



            holder.seenbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rutinaList.get(position).getIsRead()==0) {
                        rutinaList.get(position).setIsRead(1);
                        holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_black_24dp));


                    } else {
                        rutinaList.get(position).setIsRead(0);
                        holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
                    }

                }
            });
            holder.favbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rutinaList.get(position).getIsFavorite()==0) {
                        rutinaList.get(position).setIsFavorite(1);
                        holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_black_24dp));


                    } else {
                        rutinaList.get(position).setIsFavorite(0);
                        holder.favbutton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
                    }

                }
            });
            holder.suggestionImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Fragment fragment = new FragmentoDeInfo();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("id", rutinaList.get(position).getId());
//                    bundle.putString("name", rutinaList.get(position).getTitulo());
//                    bundle.putString("description", rutinaList.get(position).getDescripcion());
//                    bundle.putInt("favorite", rutinaList.get(position).getIsFavorite());
//                    bundle.putInt("read", rutinaList.get(position).getIsRead());
//                    bundle.putSerializable("coverimage", rutinaList.get(position).getAvatar());
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
        if (rutinaList != null)
            return rutinaList.size();
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