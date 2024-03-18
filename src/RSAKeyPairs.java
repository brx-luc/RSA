import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSAKeyPairs {
    public void createRSAKeyPairs() throws IOException {
        Random rand = new Random();
        Algorithms algorithms = new Algorithms();
        GenerateE generator = new GenerateE();

        // Generate two primes p and q, create n by multiplying said primes
        BigInteger p = BigInteger.probablePrime(1024, rand);
        BigInteger q = BigInteger.probablePrime(1024, rand);
        BigInteger n = p.multiply(q);
        BigInteger phiOfN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        /*Generate suitable e for public key
        * phiOfN as parameter
        */
        BigInteger e = generator.generateCoprime(phiOfN);
        BigInteger d = algorithms.extendedEuclideanAlgorithm(phiOfN, e);
        String pk = "(" + n +", " + e + ")";
        String sk = "(" + n +", " + d + ")";

        FileWriter privateKey = new FileWriter("sk.txt");
        privateKey.write(sk);
        privateKey.close();

        FileWriter publicKey = new FileWriter("pk.txt");
        publicKey.write(pk);
        publicKey.close();
    }
}
