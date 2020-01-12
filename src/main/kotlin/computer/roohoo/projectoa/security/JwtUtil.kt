package computer.roohoo.projectoa.security

import computer.roohoo.projectoa.user.SiteUsersRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwt
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
class JwtUtil(val siteUsersRepository: SiteUsersRepository) {
    val secretKey: String = "secretKey"

    val logger = LoggerFactory.getLogger(this::class.java)!!


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

        logger.debug("Is On Commitee?: ${userDetails.authorities.contains(SimpleGrantedAuthority("ManComChairmen"))}")
        val claims = mutableMapOf("userId" to (siteUsersRepository.findByUserName(userDetails.username))!!.userId, "matComChairmen" to userDetails.authorities.contains(SimpleGrantedAuthority("ManComChairmen")))

        return createToken(claims, userDetails.username)
    }

    private fun createToken(claims: Map<String, Any>, subject: String): String {

        //use this for 20 second tokens
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis())).setExpiration(Date(System.currentTimeMillis() + 20000)).signWith(SignatureAlgorithm.HS256, secretKey).compact()

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis())).setExpiration(Date(System.currentTimeMillis() + 100 * 60 * 60 * 10)).signWith(SignatureAlgorithm.HS256, secretKey).compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username  = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)!!
    }

}