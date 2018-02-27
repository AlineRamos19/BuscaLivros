package br.com.android.altech.encontre.recycler;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.android.altech.encontre.Livros;

class ProcessarDadosJSON {

    private static final String LOG_TAG = ProcessarDadosJSON.class.getSimpleName();


    public static List<Livros> extrairDadosJson(String respostaJSON) {

        if(TextUtils.isEmpty(respostaJSON)){
            return null;
        }

        List<Livros> informacoesLivosLista = new ArrayList<>();

        try{
            JSONObject jsonResposta = new JSONObject(respostaJSON);

            JSONArray dadosLivrosArray = jsonResposta.optJSONArray("items");

            if(dadosLivrosArray == null){
                return informacoesLivosLista;
            }

            for (int i = 0; i <dadosLivrosArray.length(); i++){

                JSONObject livroAtual = dadosLivrosArray.optJSONObject(i);

                JSONObject volumeInfo = livroAtual.optJSONObject("volumeInfo");

                String tituloLivro = "";
                if (volumeInfo.has("title")){
                    tituloLivro = volumeInfo.optString("title");
                }

                String descricaoLivro = "";
                if(volumeInfo.has("description")){
                    descricaoLivro = volumeInfo.optString("description");
                }

                JSONArray listaAutor = volumeInfo.getJSONArray("authors");
                String autorLivro = null;
                if (listaAutor != null && listaAutor.length() > 0){
                    for (int j = 0; j <listaAutor.length(); j++){
                        autorLivro = listaAutor.optString(j);
                    }
                }

                JSONObject imagemUrl;
                String urlImagemLivro = " ";
                if (volumeInfo.has("imageLinks")) {
                    imagemUrl = volumeInfo.optJSONObject("imageLinks");
                    if (imagemUrl.has("thumbnail")) {
                        urlImagemLivro = imagemUrl.optString("thumbnail");
                    }
                }

                String linkVenda = "";
                if(volumeInfo.has("infoLink")){
                    linkVenda = volumeInfo.optString("infoLink");
                }

                Livros novoLivro = new Livros(tituloLivro, descricaoLivro,
                        autorLivro, urlImagemLivro, linkVenda);
                informacoesLivosLista.add(novoLivro);
            }
        }catch (JSONException e){
            Log.e(LOG_TAG, "Erro ao analisar os resultados JSON", e);
        }
        return  informacoesLivosLista;
    }
}
