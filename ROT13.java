package exercici_1;
/**
 * Activitat 1: Criptorització d'un missatge per part de ROT-13, a més del procès invers.
 */

public class Rot13{
    char[] min ={'a', 'à', 'b', 'c', 'ç', 'd', 'e','è', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l',
     'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
    char[] maj = {'A', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L',
     'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args){
        String originalMessage = Entrada.readLine();
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
                    finalMessage = encriptacio(originalMessage)
                } 

                System.out.println("Cambi complert");
                System.out.println("Missatge final: " + finalMessage);
        } else {
            System.out.println("error");
        }
    }

    public static String encriptacio(String missatgeOriginal){
        String missatgeFinal;
        int llargada = missatgeOriginal.length();
        for (int i = 0; i < llargada; i++){
            char caracAnal = missatgeOriginal.charAt(i);
            for (int p = 0; p < ){}
        }

        return missatgeFinal;
    }
    public static String desencriptacio(String missatge){
        String missatgeFinal;
        String llargada = missatgeOriginal.length(); 

        return missatgeFinal;
    }
}
