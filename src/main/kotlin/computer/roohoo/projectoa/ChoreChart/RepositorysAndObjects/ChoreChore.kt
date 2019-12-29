package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity(name = "chore_chore")
data class ChoreChore(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val choreChoreId: Int = 0,

        @NotBlank
        val choreName: String = ""
)