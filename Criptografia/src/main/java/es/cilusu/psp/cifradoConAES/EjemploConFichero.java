package es.cilusu.psp.cifradoConAES;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;

public class EjemploConFichero {
    public static void main(String[] args) throws Exception {
        String nombreFichero = "clave.txt";
        generarYGuardarClave(nombreFichero);
    }

    public static void generarYGuardarClave(String nombreFichero) throws Exception {
        //Generar la clave de 128 bits
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        File archivo = new File(".\\src\\main\\java\\es\\cilusu\\psp\\cifradoConAES\\" + nombreFichero);
        FileOutputStream fos = new FileOutputStream(archivo);
        fos.write(secretKey.getEncoded());
    }


}
