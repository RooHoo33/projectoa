package computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week

import org.springframework.data.repository.CrudRepository

interface DayAndWeekRepository : CrudRepository<DayAndWeek, Int> {
    fun findByWeek(week: String): List<DayAndWeek>

    fun findByWeekAndAndDayId(week: String, dayId: Int): List<DayAndWeek>
}