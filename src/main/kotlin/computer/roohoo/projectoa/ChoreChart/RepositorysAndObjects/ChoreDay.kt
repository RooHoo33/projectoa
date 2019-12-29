package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity(name = "chore_day")
data class ChoreDay(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val choreDayId: Int = 0,

        @NotBlank
        val day:String = ""
)