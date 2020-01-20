package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import org.springframework.data.repository.CrudRepository

interface ChoreDayRepository : CrudRepository<ChoreDay, Int>{
    fun findByName(name:String): ChoreDay
}