package computer.roohoo.projectoa.User

import org.springframework.data.repository.CrudRepository

interface SiteUsersRepository : CrudRepository<SiteUser, Int> {
    fun findByUserName(name: String): SiteUser
//    fun findByUserId(id: Int):SiteUser
}