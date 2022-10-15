package com.example.guessnumber;
import android.app.Application;
import com.example.guessnumber.data.Game;

public class GuessNumberApplication extends Application {
    private Game game;

    @Override
    public void onCreate(){
        super.onCreate();
        game = new Game();
    }

    public Game getGame(){
        return game;
    }
}
