import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        /*How to:
        * 1. Create keypair both will be saved in the project level directory
        * 2. encrypt a txt-file, URL must be specified
        * 3. decrypt a txt-file, URL must be specified
        * To skip a step simply comment the respective method*/

        RSAKeyPairs rsaKeyPairs = new RSAKeyPairs();
        HandleFile handleFile = new HandleFile();

        //Exercise 1: Create public and private keypair
        rsaKeyPairs.createRSAKeyPairs();

        /*Exercise 2: encrypt txt file with public key
        * txt file URL to encrypt as parameter
        */
        handleFile.encryptTXTFile("text.txt");


        /*Exercise 3: decrypt txt file with private key
        * txt file URL to decrypt as parameter
        */
        handleFile.decryptTXTFile("cipher.txt");

    }
}