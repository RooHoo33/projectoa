package computer.roohoo.projectoa.user

import org.springframework.data.repository.CrudRepository

interface UserAuthRepository : CrudRepository<UserAuth, Int> {
    fun findAllByUserId(id: Int): List<UserAuth>
//    fun findByAuth_key(id: Int):UserAuth
}