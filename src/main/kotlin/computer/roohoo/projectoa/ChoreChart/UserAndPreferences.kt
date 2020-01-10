package computer.roohoo.projectoa.choreChart

import computer.roohoo.projectoa.choreChart.repositorysAndObjects.UserPreference
import computer.roohoo.projectoa.user.SiteUser

data class UserAndPreferences(val siteUser: SiteUser, val preferences: MutableList<UserPreference> )