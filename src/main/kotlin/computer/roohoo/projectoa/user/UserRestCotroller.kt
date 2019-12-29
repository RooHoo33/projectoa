package computer.roohoo.projectoa.user

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/users")
public class UserRestCotroller(private val siteUsersRepository: SiteUsersRepository){

    @PreAuthorize("hasRole('USER')")
    fun getUsers(): MutableIterable<SiteUser> {
        return siteUsersRepository.findAll()
    }
}