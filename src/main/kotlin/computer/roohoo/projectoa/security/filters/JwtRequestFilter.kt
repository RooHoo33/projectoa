package computer.roohoo.projectoa.security.filters

import computer.roohoo.projectoa.security.JwtUtil
import computer.roohoo.projectoa.user.UserAuthDatabaseDetails
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(val userAuthDatabaseDetails: UserAuthDatabaseDetails,
                       val jwtUtil: JwtUtil) : OncePerRequestFilter() {


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authHeader = request.getHeader("Authorization")
        val logger = LoggerFactory.getLogger(this::class.java)!!

        logger.debug("Here is the auth token: $authHeader")



        var username: String? = null
        var jwt: String? = null

        if (authHeader !=null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7)
            username = jwtUtil.extractUsername(jwt)

        }

        if (username != null && SecurityContextHolder.getContext().authentication == null && jwt != null){

            val userDetails = this.userAuthDatabaseDetails.loadUserByUsername(username)
            if (jwtUtil.validateToken(jwt, userDetails)){
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }

        }
        filterChain.doFilter(request,response)
    }
}