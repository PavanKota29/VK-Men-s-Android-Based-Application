package com.example.vkmeans;

public class data {
   String name,num,size,typ;
    //for single
    public data(String name, String num, String size, String typ) {
        this.name = name;
        this.num = num;
        this.size = size;
        this.typ = typ;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public String getSize() {
        return size;
    }

    public String getTyp() {
        return typ;
    }
}
