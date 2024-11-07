package iticbcn.xifratge;

//import org.w3c.dom.Text;

/**
 * Exercici següent a ROT13, en que ara l'usuari escull quin es el núm de la codificació. 
 * A més a més, hi ha una pàgina que descodifica el codi a la força.
 */

public class XifradorROTX implements Xifrador {
    public static char[] MIN ={'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e','è', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l',
     'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
    public static char[] MAJ = {'A', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L',
     'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};
    public String[] numOriginalMessage = {
        "The quick fox jumps over the angry dog", 
        "La zebra busca el seu calçat",
        "A Rafael le ha venido una ñoña de tres al cuarto",
        "Busca el que és, i el que no és, no serà pas.",
        "La Conservació I El Tracte Correcte Del Mediambient És Important Per A La Salut."
    };
    public int rot = 0;


    public String xifratROTX(String cadena, int desplaçament){
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
    public String desxifratROTX(String cadena, int desplaçament){
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

    public String desxifratROT(String cadenaXifrada){
        String missatgeDesxifrat = "";

        for (int i = 0; i < MIN.length; i++){
            missatgeDesxifrat += xifratROTX(cadenaXifrada, i) + "\n";
        }

        return missatgeDesxifrat;
    }

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        try {
            rot = Integer.parseInt(clau);
        } catch (NumberFormatException i){
            throw new ClauNoSuportada("La clau no es un número acceptable per a la rotació");
        }

        String msgXifratge = xifratROTX(msg, rot);
        return new TextXifrat(msgXifratge.getBytes());
    }
    
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{
        try {
            rot = Integer.parseInt(clau);
        } catch (NumberFormatException i){
            throw new ClauNoSuportada("La clau no es un número acceptable per a la rotació");
        }

        String msgXifrat = new String(xifrat.getBytes());
        return desxifratROTX(msgXifrat, rot);
    }
}
