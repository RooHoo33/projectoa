package computer.roohoo.projectoa.User

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "user_and_id")
data class SiteUser(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val userId: Int = 0,

        @get: NotBlank
        val userName: String = "",

        @get: NotBlank
        val firstName: String = "",
        @get: NotBlank
        var password: String = "",
        @get: NotBlank
        val lastName: String = "",

        @get: NotNull
        val kappaSigma: Int = 0,

        @get: NotNull
        val big: Int = 0,
        @get: NotNull
        val brother: Boolean = false


)