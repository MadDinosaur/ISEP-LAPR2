package app.domain.model.Exceptions;

import app.domain.model.Client;

import java.util.List;

public class InsertionSort {

    public Client[] sortByTin(List<Client> clientList) {
        Client[] clientArr = new Client[clientList.size()];
        clientList.toArray(clientArr);
        /**
         * Inspired by "https://www.stackabuse.com/sorting-algorithms-in-java" .
         */
        for (int i = 1; i < clientArr.length; i++) {
            Client current = clientArr[i];
            int j = i - 1;
            while(j >= 0 && current.compareToTin(clientArr[j]) < 0) {
                clientArr[j+1] = clientArr[j];
                j--;
            }
            // at this point we've exited, so j is either -1
            // or it's at the first element where current >= a[j]
            clientArr[j+1] = current;
        }
        return clientArr;
    }

    public Client[] sortByName(List<Client> clientList) {
        Client[] clientArr = new Client[clientList.size()];
        clientList.toArray(clientArr);
        /**
         * Inspired by "https://www.stackabuse.com/sorting-algorithms-in-java" .
         */
        for (int i = 1; i < clientArr.length; i++) {
            Client current = clientArr[i];
            int j = i - 1;
            while(j >= 0 && current.compareToName(clientArr[j]) < 0) {
                clientArr[j+1] = clientArr[j];
                j--;
            }
            // at this point we've exited, so j is either -1
            // or it's at the first element where current >= a[j]
            clientArr[j+1] = current;
        }
        return clientArr;
    }
}
