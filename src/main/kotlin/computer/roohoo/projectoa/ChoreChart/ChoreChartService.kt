package computer.roohoo.projectoa.choreChart

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.*
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.ChoreAndWeek
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.ChoreAndWeekRepository
import computer.roohoo.projectoa.choreChart.repositorysAndObjects.Week.DayAndWeekRepository
import computer.roohoo.projectoa.user.SiteUser
import computer.roohoo.projectoa.user.SiteUsersRepository
import org.apache.catalina.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.math.log

@Service
class ChoreChartService(private val userPreferenceRepository: UserPreferenceRepository,
                        private val choreChartRepository: ChoreChartRepository,
                        private val choreChoreRepository: ChoreChoreRepository,
                        private val choreDayRepository: ChoreDayRepository,
                        private val choreDayWithUserRepository: ChoreDayWithUserRepository,
                        private val siteUsersRepository: SiteUsersRepository,
                        private val termInformationRepository: TermInformationRepository,
                        private val choreAndWeekRepository: ChoreAndWeekRepository,
                        private val dayAndWeekRepository: DayAndWeekRepository,
                        private val choreChartWeekRepository: ChoreChartWeekRepository) {

    val logger = LoggerFactory.getLogger(ChoreChartService::class.java)!!


    fun getChoreChartForms(week: String) {

        val choreChart = choreChartRepository.findByWeek(week)
        val choreAndWeek = choreAndWeekRepository.findByWeek(week)
        val dayAndWeek = dayAndWeekRepository.findByWeek(week)


    }


    /**
     * Finds all prefs for a user then sorts in into a map with the key being the week
     *
     * @param userId the users userId
     */
    fun getAllUserPrefs(userId: Int): MutableMap<String, MutableList<UserPreference>> {
        val allUserPrefs = userPreferenceRepository.findByUserId(userId)

        val sortedUserPrefs = mutableMapOf<String, MutableList<UserPreference>>()

        allUserPrefs.forEach {
            if (sortedUserPrefs.containsKey(it.week)) {
                sortedUserPrefs[it.week]?.add(it)
            } else {
                sortedUserPrefs[it.week] = mutableListOf(it)
            }
        }

        return sortedUserPrefs
    }

    fun createChoreChart(): MutableList<ChoreDayUser> {

        val activePeople = this.siteUsersRepository.findByActive()


        val list = mutableListOf<UserAndPreferences>()




        activePeople.forEach {
            this.getAllUserPrefs(it.userId)["default"]?.let { userPrefs ->
                if (it.kappaSigma == 0) {
                    it.kappaSigma = 99999
                }
                list.add(UserAndPreferences(siteUser = it, preferences = userPrefs))
            }

        }


        val sortedList = list.sortedWith(compareBy({ it.siteUser.kappaSigma }, { it.siteUser.big }))

        val form = this.getChoreChartForm("default")


        val choreChartFromMap = mutableMapOf<DayAndUser, MutableList<ChoreAndUser>>()

//        val daysAndChoresList = mutableMapOf<Int, MutableMap<Int, String>>()
//
//        form.days.forEach{ day ->
//            val chores = mutableMapOf<Int, String>()
//            form.chores.forEach{ chore->
//                chores[chore.id] = "Nobody"
//            }
//            daysAndChoresList[day.id] = chores
//
//
//        }
//
//        var looking = true
//        while (looking){
//            looking = false
//
//            sortedList.forEach users@ { userPrefs ->
//                var found = false
//                userPrefs.preferences.forEach userPrefs@{ userPref ->
//                    if (found){
//                        return@users
//                    }
//                    daysAndChoresList.forEach daysAndChores@ { dayId, chores ->
//                        if (found){
//                            return@daysAndChores
//                        }
//
//                        chores.forEach chores@ { choreId, user->
//
//                            if (dayId == userPref.day_id && choreId == userPref.choreId && user == "Nobody"){
//                                if (userPrefs.siteUser.kappaSigma != 99999){
//                                    daysAndChoresList[dayId]!![choreId] = userPrefs.siteUser.kappaSigma.toString()
//                                } else{
//
//                                    logger.debug(userPrefs.siteUser.firstName + " " + userPrefs.siteUser.lastName)
//                                    daysAndChoresList[dayId]!![choreId] = userPrefs.siteUser.firstName + " " + userPrefs.siteUser.lastName
//                                }
//
//                                found = true
//                                return@chores
//                            }
//
//
//                        }
//
//
//                    }
//                }
//
//            }
//
//        }

        val completeChoresAndDaysList = mutableListOf<ChoreDayUser>()

        form.days.forEach { day ->
            form.chores.forEach { chore ->
                completeChoresAndDaysList.add(ChoreDayUser(choreChore = chore, siteUser = SiteUser(firstName = "Nobody"), choreDay = day))


            }


        }

        var looking = true
        while (looking) {
            looking = false

            sortedList.forEach users@{ userPrefs ->
                userPrefs.preferences.forEach userPrefs@{ userPref ->

                    completeChoresAndDaysList.forEach daysAndChores@{ choreDayUser ->


                        if (choreDayUser.choreDay.id == userPref.day_id && choreDayUser.choreChore.id == userPref.choreId && choreDayUser.siteUser.userId == 0 && userPref.preferenceRanking !=0) {
                            choreDayUser.siteUser = userPrefs.siteUser

                            logger.debug(userPrefs.siteUser.firstName + " " + userPrefs.siteUser.lastName)
                            looking = true
                            return@users
                        }


                    }


                }


            }
        }





        logger.debug(completeChoresAndDaysList.toString())

        logger.debug("Active User Prefs for default: ${sortedList.toString()}")
        return completeChoresAndDaysList


    }

    fun getChoreChartForm(week: String): ChoresAndDays {
        val choreChores = mutableListOf<ChoreChore>()
        logger.debug("Chore Chart Week creating forms from this week: $week")

        this.choreAndWeekRepository.findByWeek(week).forEach { choreWeek ->
            logger.debug("Chore Week Getting Form: $choreWeek")
            choreChores.add(this.choreChoreRepository.findById(choreWeek.choreId).get())
        }

        val choreDays = mutableListOf<ChoreDay>()

        this.dayAndWeekRepository.findByWeek(week).forEach { choreDay ->
            logger.debug("choreDay Getting Form: $choreDay")
            choreDays.add(this.choreDayRepository.findById(choreDay.dayId).get())


        }
        return ChoresAndDays(chores = choreChores,
                days = choreDays,
                weekNumber = week)
    }


}

