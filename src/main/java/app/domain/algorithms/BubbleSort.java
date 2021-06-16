package app.domain.algorithms;

import app.domain.model.Client;
import app.domain.adapter.Sortable;

import java.util.List;

public class BubbleSort implements Sortable {

    public Client[] sortByTin(List<Client> clientList){
        Client[] clientArr = new Client[clientList.size()];
        clientList.toArray(clientArr);
        /**
         * Inspired by "https://www.stackabuse.com/sorting-algorithms-in-java" .
         */
        boolean sorted = false;
        Client temp;
        while(!sorted){
            sorted = true;
            for (int i = 0; i < clientArr.length - 1; i++){
                if(clientArr[i].compareToTin(clientArr[i+1]) > 0){
                    temp = clientArr[i];
                    clientArr[i] = clientArr[i+1];
                    clientArr[i+1] = temp;
                    sorted = false;
                }
            }
        }
        return clientArr;
    }

    public Client[] sortByName(List<Client> clientList){
        Client[] clientArr = new Client[clientList.size()];
        clientList.toArray(clientArr);
        /**
         * Inspired by "https://www.stackabuse.com/sorting-algorithms-in-java" .
         */
        boolean sorted = false;
        Client temp;
        while(!sorted){
            sorted = true;
            for (int i = 0; i < clientArr.length - 1; i++){
                if(clientArr[i].compareToName(clientArr[i+1]) > 0){
                    temp = clientArr[i];
                    clientArr[i] = clientArr[i+1];
                    clientArr[i+1] = temp;
                    sorted = false;
                }
            }
        }
        return clientArr;
    }
}
