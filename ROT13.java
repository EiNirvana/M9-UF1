/**
 * Activitat 1: Criptorització d'un missatge per part de ROT-13, a més del procès invers.
 */

public class ROT13 {
    public static char[] min ={'a', 'à', 'b', 'c', 'ç', 'd', 'e','è', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l',
     'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
    public static char[] maj = {'A', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L',
     'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args){
        String originalMessage;
        String finalMessage;

        System.out.println("Vols encriptar o desencriptar?");
        String resposta = Entrada.readLine();
        if (!resposta.isBlank()){
            if (resposta.equals("encriptar") || resposta.equals("Encriptar")){
                    System.out.println("Escriu el missatge");
                    originalMessage = Entrada.readLine();
                    finalMessage = encriptacio(originalMessage);

                } else if (resposta.equals("desencriptar") || resposta.equals("Desencriptar")){
                    System.out.println("Escriu el missatge");
                    originalMessage = Entrada.readLine();
                    finalMessage = desencriptacio(originalMessage);
                } 
                System.out.println("Cambi complert");
                System.out.println("Missatge final: " + finalMessage);
        } else {
            System.out.println("error");
        }
    }

    public static String encriptacio(String missatgeOriginal){
        String missatgeFinal;
        char caracFinal;
        char caracLlista;
        int num = 13;
        int llargada = missatgeOriginal.length();
        for (int i = 0; i < llargada; i++){
            char caracAnal = missatgeOriginal.charAt(i);
            for (int p = 0; p < llargada; p++){
                if (Character.isUpperCase(caracAnal)){
                    caracLlista = maj[p];
                    if (caracLlista == caracAnal){
                        num += p; 
                        if (num > maj.length){num = num - maj.length;}
                    } else continue;
                } else {
                    caracLlista = min[p];
                    if (caracLlista == caracAnal){
                        num += p;
                        if (num > min.length){num = num - min.length;}
                    } else continue;
                }
            }
            missatgeFinal += caracFinal;
        }
        if (missatgeFinal.isEmpty()){missatgeFinal = "?";}
        return missatgeFinal;
    }
    public static String desencriptacio(String missatge){
        String missatgeFinal;
        char caracFinal;
        char caracLlista;
        int num = 13;
        int llargada = missatge.length();
        for (int i = 0; i < llargada; i++){
            char caracAnal = missatge.charAt(i);
            for (int p = 0; p < llargada; p++){
                if (Character.isUpperCase(caracAnal)){
                    caracLlista = maj[p];
                    if (caracLlista == caracAnal){
                        num += p; 
                        if (num < 0){num = num + maj.length;}
                    } else continue;
                } else {
                    caracLlista = min[p];
                    if (caracLlista == caracAnal){
                        num -= p;
                        if (num < 0){num = num + min.length;}
                    } else continue;
                }
            }
            missatgeFinal += caracFinal;
        }
        if (missatgeFinal.isEmpty()){missatgeFinal = "?";}
        return missatgeFinal;
    }
}
