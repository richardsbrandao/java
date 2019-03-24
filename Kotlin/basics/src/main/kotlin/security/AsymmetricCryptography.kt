import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.lang.RuntimeException
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

fun main() {
    val token = create("richard")
    println(token)

    val keyFactory = KeyFactory.getInstance("RSA")
    val privateKey = getPrivateKeyByUsername("richard", keyFactory)
    val publicKey = getPublicKeyByUsername("richard", keyFactory)
    val algorithm = Algorithm.RSA512(publicKey, privateKey)

    val decodedJWT = JWT.require(algorithm).withIssuer("RICHARD_SECURITY")
            .build()

    val verify = decodedJWT.verify(token)
    println("""
        Token: $token
        Username: ${verify.claims["username"]?.asString()}
        ExpiresAt: ${verify.expiresAt}
    """)
}

fun create(username: String): String {
    val keyFactory = KeyFactory.getInstance("RSA")
    val privateKey = getPrivateKeyByUsername(username, keyFactory)
    val publicKey = getPublicKeyByUsername(username, keyFactory)
    val algorithm = Algorithm.RSA512(publicKey, privateKey)
    val expirationDatetime = LocalDateTime.now().plusDays(10)
    return JWT.create()
                .withIssuer("RICHARD_SECURITY")
                .withClaim("username", username)
                .withExpiresAt(Date.from(expirationDatetime.toInstant(ZoneOffset.UTC)))
                .sign(algorithm)
}


fun getPrivateKeyByUsername(username: String, keyFactory: KeyFactory): RSAPrivateKey {
    val privateKeyContent = "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"
            .replace("\n", "")
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")

    return try {
        val keySpecPKCS8 = PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent.toByteArray()))
        keyFactory.generatePrivate(keySpecPKCS8) as RSAPrivateKey
    } catch (e: Exception) {
        throw RuntimeException("Invalid private key", e)
    }
}

fun getPublicKeyByUsername(username: String, keyFactory: KeyFactory): RSAPublicKey {
    val publicKeyContent = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAvc+ijQ1atTn+i0Errj/QkwvlVNdtp+bS+M82BjZ4rR2JcmHGPGVWZjluGVPVR74tu8EW/ILeECp0u5edscRInEG4Mz4wLds710aBpOMnLF3zcJ3s3HknEpxxfSu16bja4gH/5I4g4AEuH7yiMaXeGJ0/14IrNA1iL1vkxfk403Mqq8QfJHrPqGlsOVl2pDxrWbsiObgxBYW4R6zHpEHhYbXbWl63XMi0LZD13ribWtL3VaVLx2GqL5SLZhVIWXw9BDj21GxORM+d7XbKx7rysn7dDGIp+48YwTZKT674bxKqydYkwl+e82qDTdwQBlIV41k6mMWvZkx9/LWwUEl+aW6xwveRJVpy10eiDJLAPgzhLTSf0Udh4I3DLWPHni5nrAJvi03/reSV+GZSCSb0lAPrtJ/D3ra/7aFZq/b3WyHIRiVXbrZt5Kke6SvsW5X3LUd4qj1GGP4km9bu/wi8zjgAhrvWKhf7QMWldK8b0zb6p1NvZdobw8EijtbLIXN8vLFZGPdEoKgaPScwfIU3T/Ly7ma+CDmHGEfamZYOg6IkSCbpdwfyhUxYSEjVHWuKSGOSYhvapNUWb2hr6bwEwyF59GrbLePpYZgnxh4RoBdJ/D2xfpo1cRplTwNGRPVO6935ieLWnlYhRW0UZ1vBsZoH6BN3Jk+XyOfps/OmnlECAwEAAQ=="
            .replace("\n", "")
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")

    return try {
        val keySpecX509 = X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent.toByteArray()))
        keyFactory.generatePublic(keySpecX509) as RSAPublicKey
    } catch (e: Exception) {
        throw RuntimeException("Invalid public key", e)
    }
}