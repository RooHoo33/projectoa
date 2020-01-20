package computer.roohoo.projectoa.schedule

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreChoreRepository
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreDayRepository
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.ChoreDayUserWithWeekReporistory
import computer.roohoo.projectoa.user.SiteUsersRepository
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*



@Component
class ChoreChartScheduler(private val choreDayUserWithWeekReporistory: ChoreDayUserWithWeekReporistory,
                          private val choreDayRepository: ChoreDayRepository,
                          private val choreChoreRepository: ChoreChoreRepository,
                          private val siteUsersRepository: SiteUsersRepository) {

    val logger = LoggerFactory.getLogger(this.javaClass.name)!!

    val daysOfWeek = mutableMapOf(1 to "Sunday",
            2 to "Monday",
            3 to "Tuesday",
            4 to "Wednesday",
            5 to "Thursday",
            6 to "Friday",
            7 to "Saturday")

        @Scheduled(cron = "0 0 6 * * 1-5")
//    @Scheduled(cron = "0 * * * * ?")
    fun notifyUsersOfChores() {

        val subject = "Chore For Today"


        val calendar: Calendar = GregorianCalendar()
        val currentTime = Date()
        calendar.time = currentTime;
//        calendar.add(Calendar.DAY_OF_MONTH, 1)

        val currentDay = daysOfWeek[calendar.get(Calendar.DAY_OF_WEEK)]
        logger.debug("Current Dat: $currentDay")

        val day = this.choreDayRepository.findByName(currentDay!!)

        val currentWeek = calendar.get(Calendar.YEAR).toString() + "-W" + calendar.get(Calendar.WEEK_OF_YEAR).toString().padStart(2, '0')

        val choresAndDayForTheCurrentDay = this.choreDayUserWithWeekReporistory.findByWeekAndDayId(week = currentWeek, dayId = day.id)

        choresAndDayForTheCurrentDay.forEach {

            val user = this.siteUsersRepository.findById(it.userId)
            val choreChore = this.choreChoreRepository.findById(it.choreId)
            if (user.isPresent && choreChore.isPresent) {


                val message = user.get().firstName + ", you are scheduled to have a chore today. The chore you have to do today is " + choreChore.get().name + "."


                val jsonData = mapOf("subject" to subject, "message" to message, "recipient" to user.get().email)
                logger.debug("Sending Email, Data: ${jsonData.toString()}")
//                Fuel.post("http://localhost:4444/sendemail").jsonBody(ObjectMapper().writeValueAsString(jsonData)).also { println(it) }.response { result -> println(result)}
                Fuel.post("http://192.168.1.202:4444/sendemail").jsonBody(ObjectMapper().writeValueAsString(jsonData)).also { println(it) }.response { result -> println(result)}
            }

        }






        logger.debug("hello")
    }
}