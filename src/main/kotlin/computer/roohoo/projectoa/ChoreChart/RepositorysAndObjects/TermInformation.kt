package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import org.springframework.data.jpa.repository.Temporal
import java.time.LocalDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "chore_chart_term_information")
data class TermInformation(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int = 0,

        @NotNull
        @Temporal(TemporalType.DATE)
        val termStart: LocalDate = LocalDate.MIN,

        @NotNull
        @Temporal(TemporalType.DATE)
        val termEnd: LocalDate = LocalDate.MIN,

        @NotNull
        val active: Boolean = false,

        @NotNull
        val population: Int = 0,

        @NotNull
        val brotherChoreAmount: Int = 0,

        @NotNull
        val whiteTeamChoreAmount: Int = 0

) {
}