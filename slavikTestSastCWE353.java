import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DataEncryptor {
    private static final String ALGORITHM = "AES";
    private static final String MODE = "AES/CBC/PKCS5Padding";
    private static final String HMAC_ALGORITHM = "HmacSHA256";

    private static final byte[] SECRET_KEY = "abcdefghijklmnop".getBytes();
    private static final byte[] IV = "1234567890abcdef".getBytes();

    public static void main(String[] args) {
        String originalData = "Hello, world!";
        
        // Encryption
        byte[] encryptedData = encrypt(originalData);
        
        // Decryption
        String decryptedData = decrypt(encryptedData);
        
        System.out.println("Original data: " + originalData);
        System.out.println("Decrypted data: " + decryptedData);
    }

    public static byte[] encrypt(String data) {
        try {
            // Generate a secret key from the provided key material
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY, ALGORITHM);
            
            // Initialize the cipher with the secret key and initialization vector (IV)
            Cipher cipher = Cipher.getInstance(MODE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(IV));
            
            // Encrypt the data
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            
            // Calculate the HMAC for integrity check
            Mac hmac = Mac.getInstance(HMAC_ALGORITHM);
            hmac.init(secretKeySpec);
            byte[] hmacBytes = hmac.doFinal(encryptedData);
            
            // Concatenate the HMAC with the encrypted data
            byte[] encryptedDataWithHmac = new byte[hmacBytes.length + encryptedData.length];
            System.arraycopy(hmacBytes, 0, encryptedDataWithHmac, 0, hmacBytes.length);
            System.arraycopy(encryptedData, 0, encryptedDataWithHmac, hmacBytes.length, encryptedData.length);
            
            return encryptedDataWithHmac;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(byte[] encryptedDataWithHmac) {
        try {
            // Extract the HMAC and encrypted data from the combined byte array
            int hmacLength = 32; // Assuming the HMAC is 32 bytes long
            byte[] hmacBytes = new byte[hmacLength];
            byte[] encryptedData = new byte[encryptedDataWithHmac.length - hmacLength];
            System.arraycopy(encryptedDataWithHmac, 0, hmacBytes, 0, hmacLength);
            System.arraycopy(encryptedDataWithHmac, hmacLength, encryptedData, 0, encryptedData.length);
            
            // Calculate the HMAC again for integrity check
            Mac hmac = Mac.getInstance(HMAC_ALGORITHM);
            hmac.init(new SecretKeySpec(SECRET_KEY, ALGORITHM));
            byte[] recalculatedHmacBytes = hmac.doFinal(encryptedData);
            
            // Verify the integrity by comparing the original
