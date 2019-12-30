package computer.roohoo.projectoa.choreChart

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreChore
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreDay

data class ChoreForm(val active: Boolean,
                     val week: String,
                     val choreDays: MutableList<ChoreDay>,
                     val choreChores: MutableList<ChoreChore>)