package computer.roohoo.projectoa.choreChart

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.*
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.ChoreAndWeek
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.ChoreAndWeekRepository
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.DayAndWeek
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.DayAndWeekRepository
import computer.roohoo.projectoa.error.ErrorMessage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/chorechart")
public class ChoreChartController(private val userPreferenceRepository: UserPreferenceRepository,
                                  private val choreChartRepository: ChoreChartRepository,
                                  private val choreChoreRepository: ChoreChoreRepository,
                                  private val choreDayRepository: ChoreDayRepository,
                                  private val choreDayWithUserRepository: ChoreDayWithUserRepository,
                                  private val choreAndWeekRepository: ChoreAndWeekRepository,
                                  private val dayAndWeekRepository: DayAndWeekRepository) {

    val logger = LoggerFactory.getLogger(this.javaClass.name)!!

    @GetMapping("/create-chore-day")
    fun getCreateChoreDay(model: Model): String {

        model.addAttribute("choreDay", ChoreDay())
        return "chore-chart/create-chore-day"

    }

    @PostMapping("/create-chore-day")
    fun createChoreDay(choreDay: ChoreDay, model: Model): String {
        choreDayRepository.save(choreDay)
        return "chore-chart/create-chore-day"
    }

    @GetMapping("/create-chore-chore")
    fun getCreateChoreChore(model: Model): String {

        model.addAttribute("choreChore", ChoreDay())
        return "chore-chart/create-chore-chore"

    }

    @PostMapping("/create-chore-chore")
    fun createChoreChore(choreChore: ChoreChore, model: Model): String {
        choreChoreRepository.save(choreChore)
        return "chore-chart/create-chore-chore"
    }

    @GetMapping("/create-chore-chart-form")
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

    @PostMapping("/create-chore-chart-form/create")
    fun createChoreChartFormPost(choresAndDays: ChoresAndDays, model: Model): String {

        val errors = mutableListOf<ErrorMessage>()
        if (choresAndDays.weekNumber.isEmpty()) {
            errors.add(ErrorMessage("Please Select a Week"))
        }

        if (choresAndDays.chores.isEmpty()) {
            errors.add(ErrorMessage("Please Select At Least One Chore"))
        }

        if (choresAndDays.days.isEmpty()) {
            errors.add(ErrorMessage("Please Select At Least One Day"))
        }

        if (errors.isNotEmpty()) {
            model.addAttribute("errorMessages", errors)

            return createChoreChartForm(model)
        }

        choreAndWeekRepository.findByWeek(week = choresAndDays.weekNumber).forEach {
            if (choresAndDays.chores.none { day -> day.choreChoreId == it.choreId }) {
                logger.debug("Deleting chore: $it")
                choreAndWeekRepository.delete(it)
            }
        }

        for (it in choresAndDays.chores) {
            val oldChores = choreAndWeekRepository.findByWeekAndChoreId(week = choresAndDays.weekNumber, choreId = it.choreChoreId)
            if (oldChores.isEmpty()) {
                logger.debug("New Save, Chore Id: ${it.choreChoreId} exists for the week of: ${choresAndDays.weekNumber}")
                choreAndWeekRepository.save(ChoreAndWeek(week = choresAndDays.weekNumber, choreId = it.choreChoreId))
            } else if (oldChores.size != 1) {
                logger.error("Number of chores returned from a database call is more than one, $oldChores")

            } else if (!oldChores.isEmpty()) {

                logger.debug("Existing Chore, Day Id: ${it.choreChoreId} exists for the week of: ${choresAndDays.weekNumber}")

            }
        }

        dayAndWeekRepository.findByWeek(week = choresAndDays.weekNumber).forEach {
            if (choresAndDays.days.none { day -> day.choreDayId == it.dayId }) {
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