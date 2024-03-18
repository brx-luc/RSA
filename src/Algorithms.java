import java.math.BigInteger;

public class Algorithms {
    /** With the extended euclidean algorithm we can generate a d for our private keypair
    * suitable means e*d mod m = 1
     *@param a
     *@param b
     *@return y0 (as our generated d) */
    public BigInteger extendedEuclideanAlgorithm(BigInteger a, BigInteger b){
        BigInteger x0 = BigInteger.ONE, y0 = BigInteger.ZERO, x1 = BigInteger.ZERO, y1 = BigInteger.ONE, temp;
        while (!b.equals(BigInteger.ZERO)){
            BigInteger q = a.divide(b);
            BigInteger r = a.mod(b);

            a = b;
            b = r;

            temp = x1;
            x1 = x0.subtract(q.multiply(x1));
            x0 = temp;

            temp = y1;
            y1 = y0.subtract(q.multiply(y1));
            y0 = temp;
        }
        return y0;
    }

    /** The fast exponential algorithm is used to encrypt and decrypt files
    * @param x (char to be encrypted/decrypted)
    * @param exponent (either e from pk for encryption or d from sk for decryption
    * @param m (our n from the keypairs)
    * @return h
     * */
    public BigInteger fastExponentialAlgorithm(BigInteger x, BigInteger exponent, BigInteger m){
        String binaryRep = exponent.toString(2);
        BigInteger h = BigInteger.ONE;
        BigInteger k = x;
        for (int i = binaryRep.length()-1; i >= 0; i--){
            if(binaryRep.charAt(i) == '1'){
                h = h.multiply(k).mod(m);
            }
            k = k.pow(2).mod(m);
        }
        return h;
    }
}
