package requestObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class GeneralObject {

    public GeneralObject(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Folosim readerForUpdating pentru a popula obiectul curent din fișierul JSON
            objectMapper.readerForUpdating(this).readValue(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            // Poți arunca o excepție personalizată sau gestiona eroarea altfel
        }

    }
}

