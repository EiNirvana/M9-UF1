package iticbcn.xifratge;

//Resposta de la pràctica quatre, amb una codificació més extrema

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador{
    public static char[] MIN ={'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e','è', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l',
     'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};
    public static final int clauSecreta = 16;
    public Random random = new Random();


    public String xifraPoliAlfa(String msg) throws ClauNoSuportada{
        StringBuilder finalMessage = new StringBuilder();
        char carac;
        boolean upperCase = false;
        char caracFinal;
        
        for (int i = 0; i < msg.length(); i++){
            char[] mixMin = transformListToArray(permutaAlfabet(transformArrayToList(MIN)));
            carac = msg.charAt(i);
            if (!Character.isLetter(carac)){finalMessage.append(carac);}
            else{
                if (Character.isUpperCase(carac)){upperCase = true; carac = Character.toLowerCase(carac);}
                for (int p = 0; p < MIN.length; p++){
                    if (carac == MIN[p]) {
                        caracFinal = mixMin[p];
                        if (upperCase == true){caracFinal = Character.toUpperCase(caracFinal);}
                        finalMessage.append(caracFinal);
                        upperCase = false;
                        break;
                    }
                }
            }
        }
        return finalMessage.toString();
    }

    public String desxifraPoliAlfa(String msgXifrat) throws ClauNoSuportada{
        StringBuilder finalMessage = new StringBuilder();
        char carac;
        boolean upperCase = false;
        char caracFinal;
        
        for (int i = 0; i < msgXifrat.length(); i++){
            char[] mixMin = transformListToArray(permutaAlfabet(transformArrayToList(MIN)));
            carac = msgXifrat.charAt(i);
            if (!Character.isLetter(carac)){finalMessage.append(carac);}
            else{
                if (Character.isUpperCase(carac)){upperCase = true; carac = Character.toLowerCase(carac);}
                for (int p = 0; p < MIN.length; p++){
                    if (carac == mixMin[p]) {
                        caracFinal = MIN[p];
                        if (upperCase == true){caracFinal = Character.toUpperCase(caracFinal);}
                        finalMessage.append(caracFinal);
                        upperCase = false;
                        break;
                    }
                }
            }
        }
        return finalMessage.toString();
    }

    public void initRandom(int clauSecreta){
        random.setSeed(clauSecreta);
    }

    public List<Character> permutaAlfabet(List<Character> alfabetList){

        Collections.shuffle(alfabetList, random);

        return alfabetList;
    }

    public List<Character> transformArrayToList(char[] list){
        List<Character> alfabetList = new ArrayList<>();

        for (char carac:list){
            alfabetList.add(carac);
        }
        return alfabetList;
    }

    public char[] transformListToArray(List<Character> alfabetList){
        char[] list = new char[alfabetList.size()];

        for (int i = 0; i < alfabetList.size(); i++){
            list[i] = alfabetList.get(i);
        }
        return list;
    }

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        String msgXifratge = xifraPoliAlfa(msg);
        return new TextXifrat(msgXifratge.getBytes());
    }
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{
        String msgXifrat = new String(xifrat.getBytes());
        return desxifraPoliAlfa(msgXifrat);
    }
}