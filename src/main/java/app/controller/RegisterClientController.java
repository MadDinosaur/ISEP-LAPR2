package app.controller;

import app.domain.store.ClientStore;

public class RegisterClientController {
    // public String setName(){
     //   return
   // }

    public String getSexOpcao(int opcao){
        if (opcao == 1){
            return "masculine";
        }if(opcao == 2){
            return "femenine";
        }else {
            return "";
        }
    }

}