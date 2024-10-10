//Resposta de la pràctica quatre, amb una codificació més extrema

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class polialfabetic {
    public static char[] MIN ={'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e','è', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l',
     'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
    public static final int clauSecreta = 16;
    public static Random random = new Random();

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, año",
                "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
    
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }
    
        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static String xifraPoliAlfa(String msg){
        String finalMessage = "";
        char carac;
        boolean upperCase = false;

        for (int i = 0; i < msg.length(); i++){
            carac = msg.charAt(i);
            if (!Character.isLetter(carac)){finalMessage += carac;}
            else{
                if (Character.isUpperCase(carac)){upperCase = true; Character.toLowerCase(carac);}
                int num = 0;
                    
            }
            upperCase = false;
        }
        
        return finalMessage;
    }

    public static String desxifraPoliAlfa(String msgXifrat){
        String finalMessage = "";



        return finalMessage;
    }

    public static void initRandom(int clauSecreta){
        random.setSeed(clauSecreta);

        MIN = transformListToArray(permutaAlfabet(transformArrayToList(MIN)));
    }

    public static List<Character> permutaAlfabet(List<Character> alfabetList){

        Collections.shuffle(alfabetList, random);

        return alfabetList;
    }

    public static List<Character> transformArrayToList(char[] list){
        List<Character> alfabetList = new ArrayList<>();

        for (char carac:list){
            alfabetList.add(carac);
        }
        return alfabetList;
    }

    public static char[] transformListToArray(List<Character> alfabetList){
        char[] list = new char[alfabetList.size()];

        for (int i = 0; i < alfabetList.size(); i++){
            list[i] = alfabetList.get(i);
        }
        return list;
    }