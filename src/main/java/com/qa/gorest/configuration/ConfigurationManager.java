package com.qa.gorest.configuration;

import com.qa.gorest.framworkException.APIFrameworkException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private Properties prop;
    private FileInputStream fis;
    // maven: command line argument
    // mvn clean install -Denv="qa"

    public Properties initProp() {
        prop = new Properties();
        String envName = System.getProperty("env");
        System.out.println("Running tests on env: " + envName);
        try {
            if (envName == null) {
                System.out.println("No env is given, hence running on QA env...");
                fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
            } else {
                System.out.println("Running tests on env: " + envName);
                switch (envName.toLowerCase().trim()) {
                    case "qa":
                        fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;
                    case "stage":
                        fis = new FileInputStream("./src/test/resources/config/stage.config.properties");
                        break;
                    case "dev":
                        fis = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;
                    default:
                        System.out.println("Running on QA env...");
                        throw new APIFrameworkException("WRONG ENV IS Given");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (fis != null)
                prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }
}
