package computer.roohoo.projectoa.User

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "auth")
data class UserAuth(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val auth_key: Int = 0,

        @get: NotBlank
        val auth_type: String = "",

        @get: NotNull
        val userId: Int = 0

        )