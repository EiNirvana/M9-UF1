/*
 * Resposta a la activitat 08, plantejada el 14 de novembre de 2024.
 */

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;

public class Hashes {
    char charset = "abcdefABCDEF1234567890!".charAt();
    public int npass = 0;

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");
            
            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();
            
            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }

    public String getSHA512AmbSalt(String pw, String salt){
        String finalPass = null;

        try{
            MessageDigest hashtec = MessageDigest.getInstance("SHA-512");
            hashtec.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = hashtec.digest(passwordToHash.getBytes());
            StringBuilder psline = new StringBuilder();
            for (int i = 0; i <bytes.length; i++){
                psline.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                // 0xff used so each byte is considered an unsigned value
                // 0x100 used to add 256 so each hex string has three chars at least after conversion
                // toString(..., 16) converts to hexadecimal string and -substring(1) treams the unecessary padding.
                finalPass = psline.toString();
            }
        } catch (IOException e){
            System.out.println("Something went wrong when trying to create a Hash SHA512");
            e.printStackTrace();
        }
        return finalPass;
    }
    public String getPBKDF2AmbSalt(String pw, String salt){

    }
    public String forcaBruta(String alg, String hash, String salt){

    }
    public String getInterval(long t1, long t2){

    }
}
