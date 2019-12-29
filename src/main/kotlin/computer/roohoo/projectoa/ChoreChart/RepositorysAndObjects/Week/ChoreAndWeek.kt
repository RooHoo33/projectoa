package computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "chore_and_week")
data class ChoreAndWeek(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int = 0,

        @NotNull
        val choreId: Int = 0,

        @NotBlank
        val week: String = ""
)