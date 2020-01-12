package computer.roohoo.projectoa.choreChart

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreChore
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreDay
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.ChoreAndWeek
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.DayAndWeek

data class ChoresAndDays(var days: MutableList<ChoreDay>, var chores: MutableList<ChoreChore>, var weekNumber: String = ""){

}