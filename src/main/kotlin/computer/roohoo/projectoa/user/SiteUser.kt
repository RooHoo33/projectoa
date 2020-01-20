package computer.roohoo.projectoa.user

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
        var kappaSigma: Int = 0,

        @get: NotNull
        val big: Int = 0,
        @get: NotNull
        val brother: Boolean = false,

        @get: NotNull
        val active: Boolean = true,

        @get: NotBlank
        val email:String = ""


)