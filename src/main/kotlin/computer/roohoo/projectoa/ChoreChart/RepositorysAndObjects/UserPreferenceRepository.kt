package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import org.springframework.data.repository.CrudRepository

interface UserPreferenceRepository : CrudRepository<UserPreference, Int>{
    fun findByWeek(week:String):List<UserPreference>
    fun findByUserId(userId: Int):List<UserPreference>
}