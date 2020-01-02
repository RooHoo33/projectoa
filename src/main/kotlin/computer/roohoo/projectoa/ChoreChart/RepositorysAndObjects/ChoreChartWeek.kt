package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "chore_chart_weeks")
data class ChoreChartWeek(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int = 0,

        @NotNull
        val week:String = ""
)