package computer.roohoo.projectoa.choreChart

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreChartRepository
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreDayRepository
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.UserPreference
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.UserPreferenceRepository
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.ChoreAndWeek
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.ChoreAndWeekRepository
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.DayAndWeekRepository
import computer.roohoo.projectoa.user.SiteUsersRepository
import org.springframework.stereotype.Service

@Service
class ChoreChartService(val siteUser: SiteUsersRepository,
                        val choreChartRepository: ChoreChartRepository,
                        val choreAndWeekRepository: ChoreAndWeekRepository,
                        val dayAndWeekRepository: DayAndWeekRepository,
                        val userPreferenceRepository: UserPreferenceRepository) {


    fun getChoreChartForms(week: String){

        val choreChart = choreChartRepository.findByWeek(week)
        val choreAndWeek = choreAndWeekRepository.findByWeek(week)
        val dayAndWeek = dayAndWeekRepository.findByWeek(week)


    }


    /**
     * Finds all prefs for a user then sorts in into a map with the key being the week
     *
     * @param userId the users userId
     */
    fun getAllUserPrefs(userId: Int): MutableMap<String, MutableList<UserPreference>> {
        val allUserPrefs = userPreferenceRepository.findByUserId(userId)

        val sortedUserPrefs = mutableMapOf<String, MutableList<UserPreference>>()

        allUserPrefs.forEach{
            if (sortedUserPrefs.containsKey(it.week)){
                sortedUserPrefs[it.week]?.add(it)
            }else{
                sortedUserPrefs[it.week] = mutableListOf(it)
            }
        }

        return sortedUserPrefs
    }


}