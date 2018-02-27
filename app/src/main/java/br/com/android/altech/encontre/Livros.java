package br.com.android.altech.encontre;



public class Livros {

    public static String mNomeLivro, mDescricao, mAutor, mImagem, mLinkVenda;

    public Livros(String nomeLivro, String descricao, String autor, String imagem, String venda){
        this.mNomeLivro = nomeLivro;
        this.mDescricao = descricao;
        this.mAutor = autor;
        this.mImagem = imagem;
        this.mLinkVenda = venda;
            }

    public String getmNomeLivro() {
        return mNomeLivro;
    }

    public String getmDescricao() {
        return mDescricao;
    }

    public String getmAutor() {
        return mAutor;
    }

    public String getmImagem() {
        return mImagem;
    }

    public String getmLinkVenda() {
        return mLinkVenda;
    }
}
