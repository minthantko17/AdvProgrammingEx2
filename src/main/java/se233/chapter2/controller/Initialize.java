package se233.chapter2.controller;

import se233.chapter2.model.Currency;
import se233.chapter2.model.CurrencyEntity;

import java.util.List;

public class Initialize {
    public static Currency initializeApp(){
        Currency c=new Currency("USD");
        List<CurrencyEntity> cList=FetchData.fetchRange(c.getShortCode(), 8);
        c.setHistorical(cList);
        c.setCurrent(cList.get(cList.size()-1));
        return c;
    }
}
