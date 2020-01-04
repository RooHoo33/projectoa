package computer.roohoo.projectoa.choreChart

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.*
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.ChoreAndWeek
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.ChoreAndWeekRepository
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.DayAndWeek
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.DayAndWeekRepository
import computer.roohoo.projectoa.user.SiteUser
import computer.roohoo.projectoa.user.SiteUsersRepository
import org.slf4j.LoggerFactory
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@CrossOrigin(origins = arrayOf("http://localhost:3000"), maxAge = 3600)
@RestController
class ChoreChartRestController (private val userPreferenceRepository: UserPreferenceRepository,
                                private val choreChartRepository: ChoreChartRepository,
                                private val choreChoreRepository: ChoreChoreRepository,
                                private val choreDayRepository: ChoreDayRepository,
                                private val choreDayWithUserRepository: ChoreDayWithUserRepository,
                                private val siteUsersRepository: SiteUsersRepository,
                                private val termInformationRepository: TermInformationRepository,
                                private val choreAndWeekRepository: ChoreAndWeekRepository,
                                private val dayAndWeekRepository: DayAndWeekRepository,
                                private val choreChartWeekRepository: ChoreChartWeekRepository){

    val logger = LoggerFactory.getLogger(ChoreChartRestController::class.java)!!

    @GetMapping("/rest/userprefs")
    fun getUserPreferences(model: Model): MutableIterable<UserPreference> {
        return userPreferenceRepository.findAll()
    }

    @GetMapping("/rest/users")
    fun getAllUsers(): MutableIterable<SiteUser> {
        return siteUsersRepository.findAll()
    }

    @GetMapping("/rest/chorecharts")
    fun getChoreCharts(model: Model): MutableIterable<ChoreChart> {
        return choreChartRepository.findAll()
    }

    @GetMapping("/rest/chorechores")
    fun getChoreChores(model: Model): MutableIterable<ChoreChore> {
        return choreChoreRepository.findAll()
    }

    @GetMapping("/rest/choredays")
    fun getChoreDays(model: Model): MutableIterable<ChoreDay> {
        return choreDayRepository.findAll()
    }

    @GetMapping("/rest/choredaywithusers")
    fun getChoreDayWithUsers(model: Model): MutableIterable<ChoreDayWithUser> {
        return choreDayWithUserRepository.findAll()
    }

    @GetMapping("/rest/chorechart/admin/terminformations")
    fun getAllTermInformations(): MutableIterable<TermInformation> {
        return termInformationRepository.findAll()
    }

    @PostMapping("/rest/chorechart/admin/termInformation")
    fun createTermInformation(@RequestBody termInformation: TermInformation){

        logger.debug("This si the term information: ${termInformation.toString()}")
        val allTermInformations = this.termInformationRepository.findAll().filter { it ->
            termInformation.id != it.id && (
            (termInformation.termEnd > it.termStart && termInformation.termEnd < it.termEnd )
                    || (termInformation.termStart > it.termStart && termInformation.termStart < it.termEnd)
                    || (termInformation.termStart == it.termStart)
                    || termInformation.termEnd == it.termEnd)

        }

        if (termInformation.termEnd < termInformation.termStart || allTermInformations.isNotEmpty()){
            throw Throwable("Term overlaps a previous term: ${termInformation.toString()}")

        }



        logger.debug(allTermInformations.size.toString())

        this.termInformationRepository.save(termInformation)
    }

    @GetMapping("/rest/chorechart/admin/termInformation/active")
    fun getActiveTermInformation(): TermInformation {
        return this.termInformationRepository.findByActive(true)
    }

    @GetMapping("/rest/chorechart/admin/termInformation")
    fun getActiveTermById(@RequestParam id:Int): Optional<TermInformation> {
        return this.termInformationRepository.findById(id)
    }

    @GetMapping("/rest/chorechart/admin/choredays")
    fun getAllChoreDays(): MutableIterable<ChoreDay> {
        return this.choreDayRepository.findAll()
    }

    @PostMapping("/rest/chorechart/admin/choredays")
    fun getAllChoreDays(@RequestBody choreDays: List<ChoreDay>) {
        logger.debug(choreDays.toString())
        this.choreDayRepository.saveAll(choreDays)
    }

    @GetMapping("/rest/chorechart/admin/chorechores")
    fun getAllChoreChores(): MutableIterable<ChoreChore> {
        return this.choreChoreRepository.findAll()
    }

    @PostMapping("/rest/chorechart/admin/chorechores")
    fun getAllChoreChores(@RequestBody choreChores: List<ChoreChore>) {
        logger.debug(choreChores.toString())
        this.choreChoreRepository.saveAll(choreChores)
    }

    @PostMapping("/rest/chorechart/admin/choreForms")
    fun saveChoreChartForm(@RequestBody choresAndDaysList: List<ChoresAndDays>){



        choresAndDaysList.forEach { choresAndDays ->

            if (this.choreChartWeekRepository.findByWeek(choresAndDays.weekNumber) == null){
                choreChartWeekRepository.save(ChoreChartWeek(week = choresAndDays.weekNumber))
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
        }
    }

    @GetMapping("/rest/chorechart/admin/choreForms")
    fun getChoreForms(): MutableList<ChoresAndDays> {

        val choresAndDaysList = mutableListOf<ChoresAndDays>()


        this.choreChartWeekRepository.findAll().forEach{

            val choreChores = mutableListOf<ChoreChore>()

            this.choreAndWeekRepository.findByWeek(it.week).forEach { choreWeek ->
                choreChores.add(this.choreChoreRepository.findByChoreChoreId(choreWeek.choreId))
            }

            val choreDays = mutableListOf<ChoreDay>()

            this.dayAndWeekRepository.findByWeek(it.week).forEach{ choreDay ->
                choreDays.add(this.choreDayRepository.findByChoreDayId(choreDay.dayId))
            }
            choresAndDaysList.add(ChoresAndDays(chores = choreChores,
                    days = choreDays,
                    weekNumber = it.week))
        }
        return choresAndDaysList


    }

}