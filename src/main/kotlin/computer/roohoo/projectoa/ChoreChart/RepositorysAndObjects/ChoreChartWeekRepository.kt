package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import org.springframework.data.repository.CrudRepository

interface ChoreChartWeekRepository : CrudRepository<ChoreChartWeek, Int> {
    fun findByWeek(week:String): ChoreChartWeek?
}