package computer.roohoo.projectoa.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwt
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
class JwtUtil {
    val secretKey: String = "secretKey"


    fun extractUsername(token: String): String {
        return extractAllClaims(token).subject
    }

    fun extractExpiration(token: String): Date? {
        return extractAllClaims(token).expiration
    }

    private fun isTokenExpired(token: String): Boolean? {
        return extractExpiration(token)?.before(Date())
    }


//    fun extractClaim(token:String, claimsResolver: Function<Claims, Any>): Any{
//        val claims = extractAllClaims(token)
//        return claimsResolver.apply(claims)
//    }

    fun extractAllClaims(token: String): Claims{
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body
    }
    fun generateToken(userDetails:UserDetails): String{
        val claims = mutableMapOf<String, Any>()
        return createToken(claims, userDetails.username)
    }

    private fun createToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis())).setExpiration(Date(System.currentTimeMillis() + 100 * 60 * 60 * 10)).signWith(SignatureAlgorithm.HS256, secretKey).compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username  = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)!!
    }

}