package computer.roohoo.projectoa.user

import org.springframework.data.repository.CrudRepository

interface SiteUsersRepository : CrudRepository<SiteUser, Int> {
    fun findByUserName(name: String): SiteUser
//    fun findByUserId(id: Int):SiteUser
}