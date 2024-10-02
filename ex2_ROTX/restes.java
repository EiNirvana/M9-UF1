public class restes {
    public static char[] MIN ={'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e','è', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l',
    'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
   public static char[] MAJ = {'A', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L',
    'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};

    public static String desxifratROT(String cadenaXifrada){
        String missatgeDesxifrat = "";

        for (int i = 0; i < MIN.length; i++){
            for (int p = 0; p < cadenaXifrada.length(); p++){
                char carac = cadenaXifrada.charAt(p);
                if (letterOrNotLetter(carac) == false) missatgeDesxifrat += carac;
                else {
                    if (p == cadenaXifrada.length()) {
                        if (minOMaj(carac) == false) missatgeDesxifrat = missatgeDesxifrat + MAJ[(p + i) % MAJ.length] + "\n";
                        else missatgeDesxifrat += MIN[(p + i) % MIN.length] + "\n";
                    } else {
                        if (minOMaj(carac) == false) missatgeDesxifrat += MAJ[(p + i) % MAJ.length];
                        else missatgeDesxifrat += MIN[(p + i) % MIN.length];
                    } 
                }
            }
        }

        return missatgeDesxifrat;
    }

    public static boolean minOMaj(char carac){
        if (!Character.isUpperCase(carac)) return true;
        return false;
    }

    public static boolean letterOrNotLetter(char carac){
        return Character.isLetter(carac); 
    }
}
