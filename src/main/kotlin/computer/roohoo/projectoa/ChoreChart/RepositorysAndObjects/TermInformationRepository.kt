package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import org.springframework.data.repository.CrudRepository

interface TermInformationRepository : CrudRepository<TermInformation, Int> {
    fun findByActive(active: Boolean):TermInformation
}