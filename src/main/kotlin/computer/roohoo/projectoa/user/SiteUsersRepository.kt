package computer.roohoo.projectoa.user

import org.springframework.data.repository.CrudRepository

interface SiteUsersRepository : CrudRepository<SiteUser, Int> {
    fun findByUserName(name: String): SiteUser
    fun findByActive(active: Boolean = true): List<SiteUser>
//    fun findByUserId(id: Int):SiteUser
}