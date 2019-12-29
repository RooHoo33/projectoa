package computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "day_and_week")
data class DayAndWeek(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int = 0,

        @NotNull
        val day: Int = 0,

        @NotBlank
        val week: String = ""
)