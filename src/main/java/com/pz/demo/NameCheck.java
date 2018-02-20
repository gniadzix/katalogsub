package com.pz.demo;

import com.pz.demo.DataBase.DBManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class NameCheck {
    String subName;
    List<String> list = new ArrayList<>();

    @Autowired
    DBManager dbManager = DBManager.getInstance();

    public NameCheck(String subName) {
        this.subName = subName;
    }

    public void check() throws SwearException {
        if(subName.contains("kurwa")) throw new SwearException();
    }


}