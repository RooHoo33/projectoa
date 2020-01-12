package computer.roohoo.projectoa.choreChart.converter

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreDay
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreDayRepository
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
        return choreDayRepository?.findById(id.toInt())?.get()
    }
}