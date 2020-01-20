package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import computer.roohoo.projectoa.choreChart.ChoreDayUserWithWeek
import org.springframework.data.repository.CrudRepository

interface ChoreDayUserWithWeekReporistory : CrudRepository<ChoreDayUserWithWeek, Int> {
    fun findByWeek(week:String): List<ChoreDayUserWithWeek>?

    fun findByWeekAndDayId(week: String, dayId: Int): List<ChoreDayUserWithWeek>

    fun deleteAllByWeek(week:String)
}