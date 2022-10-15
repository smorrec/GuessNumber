package com.example.guessnumber.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.guessnumber.R;
import com.example.guessnumber.data.Game;
import com.example.guessnumber.databinding.ActivityPlayBinding;

/**
 * Activity que permite al usuario introducir una respuesta, comprobar si
 * es correcta y si falla le indica si el número a adivinar es mayor o menor.
 * También se le permite al usuario volver a intentarlo siempre que le queden intentos.
 */
public class PlayActivity extends AppCompatActivity {
    private ActivityPlayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        Game game = bundle.getParcelable("game");

        binding.btTryAgain.setEnabled(false);

        binding.Try.addTextChangedListener(textWatcher());
        binding.btCheck.setOnClickListener(view -> check(binding.getGame()));
        binding.btTryAgain.setOnClickListener(view -> tryAgain(binding.getGame()));

        binding.setGame(game);
    }

    private void check(Game game){
        if(game.getRespuesta() == game.getNumAdivinar() || game.getNumIntentos() ==0){
            endGame();
        }else if(game.getRespuesta() < game.getNumAdivinar()){
            binding.textResult.setText(R.string.mayor);
        }else{
            binding.textResult.setText(R.string.menor);
        }
        binding.Try.setEnabled(false);
        binding.btCheck.setEnabled(false);
        binding.btTryAgain.setEnabled(true);
    }

    private void tryAgain(Game game){
        game.setNumIntentos(game.getNumIntentos()-1);
        binding.textResult.setText(null);
        binding.Try.setText(null);
        binding.Try.setEnabled(true);
        binding.btCheck.setEnabled(true);
        binding.btTryAgain.setEnabled(false);
    }

    private void endGame(){
        Bundle bundle = new Bundle();
        bundle.putParcelable("game", binding.getGame());

        Intent intent = new Intent(this, EndPlayActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

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
                if(binding.getGame().getRespuesta() <=0 || binding.getGame().getRespuesta() > 100){
                    binding.btCheck.setEnabled(false);
                }else{
                    binding.btCheck.setEnabled(true);
                }
            }
        });
    }
}