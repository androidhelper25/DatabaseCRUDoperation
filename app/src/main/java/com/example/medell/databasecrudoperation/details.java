package com.example.medell.databasecrudoperation;

/**
 * Created by me dell on 15-11-2017.
 */

public class details {
    private   String name,num,pwd;
    public details(String name, String num, String pwd)
    {
        this.name = name;
        this.num = num;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
