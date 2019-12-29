package computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week

import org.springframework.data.repository.CrudRepository

interface ChoreAndWeekRepository : CrudRepository<ChoreAndWeek, Int>{
    fun findByWeek(week: String): List<ChoreAndWeek>
}