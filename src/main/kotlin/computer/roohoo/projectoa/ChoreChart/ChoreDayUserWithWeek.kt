package computer.roohoo.projectoa.choreChart

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreChore
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "chore_day_user")
data class ChoreDayUserWithWeek(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int = 0,

        @NotBlank
        val week:String = "",

//        @OneToOne(cascade = [CascadeType.ALL])
//        @JoinColumn(name= "choreId", referencedColumnName = "id")
//        val choreChore:ChoreChore = ChoreChore()

//        @OneToOne(cascade = [CascadeType.ALL])
//        @JoinTable(name= "chore_chore",
//                joinColumns = [JoinColumn(name = "route_id", referencedColumnName = "uid")],
//                inverseJoinColumns = [JoinColumn(name = "athlete_id", referencedColumnName = "uid")])
        @NotNull
        val choreId: Int = 0,

        @NotNull
        val dayId: Int = 0,

        @NotNull
        val userId: Int = 0



        ){}