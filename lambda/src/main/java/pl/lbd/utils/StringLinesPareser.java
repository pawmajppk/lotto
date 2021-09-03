package pl.lbd.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringLinesPareser {
    public static List<String> getLines(String s)
    {
        List<String> lista = new ArrayList<>();
        Scanner scanner = new Scanner(s);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lista.add(line);
        }
        return lista;
    }
}
