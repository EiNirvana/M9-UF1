package iticbcn.xifratge;

import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class XifradorAES implements Xifrador {
    public final String ALGORISME_XIFRAT = "AES";
    public final String ALGORISME_HASH = "SHA-256";
    public final String FORMAT_AES = "AES/CBC/PKCS5Padding";
       
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private final String CLAU = "LaClauSecretaQueVulguis";

    public byte[] xifraAES(String msg, String clau) throws Exception {
        //Obtenir els bytes de l’String	
        byte[] missOriginal = msg.getBytes();

        // Genera IvParameterSpec
        // 
        iv = new byte[MIDA_IV];
        SecureRandom randomIV = new SecureRandom();
        randomIV.nextBytes(iv);
        IvParameterSpec ivParams = new IvParameterSpec(iv); 

        // Genera hash
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = digest.digest(clau.getBytes());
        SecretKeySpec key = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParams);

        // Combinar IV i part xifrada.
        byte[] msgXifrat = cipher.doFinal(missOriginal);
        byte[] missFinal = new byte[iv.length + msgXifrat.length];
        System.arraycopy(iv, 0, missFinal, 0, iv.length);
        System.arraycopy(msgXifrat, 0, missFinal, iv.length, msgXifrat.length);

        // return iv+msgxifrat
        return missFinal;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        String missatgeFinal = " ";
        // Extreure l'IV.
        iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivParams = new IvParameterSpec(iv);

        // Extreure la part xifrada.
        byte[] msgXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

        // Fer hash de la clau
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = digest.digest(clau.getBytes());
        SecretKeySpec key = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        // Desxifrar.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParams);
        byte[] missDesxifrat = cipher.doFinal(msgXifrat);

        // return String desxifrat
        missatgeFinal = new String(missDesxifrat);
        return missatgeFinal;
    }

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        try {
            byte[] msgXifratge = xifraAES(msg, clau);
            return new TextXifrat(msgXifratge);
        } catch (NumberFormatException i){
            throw new ClauNoSuportada("La clau no es un número acceptable per a la rotació");
        } 
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{
        try {
            byte[] msgXifrat = xifrat.getBytes();
            return desxifraAES(msgXifrat, clau);
        } catch (NumberFormatException i){
            throw new ClauNoSuportada("La clau no es un número acceptable per a la rotació");
        }

        
    }
}
