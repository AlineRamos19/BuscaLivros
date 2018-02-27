package br.com.android.altech.encontre.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.android.altech.encontre.Livros;
import br.com.android.altech.encontre.R;


public class LivrosAdapter extends RecyclerView.Adapter<LivroViewHolder> {

    private static final String LOG_TAG = LivrosAdapter.class.getSimpleName();
    private List<Livros> mlivros;
    private Context mContext;

    public LivrosAdapter(Context contex, List<Livros> livros){
        this.mContext = contex;
        this.mlivros = livros;
    }

    @Override
    public LivroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_livrro, parent, false);
        return new LivroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LivroViewHolder holder, int position) {
        Livros itemLivro = mlivros.get(position);

        holder.nomeLivro.setText(itemLivro.getmNomeLivro());
        holder.descricaoLivro.setText(itemLivro.getmDescricao());
        holder.autorLivro.setText(itemLivro.getmAutor());

        if(itemLivro.getmImagem().trim().isEmpty()){
            holder.imagemLivro.setImageResource(R.drawable.placeholder);
        }else{
            Picasso.with(mContext.getApplicationContext()).load(itemLivro.getmImagem()).
                    resize(200, 280).into(holder.imagemLivro);
        }

        holder.linkVenda.setText(itemLivro.getmLinkVenda());
    }

    @Override
    public int getItemCount() {
        return mlivros != null ? mlivros.size() : 0;
    }
}
