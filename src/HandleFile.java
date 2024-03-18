import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class HandleFile {
    Algorithms algos = new Algorithms();
    //Exercise 2
    public void encryptTXTFile(String fileURL) throws IOException {
        List<String> publicKeyPair = getKeyPair("pk.txt");
        BigInteger n = new BigInteger(publicKeyPair.get(0));
        BigInteger e = new BigInteger(publicKeyPair.get(1));
        List<String> processed = new ArrayList<>();

        //Read each char of txt file and decrypt it with the fast exponential algorithm
        try(BufferedReader buffer = new BufferedReader(new FileReader(fileURL))){
            int x;
            while ((x = buffer.read()) != -1){
                processed.add(algos.fastExponentialAlgorithm(new BigInteger(String.valueOf(x)), e, n).toString());
            }
        } catch (IOException io) {
            io.printStackTrace();
        }

        try(FileWriter writer = new FileWriter("cipher.txt")){
            writer.write(String.join(",",processed));
        }
    }

    //Excercise 3
    public void decryptTXTFile(String encryptedFileURL) throws IOException {
        List<String> privateKeyPair = getKeyPair("sk.txt");
        BigInteger n = new BigInteger(privateKeyPair.get(0));
        BigInteger d = new BigInteger(privateKeyPair.get(1));
        List<String> processed = new ArrayList<>();

        //Read the encrypted file into a String list
        List<String> input = Files.readAllLines(new File(encryptedFileURL).toPath(), StandardCharsets.UTF_8).stream()
                .flatMap(s -> Stream.of(s.split(",")))
                .toList();

        /*loop throug input list, foreach BigInteger in file decrypt the char with the fas exponential algorithm
        * convert each calculated int to String, add to processed list
        * write String to decryptet txt file*/
        for (String y: input) {
            BigInteger decryptedBigInt = algos.fastExponentialAlgorithm(new BigInteger(String.valueOf(y)), d, n);
            String decryptedChar = new String(decryptedBigInt.toByteArray());
            processed.add(decryptedChar);
        }

        try(FileWriter writer = new FileWriter("text-d.txt")){
            writer.write(String.join("",processed));
        }
    }

    private List<String> getKeyPair(String fileName) throws IOException {
        return Files.readAllLines(new File(fileName).toPath(), StandardCharsets.UTF_8).stream()
                .flatMap(s -> Stream.of(s.split("[(), ]")))
                .filter(s -> !s.isBlank())
                .toList();
    }
}
