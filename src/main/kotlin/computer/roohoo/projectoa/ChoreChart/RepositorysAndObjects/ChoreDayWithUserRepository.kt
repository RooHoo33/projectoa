package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import org.springframework.data.repository.CrudRepository

interface ChoreDayWithUserRepository : CrudRepository<ChoreDayWithUser, Int>{
    fun findByChoreChartId(id: Int): ChoreDayWithUser
}