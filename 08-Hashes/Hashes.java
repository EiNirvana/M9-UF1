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
    private static final int ITERATION = 10; 
    private static final int HASH_SIZE = 16;
    public int npass = 0;

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] algorismes = {"SHA-512", "PBKDF2"};
        String pwTrobat = null;
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt) };
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
            byte[] bytes = hashtec.digest(pw.getBytes(StandardCharsets.UTF_8));
            StringBuilder psline = new StringBuilder();
            for (int i = 0; i <bytes.length; i++){
                psline.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                // 0xff used so each byte is considered an unsigned value
                // 0x100 used to add 256 so each hex string has three chars at least after conversion
                // toString(..., 16) converts to hexadecimal string and -substring(1) treams the unecessary padding.
                finalPass = psline.toString();
            }
        } catch (Exception e){
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
            return hex.formatHex(hash);
        } catch (Exception p){
            System.out.println("ERROR: Something went wrong when creating the PBKDF2.");
            p.printStackTrace();
        }
        return null;
    }

    public String forcaBruta(String alg, String targetHash, String salt){
        String charset = "abcdefABCDEF1234567890!";
        try {
            for (int ll = 1; ll <= 6; ll++) {
                char[] trys = new char[ll];
                int num = charset.length();

                for (int i = 0; i < num; i++) {
                    trys[0] = charset.charAt(i);
                    if (ll == 1 && testPw(trys, alg, targetHash, salt)) {
                        return new String(trys);
                    }

                    for (int n = 0; n < num; n++) {
                        if (ll > 1) {
                            trys[1] = charset.charAt(n);
                        }
                        if (ll == 2 && testPw(trys, alg, targetHash, salt)) {
                            return new String(trys);
                        }

                        for (int f = 0; f < num; f++) {
                            if (ll > 2) {
                                trys[2] = charset.charAt(f);
                            }
                            if (ll == 3 && testPw(trys, alg, targetHash, salt)) {
                                return new String(trys);
                            }

                            for (int d = 0; d < num; d++) {
                                if (ll > 3) {
                                    trys[3] = charset.charAt(d);
                                }
                                if (ll == 4 && testPw(trys, alg, targetHash, salt)) {
                                    return new String(trys);
                                }

                                for (int r = 0; r < num; r++) {
                                    if (ll > 4) {
                                        trys[4] = charset.charAt(r);
                                    }
                                    if (ll == 5 && testPw(trys, alg, targetHash, salt)) {
                                        return new String(trys);
                                    }

                                    for (int p = 0; p < num; p++) {
                                        if (ll > 5) {
                                            trys[5] = charset.charAt(p);
                                        }
                                        if (ll == 6 && testPw(trys, alg, targetHash, salt)) {
                                            return new String(trys);  
                                        }
                                    }
                                }
                            }
                        }
                        
                    }
                }
            }
        } catch (Exception q){
            System.out.println("There has been a mistake when executing forcaBruta");
            q.printStackTrace();
        }
        return null;
    }

    private boolean testPw(char[] attempt, String alg, String targetHash, String salt){
        npass++;
        String attemptStr = new String(attempt);
        String attempHash = alg.equals("SHA-512") ? getSHA512AmbSalt(attemptStr, salt) : getPBKDF2AmbSalt(attemptStr, salt);
        return attempHash != null && attempHash.equals(targetHash);
    }


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
