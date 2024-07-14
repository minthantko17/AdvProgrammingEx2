package se233.chapter2.controller;

import javafx.scene.control.TextInputDialog;
import se233.chapter2.Launcher;
import se233.chapter2.model.Currency;
import se233.chapter2.model.CurrencyEntity;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class AllEventHandlers {
    public static void onRefresh(){
        try{
            Launcher.refreshPane();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void onAdd(){
        try{
            TextInputDialog dialog=new TextInputDialog();
            dialog.setTitle("Add Currency");
            dialog.setContentText("Currency code: ");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            Optional<String> code=dialog.showAndWait();
            if (code.isPresent()){
                List<Currency> currencyList=Launcher.getCurrencyList();
                Currency c=new Currency(code.get().toUpperCase());
                List<CurrencyEntity> cList=FetchData.fetchRange(Launcher.getBaseCurrency(), c.getShortCode(), 30);
                c.setHistorical(cList);
                c.setCurrent(cList.get(cList.size()-1));
                currencyList.add(c);
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }

    public static void onDelete(String code){
        try{
            List<Currency> currencyList=Launcher.getCurrencyList();
            int index=-1;
            for(int i=0; i<currencyList.size(); i++){
                if (currencyList.get(i).getShortCode().equals(code)){
                    index=i;
                    break;
                }
            }
            if(index!=-1){
                currencyList.remove(index);
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }

    public static void changeBaseCurrency(){
        try{
            TextInputDialog dialog=new TextInputDialog();
            dialog.setTitle("Change Base Currency");
            dialog.setHeaderText("You current currency code: "+ Launcher.getBaseCurrency());
            dialog.setContentText("Currency code: ");
            dialog.setGraphic(null);
            Optional<String> code=dialog.showAndWait();
            if (code.isPresent()){
                List<Currency> currencyList=Launcher.getCurrencyList();
                String prevCurrency= Launcher.getBaseCurrency().toUpperCase();
                Launcher.setBaseCurrency(code.get().toUpperCase());
                for(Currency c:currencyList){
                    List<CurrencyEntity> cList;
                    if(code.get().toUpperCase().equals(c.getShortCode())){
                        cList = FetchData.fetchRange(c.getShortCode(), prevCurrency, 30);
                        c.setShortCode(prevCurrency);
                    }else {
                        cList = FetchData.fetchRange(Launcher.getBaseCurrency(), c.getShortCode(), 30);
                    }
                    c.setHistorical(cList);
                    c.setCurrent(cList.get(cList.size()-1));
                    c.setWatch(false);
                    c.setWatchRate(0.0);
                }
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }

    public static void onWatch(String code){
        try{
            List<Currency> currencyList=Launcher.getCurrencyList();
            int index=-1;
            for(int i=0; i<currencyList.size(); i++){
                if (currencyList.get(i).getShortCode().equals(code)){
                    index=i;
                    break;
                }
            }
            if(index!=-1){
                TextInputDialog dialog=new TextInputDialog();
                dialog.setTitle("Add Watch");
                dialog.setContentText("Rate: ");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                Optional<String> retrievedRate=dialog.showAndWait();
                if(retrievedRate.isPresent()){
                    double rate=Double.parseDouble(retrievedRate.get());
                    currencyList.get(index).setWatch(true);
                    currencyList.get(index).setWatchRate(rate);
                    Launcher.setCurrencyList(currencyList);
                    Launcher.refreshPane();
                }
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }

    public static void unWatch(String code){
        try {
            List<Currency> currencyList = Launcher.getCurrencyList();
            int index = -1;
            for (int i = 0; i < currencyList.size(); i++) {
                if (currencyList.get(i).getShortCode().equals(code)) {
                    index = i;
                }
            }

            if (index != -1) {
                currencyList.get(index).setWatch(false);
                currencyList.get(index).setWatchRate(0.0);
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }

}
