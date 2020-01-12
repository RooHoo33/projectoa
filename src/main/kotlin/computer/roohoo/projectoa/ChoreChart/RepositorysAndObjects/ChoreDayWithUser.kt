package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity(name = "chore_day_with_user")
data class ChoreDayWithUser(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int = 0,

        @NotNull
        val choreChartId:Int = 0,

        @NotNull
        val userId: Int = 0,

        @NotNull
        val choreId: Int = 0,

        @NotNull
        val dayId: Int = 0
)