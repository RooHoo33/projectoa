package computer.roohoo.projectoa.security

import computer.roohoo.projectoa.user.UserAuthDatabaseDetails
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
class SecuirityController(private val authenticationManager: AuthenticationManager, private val userAuthDatabaseDetails: UserAuthDatabaseDetails, private val jwtUtil: JwtUtil) {

    val logger = LoggerFactory.getLogger(this::class.java)!!


    @PostMapping("/authenticate")
    fun createAuthToken(@RequestBody authRequest: AuthRequest): ResponseEntity<Any> {

        logger.debug(authRequest.username)
        logger.debug(authRequest.password)

        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password))

        val userDetails = userAuthDatabaseDetails.loadUserByUsername(authRequest.username)

        val jwt = jwtUtil.generateToken(userDetails)

        return ResponseEntity.ok(AuthResponse(jwt))

    }


}