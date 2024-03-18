import java.math.BigInteger;

public class GenerateE {
    /**Generating a coprime to our calculated n
     *@param n (phiOfn)
     *@return result (as our e) */
    public BigInteger generateCoprime(BigInteger n){
        BigInteger result = new BigInteger("3");
        while (result.compareTo(n) >= 0 || !result.gcd(n).equals(BigInteger.ONE)){
            result = result.add(BigInteger.ONE);
        }
        return result;
    }
}
