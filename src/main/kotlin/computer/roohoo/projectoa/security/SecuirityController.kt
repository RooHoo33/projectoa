package computer.roohoo.projectoa.security

import computer.roohoo.projectoa.user.SiteUser
import computer.roohoo.projectoa.user.SiteUsersRepository
import computer.roohoo.projectoa.user.UserAuthDatabaseDetails
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.bind.annotation.*

@RestController
class SecuirityController(private val authenticationManager: AuthenticationManager, private val userAuthDatabaseDetails: UserAuthDatabaseDetails, private val jwtUtil: JwtUtil, private val siteUsersRepository: SiteUsersRepository) {

    val logger = LoggerFactory.getLogger(this::class.java)!!


    @PostMapping("/rest/authenticate")
    fun createAuthToken(@RequestBody authRequest: AuthRequest): ResponseEntity<Any> {

        logger.debug(authRequest.username)
        logger.debug(authRequest.password)
        logger.debug(UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password).toString())

        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password))

        val userDetails = userAuthDatabaseDetails.loadUserByUsername(authRequest.username)

        val jwt = jwtUtil.generateToken(userDetails)

        return ResponseEntity.ok(AuthResponse(jwt))

    }

    @PostMapping("/rest/authenticate/renew")
    fun renewAuthToken(@RequestBody authResponse: AuthResponse): AuthResponse {

        logger.debug("Old JWT: ${authResponse.jwt}")

        val username = jwtUtil.extractUsername(authResponse.jwt)

        val userDetails = this.userAuthDatabaseDetails.loadUserByUsername(username)
        if (jwtUtil.validateToken(authResponse.jwt, userDetails)){
            val newJwt = jwtUtil.generateToken(userDetails)
            logger.debug("New JWT: $newJwt")
            return AuthResponse(newJwt)
        } else{
            throw Throwable("Bad json token, username: $username, jwt: ${authResponse.jwt}")
        }

    }

    @PostMapping("/rest/security/createuser")
    fun createSiteUser(@RequestBody siteUser: SiteUser): MutableMap<String, Boolean> {

        if (siteUsersRepository.findByUserName(siteUser.userName) != null){
            return mutableMapOf<String, Boolean>("usernameTaken" to true)
        }

        logger.debug("New User: $siteUser")

        val passwordEncoder = BCryptPasswordEncoder()
        val hashedPassword = passwordEncoder.encode(siteUser.password)
        siteUser.password = hashedPassword

        this.siteUsersRepository.save(siteUser)
        return mutableMapOf("usernameTaken" to false)

    }


}