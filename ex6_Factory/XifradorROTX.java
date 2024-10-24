/**
 * Exercici següent a ROT13, en que ara l'usuari escull quin es el núm de la codificació. 
 * A més a més, hi ha una pàgina que descodifica el codi a la força.
 */

public class XifradorROTX {
    public static char[] MIN ={'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e','è', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l',
     'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
    public static char[] MAJ = {'A', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L',
     'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};
    public static String[] numOriginalMessage = {
        "The quick fox jumps over the angry dog", 
        "La zebra busca el seu calçat",
        "A Rafael le ha venido una ñoña de tres al cuarto",
        "Busca el que és, i el que no és, no serà pas.",
        "La Conservació I El Tracte Correcte Del Mediambient És Important Per A La Salut."
    };

    public static void main(String[] args){
        String finalMessageEncriptat = "";
        String finalMessageDesencriptat = "";
        String finalMessageForsat = "";
        int[] numProves = {3, 15, 5, 9, 8};
        
        
        for (int i = 0; i < numProves.length; i++){
            int desplaçament = numProves[i];
            String originalMessage = numOriginalMessage[i];

            finalMessageEncriptat = xifratROTX(originalMessage, desplaçament);
            finalMessageDesencriptat = desxifratROTX(finalMessageEncriptat, desplaçament);
            finalMessageForsat = desxifratROT(finalMessageEncriptat);
            
            
            System.out.println("Missatge original: " + originalMessage);
            System.out.println("Missatge encriptat: " + finalMessageEncriptat);
            System.out.println("Missatge desencriptat: " + finalMessageDesencriptat); //S'ha usat el mateix missatge en la desencriptació, el text no a de tenir sentit
            System.out.printf("Missatge desencriptat per força: \n" + finalMessageForsat);
        }
    }

    public static String xifratROTX(String cadena, int desplaçament){
        String missatgeFinal = "";
        char caracFinal= ' ';
        char caracLlista;
        int num;
        int llargada = cadena.length();
        for (int i = 0; i < llargada; i++){
            num = desplaçament;
            char caracAnal = cadena.charAt(i);
            if (Character.isLetter(caracAnal)){
                if (Character.isUpperCase(caracAnal)){
                    for (int p = 0; p < MAJ.length; p ++){
                        caracLlista = MAJ[p];
                        if (caracLlista == caracAnal){
                            num += p; 
                            if (num >= MAJ.length){
                                num = num - MAJ.length;
                                caracFinal = MAJ[num];
                            } else {caracFinal = MAJ[num];}
                            missatgeFinal += caracFinal;
                        } else continue;
                    }
                } else {
                    for (int p = 0; p < MIN.length; p++){
                        caracLlista = MIN[p];
                        if (caracLlista == caracAnal){
                            num += p;
                            if (num >= MIN.length){
                                num = num - MIN.length;
                                caracFinal = MIN[num];
                            } else {caracFinal = MIN[num];}
                            missatgeFinal += caracFinal;
                        } else continue;
                    }
                }
            } else {missatgeFinal += caracAnal;}
        }
        if (missatgeFinal.isEmpty()){missatgeFinal = "?";}
        return missatgeFinal;
    }
    public static String desxifratROTX(String cadena, int desplaçament){
        String missatgeFinal = "";
        char caracFinal= ' ';
        char caracLlista;
        int num;
        int numFinal;
        int llargada = cadena.length();
        for (int i = 0; i < llargada; i++){
            num = desplaçament;
            numFinal = 0;
            char caracAnal = cadena.charAt(i);
            if (Character.isLetter(caracAnal)){
                if (Character.isUpperCase(caracAnal)){
                    for (int p = 0; p < MAJ.length; p ++){
                        caracLlista = MAJ[p];
                        if (caracLlista == caracAnal){
                            numFinal = p - num; 
                            if (numFinal < 0){
                                numFinal = numFinal + MAJ.length;
                                caracFinal = MAJ[numFinal];
                            } else {caracFinal = MAJ[numFinal];}
                            missatgeFinal += caracFinal;
                        } else continue;
                    }
                } else {
                    for (int p = 0; p < MIN.length; p++){
                        caracLlista = MIN[p];
                        if (caracLlista == caracAnal){
                            numFinal = p - num; 
                            if (numFinal < 0){
                                numFinal = numFinal + MIN.length;
                                caracFinal = MIN[numFinal];
                            } else {caracFinal = MIN[numFinal];}
                            missatgeFinal += caracFinal;
                        } else continue;
                    }
                }
            } else {missatgeFinal += caracAnal;}
        }
        if (missatgeFinal.isEmpty()){missatgeFinal = "?";}
        return missatgeFinal;
    }

    public static String desxifratROT(String cadenaXifrada){
        String missatgeDesxifrat = "";

        for (int i = 0; i < MIN.length; i++){
            missatgeDesxifrat += xifratROTX(cadenaXifrada, i) + "\n";
        }

        return missatgeDesxifrat;
    }

}
