package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "chore_charts")
data class ChoreChart(
        @Id
        @NotBlank
        val week: String = "",

        @NotNull
        val active: Boolean = false
)