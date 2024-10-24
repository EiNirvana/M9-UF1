package iticbcn.xifratge;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class XifradorMonoalfabetic {
    public static char[] MIN ={'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e','è', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l',
     'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
    public static char[] MAJ = {'A', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L',
     'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};
    public char[] shuffledMAJ = transformListToArray(permutaAlfabet(transformArrayToList(MAJ)));
    public char[] shuffledMIN = transformListToArray(permutaAlfabet(transformArrayToList(MIN)));
    public static String[] numOriginalMessage = {
        "The quick fox jumps over the angry dog", 
        "La zebra busca el seu calçat",
        "A Rafael le ha venido una ñoña de tres al cuarto",
        "Busca el que és, i el que no és, no serà pas.",
        "La Conservació I El Tracte Correcte Del Mediambient És Important Per A La Salut."
    };


    public String xifraMonoAlfa(String cadena){
        String cadenaXifrada = "";

        for (int i = 0; i < cadena.length(); i++){
            char carac = cadena.charAt(i);
            if (!Character.isLetter(carac)){cadenaXifrada += carac;}
            else {
                if (Character.isUpperCase(carac)){
                    int num = 0;
                    for (int p = 0; p < MAJ.length; p++){
                        if (carac == MAJ[p]) num = p; 
                    }
                    cadenaXifrada += shuffledMAJ[num];
                } else {
                    int num = 0;
                    for (int p = 0; p < MIN.length; p++){
                        if (carac == MIN[p]) num = p; 
                    }
                    cadenaXifrada += shuffledMIN[num];
                }
            }
        }
        return cadenaXifrada;
    }

    public String desxifraMonoAlfa(String cadena){
        String cadenaXifrada = "";

        for (int i = 0; i < cadena.length(); i++){
            char carac = cadena.charAt(i);
            if (!Character.isLetter(carac)){cadenaXifrada += carac;}
            else {
                if (Character.isUpperCase(carac)){
                    int num = 0;
                    for (int p = 0; p < shuffledMAJ.length; p++){
                        if (carac == shuffledMAJ[p]) num = p; 
                    }
                    cadenaXifrada += MAJ[num];
                } else {
                    int num = 0;
                    for (int p = 0; p < shuffledMIN.length; p++){
                        if (carac == shuffledMIN[p]) num = p; 
                    }
                    cadenaXifrada += MIN[num];
                }
            }
        }
        return cadenaXifrada;
    }


    public List<Character> permutaAlfabet(List<Character> alfabetList){

        Collections.shuffle(alfabetList);

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
}
