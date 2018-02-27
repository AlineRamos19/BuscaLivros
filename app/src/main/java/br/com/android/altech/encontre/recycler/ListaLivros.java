package br.com.android.altech.encontre.recycler;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import br.com.android.altech.encontre.Livros;
import br.com.android.altech.encontre.R;

public class ListaLivros extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Livros>> {

    private RecyclerView mRecyclerView;
    ProgressBar progressBar;
    TextView visualizacaoVazia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_livros);

        Toolbar toolbar = findViewById(R.id.tollbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.label_toolbar_lista_livros);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRecyclerView = findViewById(R.id.recycler);

        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressBar = findViewById(R.id.progress_bar);

        visualizacaoVazia = findViewById(R.id.visualicao_vazia);


        verificarConexaoDisponivel();
    }

    private void verificarConexaoDisponivel() {
        ConnectivityManager verificarRede = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo infoRede = verificarRede.getActiveNetworkInfo();

        if (infoRede != null && infoRede.isConnected()) {
            getLoaderManager().initLoader(0, null, this);

        } else {
            progressBar.setVisibility(View.GONE);
            visualizacaoVazia.setBackgroundResource(R.drawable.background_aviso);
            visualizacaoVazia.setText(R.string.aviso_sem_conexao);
        }
    }

    @Override
    public Loader<List<Livros>> onCreateLoader(int i, Bundle bundle) {
        return new DadosLivrosLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Livros>> loader, List<Livros> itemLivro) {

        progressBar.setVisibility(View.GONE);
        mRecyclerView.getRecycledViewPool().clear();

        if (itemLivro != null && !itemLivro.isEmpty()) {

            mRecyclerView.setAdapter(new LivrosAdapter(this, itemLivro));


        } else {
            visualizacaoVazia.setBackgroundResource(R.drawable.background_aviso);
            visualizacaoVazia.setText(R.string.aviso_sem_resultados);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Livros>> loader) {
        mRecyclerView.getRecycledViewPool().clear();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
