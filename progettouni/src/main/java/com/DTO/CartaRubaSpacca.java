package com.DTO;
import java.util.ArrayList;

public class CartaRubaSpacca extends Carta {
    String nick;

    public CartaRubaSpacca(String nick, String path){
        super();
        this.nick = nick;
        super.setPath(path);
    }

    public CartaRubaSpacca(){}

    public void rubaSpacca(){
        Utente u = new Utente(nick);
        ArrayList <CartaSpacca> carte= u.carteSpacca;

        for(CartaSpacca s: carte){
            System.out.println(s);
        }
    }

}
