package com.example.guessnumber.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.guessnumber.R;
import com.example.guessnumber.data.Game;
import com.example.guessnumber.databinding.ActivityEndPlayBinding;

/**
 * Actividad en la que se muestra el resultado del juego, si el usuario
 * ha acertado en número antes de agotar sus intentos o si los ha consumido todos
 * antes de acertarlo.
 */
public class EndPlayActivity extends AppCompatActivity {
    private ActivityEndPlayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        Game game = bundle.getParcelable("game");

        binding.setGame(game);

        finalView(binding.getGame());
    }

    private void finalView(Game game){
        if(game.getNumIntentos() > 0){
            successView();
        }else{
            failView();
        }
    }

    private void successView(){
        binding.textResult.setText(R.string.success);
    }

    private void failView(){
        binding.textResult.setText(R.string.fail);
    }
}