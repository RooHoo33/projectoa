package computer.roohoo.projectoa.choreChart

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.*
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.ChoreAndWeek
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.ChoreAndWeekRepository
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.DayAndWeek
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.DayAndWeekRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
//@RequestMapping("/chorechart")
public class ChoreChartController(private val userPreferenceRepository: UserPreferenceRepository,
                                  private val choreChartRepository: ChoreChartRepository,
                                  private val choreChoreRepository: ChoreChoreRepository,
                                  private val choreDayRepository: ChoreDayRepository,
                                  private val choreDayWithUserRepository: ChoreDayWithUserRepository,
                                  private val choreAndWeekRepository: ChoreAndWeekRepository,
                                  private val dayAndWeekRepository: DayAndWeekRepository) {

    val logger = LoggerFactory.getLogger(this.javaClass.name)!!

    @GetMapping("/chorechart/create-chore-day")
    fun getCreateChoreDay(model: Model): String {

        model.addAttribute("choreDay", ChoreDay())
        return "chore-chart/create-chore-day"

    }

    @PostMapping("/chorechart/create-chore-day")
    fun createChoreDay(choreDay: ChoreDay, model: Model): String {
        choreDayRepository.save(choreDay)
        return "chore-chart/create-chore-day"
    }

    @GetMapping("/chorechart/create-chore-chore")
    fun getCreateChoreChore(model: Model): String {

        model.addAttribute("choreChore", ChoreDay())
        return "chore-chart/create-chore-chore"

    }

    @PostMapping("/chorechart/create-chore-chore")
    fun createChoreChore(choreChore: ChoreChore, model: Model): String {
        choreChoreRepository.save(choreChore)
        return "chore-chart/create-chore-chore"
    }

    @GetMapping("/chorechart/create-chore-chart-form")
    fun createChoreChartForm(model: Model): String {
        val days = choreDayRepository.findAll()
        val chores = choreChoreRepository.findAll()
        this.createDaysListString(days)
        model.addAttribute("choresAndDays", ChoresAndDays(days = days as MutableList<ChoreDay>, chores = chores as MutableList<ChoreChore>))
//        val map = mutableMapOf<String,List<String>>()
//        map["chores"] = this.createChoresListString(chores)
//        map["days"] = this.createDaysListString(days)
//        model.addAttribute("choresAndDays", TempClass(map))
//        model.addAttribute("testList", ArrayList<Any>())
        return "chore-chart/chore-chart-form"
    }

    fun createDaysListString(day: MutableIterable<ChoreDay>): MutableList<String> {
        val list = mutableListOf<String>()
        day.forEach {
            list.add(it.day)
        }
        return list
    }

    fun createChoresListString(day: MutableIterable<ChoreChore>): MutableList<String> {
        val list = mutableListOf<String>()
        day.forEach {
            list.add(it.choreName)
        }
        return list
    }

    fun createDaysAndBoolean(day: Iterable<Any>): MutableList<DayOrChoreAndBoolean> {
        val list = mutableListOf<DayOrChoreAndBoolean>()
        day.forEach {
            list.add(DayOrChoreAndBoolean(it))
        }
        return list
    }

    @PostMapping("/chorechart/create-chore-chart-form/create")
    fun createChoreChartFormPost(choresAndDays: ChoresAndDays, model: Model): String {
        if (choresAndDays.weekNumber.isEmpty() || choresAndDays.chores.isEmpty() ||choresAndDays.days.isEmpty()){
            logger.warn("User did not select a day, chores, or a week: $choresAndDays")
            //TODO add an error message and redirect to a page that includes a link to create-chore-chart-form
            return "error"

        }

        choresAndDays.chores.forEach {
            logger.debug("Chore: $it")
            choreAndWeekRepository.save(ChoreAndWeek(choreId = it.choreChoreId, week = choresAndDays.weekNumber))
        }
        dayAndWeekRepository.findByWeek(week = choresAndDays.weekNumber).forEach {
            if (choresAndDays.days.none { day -> day.choreDayId == it.dayId }){
                logger.debug("Deleting day: $it")
                dayAndWeekRepository.delete(it)
            }
        }

        for (it in choresAndDays.days) {
                val oldDays = dayAndWeekRepository.findByWeekAndAndDayId(week = choresAndDays.weekNumber, dayId = it.choreDayId)
                if (oldDays.isEmpty()) {
                    logger.debug("New Save, Day Id: ${it.choreDayId} exists for the week of: ${choresAndDays.weekNumber}")
                    dayAndWeekRepository.save(DayAndWeek(week = choresAndDays.weekNumber, dayId = it.choreDayId))
                } else if (oldDays.size != 1) {
                    logger.error("Number of days returned from a database call is more than one, $oldDays")

                } else if (!oldDays.isEmpty()) {

                        logger.debug("Existing day, Day Id: ${it.choreDayId} exists for the week of: ${choresAndDays.weekNumber}")

                }
        }

        logger.debug("Week: " + choresAndDays.weekNumber)

        return "home"

    }

}