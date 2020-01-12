package computer.roohoo.projectoa.schedule

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ChoreChartScheduler {

    val logger = LoggerFactory.getLogger(this.javaClass.name)!!

    @Scheduled(cron = "0 * * * * ?")
    fun createChoreChart(){
//        logger.debug("We are in the cron job")
    }
}