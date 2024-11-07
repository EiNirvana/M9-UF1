package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPairGenerator;
import javax.crypto.Cipher;;


public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception{
        KeyPairGenerator pair = KeyPairGenerator.getInstance("RSA"); //RSA = tipus d'algoritme que volem fer servir
        pair.initialize(2048);
        return pair.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        Cipher xifrat = Cipher.getInstance("RSA");
        xifrat.init(Cipher.ENCRYPT_MODE, clauPublica);
        return xifrat.doFinal(msg.getBytes());
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception{
        Cipher xifrar = Cipher.getInstance("RSA");
        xifrar.init(Cipher.DECRYPT_MODE, ClauPrivada);
        byte[] desencriptat = xifrar.doFinal(msgXifrat);
        return new String(desencriptat);
    }
}
