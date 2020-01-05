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
import java.util.*
import kotlin.math.log

@CrossOrigin(origins = arrayOf("http://localhost:3000"), maxAge = 3600)
@RestController
@RequestMapping("/rest")
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

    @GetMapping("/userprefs")
    fun getUserPreferences(model: Model): MutableIterable<UserPreference> {
        return userPreferenceRepository.findAll()
    }

    @GetMapping("/users")
    fun getAllUsers(): MutableIterable<SiteUser> {
        return siteUsersRepository.findAll()
    }

    @GetMapping("/chorecharts")
    fun getChoreCharts(model: Model): MutableIterable<ChoreChart> {
        return choreChartRepository.findAll()
    }

    @GetMapping("/chorechores")
    fun getChoreChores(model: Model): MutableIterable<ChoreChore> {
        return choreChoreRepository.findAll()
    }

    @GetMapping("/choredays")
    fun getChoreDays(model: Model): MutableIterable<ChoreDay> {
        return choreDayRepository.findAll()
    }

    @GetMapping("/choredaywithusers")
    fun getChoreDayWithUsers(model: Model): MutableIterable<ChoreDayWithUser> {
        return choreDayWithUserRepository.findAll()
    }

    @GetMapping("/chorechart/admin/terminformations")
    fun getAllTermInformations(): MutableIterable<TermInformation> {
        logger.debug("WE HERE")
        return termInformationRepository.findAll()
    }

    @GetMapping("/chorechart/admin/terminformation/delete")
    fun getDeleteActiveTermById(@RequestParam id:Int): Optional<TermInformation> {
        this.termInformationRepository.deleteById(id)

        return this.termInformationRepository.findById(id)
    }
    @GetMapping("/chorechart/admin/chorechore/delete")
    fun getDeleteChoreChoreById(@RequestParam id:Int): Optional<ChoreChore> {
        this.choreChoreRepository.deleteById(id)

        return this.choreChoreRepository.findById(id)
    }
    @GetMapping("/chorechart/admin/choreday/delete")
    fun getDeleteChoreDayById(@RequestParam id:Int): Optional<ChoreDay> {
        this.choreDayRepository.deleteById(id)

        return this.choreDayRepository.findById(id)
    }

//    @PostMapping("/chorechart/admin/terminformation/delete")
//    fun deleteTermInformation(@RequestBody deleteId: Int): TermInformation {
//        logger.debug("We Are In HERE")
//        this.termInformationRepository.deleteById(deleteId)
//        return TermInformation()
//    }

    @PostMapping("/chorechart/admin/terminformation")
    fun createTermInformation(@RequestBody termInformation: TermInformation): TermInformation {

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

        return this.termInformationRepository.save(termInformation)
    }

    @GetMapping("/chorechart/admin/termInformation/active")
    fun getActiveTermInformation(): TermInformation {
        return this.termInformationRepository.findByActive(true)
    }

    @GetMapping("/chorechart/admin/termInformation")
    fun getActiveTermById(@RequestParam id:Int): Optional<TermInformation> {
        return this.termInformationRepository.findById(id)
    }

    @GetMapping("/chorechart/admin/choredays")
    fun getAllChoreDays(): MutableIterable<ChoreDay> {
        return this.choreDayRepository.findAll()
    }

    @PostMapping("/chorechart/admin/choredays")
    fun getAllChoreDays(@RequestBody choreDays: List<ChoreDay>) {
        logger.debug(choreDays.toString())
        this.choreDayRepository.saveAll(choreDays)
    }

    @PostMapping("/chorechart/admin/choreday")
    fun saveChoreDay(@RequestBody choreDay: ChoreDay): ChoreDay {
        logger.debug(choreDay.toString())
        return this.choreDayRepository.save(choreDay)
    }

    @PostMapping("/chorechart/admin/chorechore")
    fun saveChoreChore(@RequestBody choreChore: ChoreChore): ChoreChore {
        logger.debug(choreChore.toString())
        return this.choreChoreRepository.save(choreChore)
    }
    @DeleteMapping("/chorechart/admin/choreday")
    fun deleteChoreDay(@RequestParam id: Int){
        this.choreDayRepository.deleteById(id)
    }

    @GetMapping("/chorechart/admin/chorechores")
    fun getAllChoreChores(): MutableIterable<ChoreChore> {
        return this.choreChoreRepository.findAll()
    }

    @PostMapping("/chorechart/admin/chorechores")
    fun getAllChoreChores(@RequestBody choreChores: List<ChoreChore>) {
        logger.debug(choreChores.toString())
        this.choreChoreRepository.saveAll(choreChores)
    }

    @DeleteMapping("/chorechart/admin/chorechore")
    fun deleteChoreChore(@RequestParam id: Int){
        this.choreChoreRepository.deleteById(id)
    }
    @DeleteMapping("/chorechart/admin/terminformation")
    fun deleteTermInformation(@RequestParam id: Int){
        this.termInformationRepository.deleteById(id)
    }

    @PostMapping("/chorechart/admin/choreForms")
    fun saveChoreChartForm(@RequestBody choresAndDaysList: List<ChoresAndDays>){



        choresAndDaysList.forEach { choresAndDays ->

            if (this.choreChartWeekRepository.findByWeek(choresAndDays.weekNumber) == null){
                choreChartWeekRepository.save(ChoreChartWeek(week = choresAndDays.weekNumber))
            }


            choreAndWeekRepository.findByWeek(week = choresAndDays.weekNumber).forEach {
                if (choresAndDays.chores.none { day -> day.id == it.choreId }) {
                    logger.debug("Deleting chore: $it")
                    choreAndWeekRepository.delete(it)
                }
            }

            for (it in choresAndDays.chores) {
                if (it.id == 0){
                    throw Throwable("This Is A Bad Chore: $it")
                }
                val oldChores = choreAndWeekRepository.findByWeekAndChoreId(week = choresAndDays.weekNumber, choreId = it.id)
                if (oldChores.isEmpty()) {
                    logger.debug("New Save, Chore Id: ${it.id} exists for the week of: ${choresAndDays.weekNumber}")
                    choreAndWeekRepository.save(ChoreAndWeek(week = choresAndDays.weekNumber, choreId = it.id))
                } else if (oldChores.size != 1) {
                    logger.error("Number of chores returned from a database call is more than one, $oldChores")

                } else if (!oldChores.isEmpty()) {

                    logger.debug("Existing Chore, Day Id: ${it.id} exists for the week of: ${choresAndDays.weekNumber}")

                }
            }

            dayAndWeekRepository.findByWeek(week = choresAndDays.weekNumber).forEach {
                if (choresAndDays.days.none { day -> day.id == it.dayId }) {
                    logger.debug("Deleting day: $it")
                    dayAndWeekRepository.delete(it)
                }
            }

            for (it in choresAndDays.days) {
                if (it.id == 0){
                    throw Throwable("This Is A Bad Day: $it")
                }
                val oldDays = dayAndWeekRepository.findByWeekAndAndDayId(week = choresAndDays.weekNumber, dayId = it.id)
                if (oldDays.isEmpty()) {
                    logger.debug("New Save, Day Id: ${it.id} exists for the week of: ${choresAndDays.weekNumber}")
                    dayAndWeekRepository.save(DayAndWeek(week = choresAndDays.weekNumber, dayId = it.id))
                } else if (oldDays.size != 1) {
                    logger.error("Number of days returned from a database call is more than one, $oldDays")

                } else if (!oldDays.isEmpty()) {

                    logger.debug("Existing day, Day Id: ${it.id} exists for the week of: ${choresAndDays.weekNumber}")

                }
            }

            logger.debug("Week: " + choresAndDays.weekNumber)
        }
    }

    @GetMapping("/chorechart/admin/choreForms")
    fun getChoreForms(): MutableList<ChoresAndDays> {

        val choresAndDaysList = mutableListOf<ChoresAndDays>()


        this.choreChartWeekRepository.findAll().forEach{

            val choreChores = mutableListOf<ChoreChore>()
            logger.debug("Chore Chart Week creating forms from: $it")

            this.choreAndWeekRepository.findByWeek(it.week).forEach { choreWeek ->
                logger.debug("Chore Week Getting Form: $choreWeek")
                choreChores.add(this.choreChoreRepository.findById(choreWeek.id).get())
            }

            val choreDays = mutableListOf<ChoreDay>()

            this.dayAndWeekRepository.findByWeek(it.week).forEach{ choreDay ->

                    choreDays.add(this.choreDayRepository.findById(choreDay.id).get())


            }
            choresAndDaysList.add(ChoresAndDays(chores = choreChores,
                    days = choreDays,
                    weekNumber = it.week))
        }
        return choresAndDaysList


    }

}