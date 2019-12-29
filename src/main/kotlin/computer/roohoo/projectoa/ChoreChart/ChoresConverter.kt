package computer.roohoo.projectoa.choreChart

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreChore
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreChoreRepository
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreDayRepository
import computer.roohoo.projectoa.security.SecurityConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
public class ChoresConverter : Converter<String, ChoreChore> {

    @Autowired
    private val choreChoreRepository: ChoreChoreRepository? = null

//    val logger = LoggerFactory.getLogger(ChoresConverter::class.java)!!


    override fun convert(id: String): ChoreChore? {
//        logger.debug("This is the Chore id: $id")
        return choreChoreRepository?.findByChoreChoreId(id.toInt())
    }

}