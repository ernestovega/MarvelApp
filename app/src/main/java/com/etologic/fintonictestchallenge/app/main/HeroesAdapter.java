package com.etologic.fintonictestchallenge.app.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.etologic.fintonictestchallenge.R;
import com.etologic.fintonictestchallenge.app.utils.PicassoCache;
import com.etologic.fintonictestchallenge.domain.model.Hero;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ernesto.vega on 16/06/2017.
 */

class HeroesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    interface ItemHeroeListener {
        void onItemHeroClick(String heroeName);
    }

    private List<Hero> heroes;
    private ItemHeroeListener itemHeroeListener;

    HeroesAdapter(List<Hero> heroes, ItemHeroeListener itemHeroeListener) {
        this.heroes = heroes;
        this.itemHeroeListener = itemHeroeListener;
    }

    class ItemHeroeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivItemHeroPhoto)
        ImageView ivItemHeroePhoto;
        @BindView(R.id.pbItemHeroPhoto)
        ProgressBar pbItemHeroPhoto;
        @BindView(R.id.tvItemHeroName)
        TextView tvItemHeroeName;
        @BindView(R.id.tvItemHeroRealName)
        TextView tvItemHeroeRealName;

        ItemHeroeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.llItemHeroeContainer)
        void onClick() {
            if(itemHeroeListener != null) {
                itemHeroeListener.onItemHeroClick(tvItemHeroeName.getText().toString());
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_heroe, parent, false);
        return new ItemHeroeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemHeroeViewHolder viewHolder = (ItemHeroeViewHolder) holder;
        viewHolder.pbItemHeroPhoto.setVisibility(View.VISIBLE);
        Hero hero = heroes.get(position);
        viewHolder.tvItemHeroeName.setText(hero.getName());
        viewHolder.tvItemHeroeRealName.setText(hero.getRealName());
        Picasso piccaso = PicassoCache.getPicassoInstance(viewHolder.tvItemHeroeName.getContext());
        piccaso.load(hero.getPhoto())
                .error(R.drawable.error_icon)
                .noPlaceholder()
                .into(viewHolder.ivItemHeroePhoto, new Callback() {
                    @Override
                    public void onSuccess() {
                        viewHolder.pbItemHeroPhoto.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError() {
                        viewHolder.pbItemHeroPhoto.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }
}
