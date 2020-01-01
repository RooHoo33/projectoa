package computer.roohoo.projectoa.choreChart

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.*
import computer.roohoo.projectoa.user.SiteUser
import computer.roohoo.projectoa.user.SiteUsersRepository
import org.slf4j.LoggerFactory
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = arrayOf("http://localhost:3000"), maxAge = 3600)
@RestController
class ChoreChartRestController (private val userPreferenceRepository: UserPreferenceRepository,
                                private val choreChartRepository: ChoreChartRepository,
                                private val choreChoreRepository: ChoreChoreRepository,
                                private val choreDayRepository: ChoreDayRepository,
                                private val choreDayWithUserRepository: ChoreDayWithUserRepository,
                                private val siteUsersRepository: SiteUsersRepository){

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

}