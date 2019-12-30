package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "user_preference")
data class UserPreference(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val preferenceId: Int = 0,

        @get: NotBlank
        val week: String = "",

        @get: NotNull
        val day_id: Int = 0,

        @get: NotNull
        val choreId: Int = 0,

        @get: NotNull
        val preferenceRanking: Int = 0,

        @get: NotNull
        val userId:Int = 0
)