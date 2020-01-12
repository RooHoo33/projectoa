package computer.roohoo.projectoa.user

import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController(private val siteUserRepository: SiteUsersRepository, private val userAuthRepository: UserAuthRepository) {
    val logger = LoggerFactory.getLogger(UserController::class.java)!!

    @GetMapping("/create-user")
    fun createNewUserPage(model: Model): String {
        model.addAttribute("siteUser", CreateSiteUser())
        return "site-users/create-user"
    }

    @PostMapping("/create-user")
    fun createNewUser(siteUser:CreateSiteUser, model: Model): String {
        val passwordEncoder = BCryptPasswordEncoder()
        val hashedPassword = passwordEncoder.encode(siteUser.password)

        logger.debug(siteUser.toString())

        val siteUserHere = SiteUser(firstName = siteUser.first_name!!, brother = siteUser.kappa_sigma != 0, kappaSigma = siteUser.kappa_sigma!!, lastName = siteUser.last_name!!, password = hashedPassword, userName = siteUser.user_name!!, big = siteUser.big!!)
        logger.warn(siteUserHere.toString())
        siteUserRepository.save(siteUserHere)
        val user = siteUserRepository.findByUserName(siteUser.user_name!!)
        userAuthRepository.save(UserAuth(userId = user!!.userId, auth_type = "user"))
        return "home"
    }


}
