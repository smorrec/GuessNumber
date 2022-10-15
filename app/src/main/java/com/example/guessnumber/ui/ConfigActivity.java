package com.example.guessnumber.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.guessnumber.GuessNumberApplication;
import com.example.guessnumber.data.Game;
import com.example.guessnumber.databinding.ActivityConfigBinding;

/**
 * Activity inicial en la que el usuario introduce su nombre de usuario
 * y el número máximo de intentos en el que este debe adivinar el número.
 */
public class ConfigActivity extends AppCompatActivity {
    private ActivityConfigBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfigBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        GuessNumberApplication app = (GuessNumberApplication) getApplication();
        binding.setGame(new Game(app.getGame()));
        binding.btSend.setOnClickListener(view -> play());
        /**
         * Declaro un listener a cada uno de los textedit para que el botón esté
         * desabilitado si los valores introducidos en estos no son válidos
         */
        binding.User.addTextChangedListener(textWatcher());
        binding.NumIntentos.addTextChangedListener(textWatcher());
    }

    public void play(){
        Bundle bundle = new Bundle();
        bundle.putParcelable("game", binding.getGame());

        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);

    }



    /**
     * Método que devuelve un TextWatcher con su método afterTextChanged modificado para
     * que active o desactive el botón de jugar.
     * @return
     */
    private TextWatcher textWatcher(){
        return (new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(binding.getGame().getUsuario() == null || binding.getGame().getUsuario().equals("") || binding.getGame().getNumIntentos() <= 0){
                    binding.btSend.setEnabled(false);
                }else{
                    binding.btSend.setEnabled(true);
                }
            }
        });
    }
}