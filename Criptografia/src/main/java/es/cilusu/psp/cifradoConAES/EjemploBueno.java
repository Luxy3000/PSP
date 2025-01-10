package es.cilusu.psp.cifradoConAES;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EjemploBueno {
    public static void main(String[] args) throws Exception {
        // 1. Clave de 16 bytes (128 bits) para AES
        String clave = "1234567890123456"; //Exactamente 16 caracteres
        SecretKeySpec key = new SecretKeySpec(clave.getBytes(), "AES");

        // 2. Texto a encriptar
        String textoEnClaro = "Hola, esto es un ejemplo";

        // 3. Crear cifrador AES
        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // 4. Encriptar el texto
        aes.init(Cipher.ENCRYPT_MODE, key);
        byte[] textoEncriptado = aes.doFinal(textoEnClaro.getBytes());
        System.out.println("Texto encriptado: " + bytesToHex(textoEncriptado));

        // 5. Desencriptar el texto
        aes.init(Cipher.DECRYPT_MODE, key);
        byte[] textoDesencriptado = aes.doFinal(textoEncriptado);
        System.out.println("Texto desencriptado: " + new String(textoDesencriptado));
    }

    //Convertir bytes a formato hexadecimal legible
    private static String bytesToHex(byte[] bytes){
        String resultado = "";
        for(byte b : bytes){
            resultado += Integer.toHexString(b & 0xFF);
        }
        return resultado;
    }
}
