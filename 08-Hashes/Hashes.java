/*
 * Resposta a la activitat 08, plantejada el 14 de novembre de 2024.
 */

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;

public class Hashes {
    char charset = "abcdefABCDEF1234567890!".charAt();
    public int npass = 0;
    private static final int ITERATION = 10000; 
    private static final int HASH_SIZE = 16;

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
        try{
            byte[] saltByte = salt.getBytes(StandardCharsets.UTF_8);
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), saltByte, ITERATION, HASH_SIZE * 8);
            SecretKeyFactory skPB = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skPB.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            hash = hex.formatHex(bytes);

        } catch (IOException p){
            System.out.println("ERROR: Something went wrong when creating the PBKDF2.");
            p.printStackTrace();
        }
        return toHex(hash);
    }

    /*  necessary for the modul atop this one.
    private static String toHex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(Integer.toString((array[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    */

    public String forcaBruta(String alg, String hash, String salt){
        /**
         * for (---)
         * if ((testPw (0-----) != null) return pw
         * for (---)
         * testPw (-0----)
         * for ...
         */
        try{
            for (int length = 1; length <= 6; length++) {
                // Generem totes les combinacions possibles de longitud actual
                char[] password = new char[length];
                if ((testPw(password, 0, length, alg, targetHash, salt)) != null) {
                    return new String(password);
                }
            }
            return null;

        } catch (IOException q){
            System.out.println("There has been a mistake when executing forcaBruta");
            q.printStackTrace();
        }
    }

    private boolean testPw()


    public String getInterval(long t1, long t2){
        long duration = t2 - t1;

        long seconds = duration / 1000;
        seconds = seconds % 60;

        long millis = duration % 1000;

        long minutes = seconds / 60;
        minutes = minutes % 60;

        long hours = minutes / 60;
        hours = hours % 24;

        long days = hours / 24;
        
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", 
                days, hours, minutes, seconds, millis);
    }
}
