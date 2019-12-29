package computer.roohoo.projectoa.choreChart

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreChoreRepository
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreDay
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreDayRepository
import computer.roohoo.projectoa.user.SiteUsersRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
public class DaysConverter : Converter<String, ChoreDay>{

    @Autowired
    private val choreDayRepository: ChoreDayRepository? = null

//    val logger = LoggerFactory.getLogger(DaysConverter::class.java)!!


    override fun convert(id: String): ChoreDay? {

//        logger.debug("This is the Day id: $id")
        return choreDayRepository?.findByChoreDayId(id.toInt())
    }
}