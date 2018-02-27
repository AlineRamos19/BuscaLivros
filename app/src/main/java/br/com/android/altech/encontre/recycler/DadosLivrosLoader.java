package br.com.android.altech.encontre.recycler;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import br.com.android.altech.encontre.Livros;
import br.com.android.altech.encontre.Util;


class DadosLivrosLoader extends AsyncTaskLoader<List<Livros>> {

    private String  mGoogleUrl= "https://www.googleapis.com/books/v1/volumes?q="
            + Util.consultaDadoInformado;

    public DadosLivrosLoader(Context context) {

        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Livros> loadInBackground() {
        if(mGoogleUrl == null){
            return null;
        }

        List<Livros> inforLivros = ConsultarRequisicao.buscarDadosUrl(mGoogleUrl);
        return  inforLivros;

    }
}
