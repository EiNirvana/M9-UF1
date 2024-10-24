/**
 * Activitat 1: Criptorització d'un missatge per part de ROT-13, a més del procès invers.
 */

public class XifradorROT13 {
    public static char[] MIN ={'a', 'à', 'b', 'c', 'ç', 'd', 'e','è', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l',
     'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
    public static char[] MAJ = {'A', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L',
     'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args){
        String originalMessage = "La Conservació I El Tracte Correcte Del Mediambient És Important Per A La Salut.";
        String finalMessageEncriptat = "";
        String finalMessageDesencriptat = "";
                    
        finalMessageEncriptat = xifratROT13(originalMessage);
        finalMessageDesencriptat = desxifratROT13(originalMessage);

        System.out.println("Missatge original: " + originalMessage);
        System.out.println("Missatge encriptat: " + finalMessageEncriptat);
        System.out.println("Missatge desencriptat: " + finalMessageDesencriptat); //S'ha usat el mateix missatge en la desencriptació, el text no a de tenir sentit
    }

    public static String xifratROT13(String missatgeOriginal){
        String missatgeFinal = "";
        char caracFinal= ' ';
        char caracLlista;
        int num;
        int llargada = missatgeOriginal.length();
        for (int i = 0; i < llargada; i++){
            num = 13;
            char caracAnal = missatgeOriginal.charAt(i);
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
    public static String desxifratROT13(String missatgeOriginal){
        String missatgeFinal = "";
        char caracFinal= ' ';
        char caracLlista;
        int num;
        int numFinal;
        int llargada = missatgeOriginal.length();
        for (int i = 0; i < llargada; i++){
            num = 13;
            numFinal = 0;
            char caracAnal = missatgeOriginal.charAt(i);
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
        
}
