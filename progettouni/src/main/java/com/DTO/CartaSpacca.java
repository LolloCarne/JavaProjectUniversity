package com.DTO;
import java.util.Random;


public class CartaSpacca {
    public String lettera;
    
    public Random r = new Random();

    public CartaSpacca(String lettera){
        this.lettera = lettera;
    }

    public String getLettera(){
        String[] list = {"S", "P", "A", "C"};
        for (int i =0; i< list.length; i++){
            lettera= list[r.nextInt(4)+1];
        }
        return lettera;
    }

}
