import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
       
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";
   
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet", 
                    "Hola Andrés cómo está tu cuñado", 
                    "Àgora ïlla Ôtto"};
    
        for (int i = 0; i < msgs.length; i++) {
                String msg = msgs[i]; 
    
                byte[] bXifrats = null;
                String desxifrat = "";
                try {
                    bXifrats = xifraAES(msg, CLAU);
                    desxifrat = desxifraAES(bXifrats, CLAU);
                } catch (Exception e) {
                System.err.println("Error de xifrat: " 
                    + e.getLocalizedMessage());
        }
        System.out.println("--------------------");
        System.out.println("Msg: " + msg);
        System.out.println("Enc: " + new String(bXifrats));
        System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        //Obtenir els bytes de l’String	
        byte[] missOriginal = msg.getBytes();

        // Genera IvParameterSpec
        // 
        byte[] iv = new byte[MIDA_IV];
        SecureRandom randomIV = new SecureRandom();
        randomIV.nextBytes(iv);
        IvParameterSpec ivParams = new IvParameterSpec(iv); 

        // Genera hash
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = digest.digest(clau.getBytes());
        SecretKeySpec secretKey = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);

        // Combinar IV i part xifrada.
        byte[] msgXifrat = cipher.doFinal(missOriginal);
        byte[] missFinal = new byte[iv.length + msgXifrat.length];
        System.arraycopy(iv, 0, missFinal, 0, iv.length);
        System.arraycopy(msgXifrat, 0, missFinal, iv.length, msgXifrat.length);

        // return iv+msgxifrat
        return missFinal;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        String missatgeFinal = " ";
        // Extreure l'IV.
        byte[] iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivParams = new IvParameterSpec(iv);

        // Extreure la part xifrada.
        byte[] msgXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

        // Fer hash de la clau
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = digest.digest(clau.getBytes());
        SecretKeySpec secretKey = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        // Desxifrar.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
        byte[] missDesxifrat = cipher.doFinal(msgXifrat);

        // return String desxifrat
        missatgeFinal = new String(missDesxifrat);
        return missatgeFinal;
    }
}
