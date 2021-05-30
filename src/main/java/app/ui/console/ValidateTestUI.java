package app.ui.console;

import app.controller.CreateNewParameterController;
import app.controller.ValidateTestController;
import app.domain.model.Exceptions.EmptyListException;
import app.domain.model.Exceptions.TestDoesntExistException;
import app.mappers.dto.TestDTO;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ValidateTestUI implements Runnable{

    Scanner sc = new Scanner(System.in);
    private ValidateTestController vtctrl = new ValidateTestController();

    @Override
    public void run() {
        vtctrl.getListTestsWithReport();
        try {
            System.out.println("These are the tests ready to be validated:");
            List<TestDTO> listTestsWithReportDTO = vtctrl.toDTO();
            int i = 0;
            Iterator<TestDTO> testDTOIterator = listTestsWithReportDTO.iterator();
            while(testDTOIterator.hasNext()){
                System.out.println("Test#"+ i + "(" + "Registration date and time:" + testDTOIterator.next().getDateOfCreation() + "; Results date and time:" + testDTOIterator.next().getDateResults() + " at " +testDTOIterator.next().getTimeResults() + "; Report date and time:" + testDTOIterator.next().getDateReport() + " at " +testDTOIterator.next().getTimeReport() + ")");
                i++;
            }
            System.out.println("Please write the number of the tests you wish to validate (write -1 to finish).");
            int testNumber = 0;
            List<Integer> testNumbers = null;
            while(testNumber != -1) {
                testNumber = sc.nextInt();
                testNumbers.add(testNumber);
            }

        }catch(EmptyListException e){
            System.out.println("There are no tests for validation!");
        }
    }
}
