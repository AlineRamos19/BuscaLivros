package br.com.android.altech.encontre;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import br.com.android.altech.encontre.recycler.ListaLivros;

public class MainActivity extends AppCompatActivity {

    EditText editUsuarioLivro;
    String consultaLivroUsuario;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);

        Button button = findViewById(R.id.button_buscar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                linearLayout = findViewById(R.id.linear_layout_main);
                editUsuarioLivro = findViewById(R.id.edit_livro);
                consultaLivroUsuario = editUsuarioLivro.getText().toString();


                if (consultaLivroUsuario == null || consultaLivroUsuario.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(linearLayout, R.string.eidt_inserir_infor,
                            Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    String inforUsuario = Util.removeAcento(consultaLivroUsuario);
                    Util.encode(inforUsuario);

                    Intent intent = new Intent(MainActivity.this, ListaLivros.class);
                    startActivity(intent);
                }

            }
        });
    }
}
