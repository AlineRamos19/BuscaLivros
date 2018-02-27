package br.com.android.altech.encontre.recycler;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.android.altech.encontre.R;

public class LivroViewHolder extends RecyclerView.ViewHolder  {

    final TextView nomeLivro;
    final TextView descricaoLivro;
    final TextView autorLivro;
    final ImageView imagemLivro;
    final TextView linkVenda;

    public LivroViewHolder(View itemView) {
        super(itemView);

        nomeLivro = itemView.findViewById(R.id.nome_livro);
        descricaoLivro = itemView.findViewById(R.id.descricao);
        autorLivro = itemView.findViewById(R.id.autor);
        imagemLivro = itemView.findViewById(R.id.imagem);
        linkVenda = itemView.findViewById(R.id.link_venda);
    }

}
