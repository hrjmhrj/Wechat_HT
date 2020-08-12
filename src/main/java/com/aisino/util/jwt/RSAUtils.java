package com.aisino.util.jwt;

import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class RSAUtils {

    /**
     * 私钥
     */
    protected final static  String  PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANlWPMMAKWJlo4OCgS3a14yNTkqwzVUVOFvB/MelSJUFH1N71jw/lQYD4F6F8pXcjmDCq058Hd2r7ESOUkvBKVNEQh1Xty7a5oEpzZMsckn4ZQXQDjqevg/1us7mT9LkZYc2xss2xzMoHq7FbaNkHxzx5bdeHCv0L142IBGOsqpdAgMBAAECgYBQKePdlTiuh4jJYCCftkGdIR3IpPAHuvz/s0y2ovtjo/ELDPlXXahtAI8IrmVs615EyGkBWPx3IAvUuZ4XPRctZkIEXHWL46nPVpJ5faDcvPSkOZJam2yQPanmMHHAE+VDPVs3qmyJNgKUB41tSxrP/IK9NgDHvU/RreViqKyEjQJBAPLhsEhK5HhzAJ4k14GmwDaXvMirjrGVls8oX6LtX0a6EmSgZi4N9gIZ3VkYT6n2CRYvCaFY1SbjV6BQY3AXc8MCQQDlE1fSVBEZabIKaq8Fbxdcu//8L3G8IjfI5inO5vZ+szT/PR8L4Jti1KnVzCxg5LoEX22KoObsu9aoCVlldCdfAkBXQh/sCQQGBgMNzdyi1r6tnlwTmBb+0+hHapDZXwBgz462Gi47k4ZzgMlWLfbI7/yZvLJwBhMmEn0zuk3PQo2zAkBMpz5Z6TuXj2C+T5ilbtkYdOhh0NZ0J9cW6L3QuER/fSQczK0Y4l3comD6Y8nt2lvMnsoe+1yKlwhrTkWVx1ptAkBjQewfH7wol/VnT9sIrKYZSyF+pHTDtZR2c3kFNCzKCb/i3+Q4L38pf6Aam9V4dbUpaFd5Z5PfuLLKPO0sXwM7";


    /**
     * 解密数据
     * @param string 需要解密的字符串
     * @return 破解之后的字符串
     */
    public static String decryptBase64(String string) {
        // decodeBase64():将Base64数据解码为"八位字节”数据
        return new String(decrypt(Base64.decodeBase64(string)));
    }

    private static byte[] decrypt(byte[] byteArray) {
        try {
            Provider provider = new org.bouncycastle.jce.provider.BouncyCastleProvider();
            Security.addProvider(provider);
            // Cipher: 提供加密和解密功能的实例
            // transformation: "algorithm/mode/padding"
            Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", provider);

            byte[] byteKey = Base64.decodeBase64(PRIVATE_KEY);
            PKCS8EncodedKeySpec x509EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(x509EncodedKeySpec);
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // doFinal(): 加密或者解密数据
            byte[] plainText = cipher.doFinal(byteArray);
            return plainText;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
