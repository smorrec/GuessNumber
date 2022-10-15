package com.example.guessnumber.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.InverseMethod;

import java.io.Serializable;
import java.util.Random;

/**
 * Clase que contiene la información del juego
 */
public class Game implements Parcelable, Serializable {
    private String usuario;
    private int numIntentos;
    private int numAdivinar;
    private int respuesta;


    public Game(Game game) {
        usuario = game.usuario;
        numIntentos = game.numIntentos;
        numAdivinar = game.numAdivinar;
        respuesta = game.respuesta;
    }
    public Game(){
        numAdivinar = (new Random()).nextInt(100) +1;
    }

    public Game(String usuario, int numIntentos, int numAdivinar, int respuesta) {
        this.usuario = usuario;
        this.numIntentos = numIntentos;
        this.numAdivinar = numAdivinar;
        this.respuesta = respuesta;
    }

    //region IMPLEMENTACION PARCELABLE
    protected Game(Parcel in) {
        usuario = in.readString();
        numIntentos = in.readInt();
        numAdivinar = in.readInt();
        respuesta = in.readInt();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(usuario);
        parcel.writeInt(numIntentos);
        parcel.writeInt(numAdivinar);
        parcel.writeInt(respuesta);
    }
    //endregion

    //region PARSEO DE ENTRADA
    @InverseMethod("stringToInt")
    public String intToString(int num){
        String text = "";
        try{
            text = String.valueOf(num);
        }catch(Exception e){}
        return text;
    }

    public int stringToInt(String text){
        int num = 0;
        try{
            num = Integer.parseInt(text);
        }catch(Exception e){}
        return num;
    }
    //endregion

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getNumIntentos() {
        return numIntentos;
    }

    public void setNumIntentos(int numIntentos) {
        this.numIntentos = numIntentos;
    }

    public int getNumAdivinar() {
        return numAdivinar;
    }

    public void setNumAdivinar(int numAdivinar) {
        this.numAdivinar = numAdivinar;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Game{" +
                "usuario='" + usuario + '\'' +
                ", numIntentos=" + numIntentos +
                ", numAdivinar=" + numAdivinar +
                ", respuesta=" + respuesta +
                '}';
    }

}
