package computer.roohoo.projectoa.user

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*

@Service
@Component
class UserAuthDatabaseDetails : UserDetailsService{
    @Autowired
    private val repository: SiteUsersRepository? = null

    @Autowired
    private val userAuthRepository:UserAuthRepository? = null

    val logger = LoggerFactory.getLogger(UserAuthDatabaseDetails::class.java)!!

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = repository?.findByUserName(username) ?: throw UsernameNotFoundException("user not found")

        val userAuths = userAuthRepository!!.findAllByUserId(user.userId)

        val auths = mutableListOf<SimpleGrantedAuthority>()

        userAuths.forEach { it ->
            auths.add(SimpleGrantedAuthority(it.auth_type))
        }
        logger.debug(user.toString())
        val userSpring = User(user.userName, user.password, auths)
        logger.debug(userSpring.toString())
        return userSpring
    }
}