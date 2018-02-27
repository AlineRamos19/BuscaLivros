package br.com.android.altech.encontre.recycler;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import br.com.android.altech.encontre.Livros;

class ConsultarRequisicao {

    private static final String LOG_TAG = ConsultarRequisicao.class.getSimpleName();


    public static List<Livros> buscarDadosUrl(String mGoogleUrl) {

        URL url = criarURL(mGoogleUrl);

        String respostaJSON = "";

        try {
            respostaJSON = fazerPedidoHTTP(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Erro ao criar pedido HTTP", e);
        }

        List<Livros> livros = ProcessarDadosJSON.extrairDadosJson(respostaJSON);
        return livros;
    }

    private static URL criarURL(String mGoogleUrl) {
        URL url = null;
        try {
            url = new URL(mGoogleUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Erro ao criar URL", e);
            return null;
        }
        return url;
    }

    public static String fazerPedidoHTTP(URL url) throws IOException {

        String jsonResposta = "";

        if (url == null) {
            return jsonResposta;
        }

        HttpURLConnection conexao = null;
        InputStream inputStream = null;

        try {
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(10000);
            conexao.connect();

            if (conexao.getResponseCode() == 200) {
                inputStream = conexao.getInputStream();
                jsonResposta = lerInputStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Erro na resposta do servidor: " + conexao.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Erro ao fazer conexao", e);
        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResposta;
    }

    //conversao da respotsa JSON inputStream para String
    private static String lerInputStream(InputStream inputStream) throws IOException {

        StringBuilder saida = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                saida.append(line);
                line = bufferedReader.readLine();
            }
        }

        return saida.toString();
    }
}
