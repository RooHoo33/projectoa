package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity(name = "chore_chore")
data class ChoreChore(

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int = 0,

        @NotBlank
        val name: String = ""
)