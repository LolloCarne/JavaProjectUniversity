package com.DTO;
import java.util.Random;


public class CartaSpacca {
    public String lettera;
    private String path;
    

    public CartaSpacca(String lettera){
        this.lettera = lettera;
        this.path=lettera+".png";
    }

    public String getPath() {
        return path;
    }

}
