package Kaufvertrag.dataLayer;

import java.util.Arrays;
import java.util.List;

public class Formatter {

    public static String ListToString(List<String> list) {

        String formattedString = list.get(0);
        list.remove(0);

        formattedString = String.join(", ", list);

        return formattedString;
    }

    public static List<String> StringToList(String string) {
        
        List<String> list = Arrays.asList(string.split(", "));
        
        return list;
    }
    
}
