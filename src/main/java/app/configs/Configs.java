package app.configs;

import app.domain.model.Company;

import java.io.*;
import java.util.Properties;

public class Configs {

    public static Properties props = new Properties();

    public void saveProp(String title, String value){
        try{
            OutputStream output = new FileOutputStream("config.properties");
            props.store(output, null);
            output.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public void loadProp() throws FileNotFoundException{
        try {
            InputStream input = new FileInputStream("config.properties");
            props.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SortingAlgorithm getPerformanceEvaluator() {
        String className = props.getProperty("Company.SortingAlgorithm.Class");
        Class<?> oClass = null;
        try {
            oClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SortingAlgorithm sortingAlgorithm = null;
        try {
            sortingAlgorithm = (SortingAlgorithm) oClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortingAlgorithm;
    }
}
