package computer.roohoo.projectoa.choreChart.repositorysAndObjects

import org.springframework.data.repository.CrudRepository

interface ChoreChartRepository : CrudRepository<ChoreChart, String>{
    fun findByActive(active: Boolean): List<ChoreChart>
}