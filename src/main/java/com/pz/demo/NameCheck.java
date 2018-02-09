package com.pz.demo;

import com.pz.demo.DataBase.DBManager;
import org.springframework.aop.target.LazyInitTargetSource;
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

    public void check() throws MyException {
        if(subName == "kurwa") throw new MyException();
    }


}