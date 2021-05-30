package app.ui.console;

import app.controller.CreateNewParameterController;
import app.controller.ValidateTestController;
import app.domain.model.Exceptions.EmptyListException;
import app.domain.model.Exceptions.TestDoesntExistException;
import app.mappers.dto.TestDTO;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ValidateTestUI implements Runnable{

    Scanner sc = new Scanner(System.in);
    private ValidateTestController vtctrl = new ValidateTestController();

    @Override
    public void run() {
      vtctrl.getListTestsWithReport();
      if(vtctrl.toDTO() != null) {
          System.out.println("These are the available tests:");
          Iterator<TestDTO> testDTOIterator = vtctrl.toDTO().iterator();
          int i = 0;
          List<String> nhscodes = null;
          while(testDTOIterator.hasNext()) {
              System.out.println("Test#" + i + ": " + testDTOIterator.next());
              nhscodes.add(testDTOIterator.next().getNhsCode());
              i++;
          }
          System.out.println("Please select the tests you wish to validate by their number (write -1 when you have finished).");
          int tests = sc.nextInt();
          i = 0;
          while(tests != -1){
              Iterator<String> nhscodesIterator = nhscodes.iterator();
              while(nhscodesIterator.hasNext()){
                  if(i == tests){
                      vtctrl.validateTest(nhscodesIterator.next());
                      try {
                          vtctrl.sendNotification(nhscodesIterator.next());
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                      System.out.println("The client has been notified.");
                  }
                  i++;
              }
              tests = sc.nextInt();
          }
          System.out.println("All test have been validated.");
        }

    }
}
