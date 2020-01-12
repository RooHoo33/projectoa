package computer.roohoo.projectoa.committee

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "committee")
data class CommitteeMember(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val committeeEntryId: Int = 0,

        @get: NotBlank
        val committeeName: String = "",

        @get: NotNull
        val userId: Int = 0


)