package br.com.android.altech.encontre;


import android.support.v7.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Normalizer;


public class Util extends AppCompatActivity {

    public static String consultaDadoInformado = null;

    public static String removeAcento(String str) {
        str = java.text.Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }

    public static String encode(String valorDigitado) {
        try {
            return consultaDadoInformado = URLEncoder.encode(valorDigitado, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
