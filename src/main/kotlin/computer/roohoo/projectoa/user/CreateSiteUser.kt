package computer.roohoo.projectoa.user

class CreateSiteUser{
    var user_name: String? = null
    var first_name: String? = null
    var last_name: String? = null
    var kappa_sigma: Int? = 0
    var big: Int? = null
    var brother: Boolean = false
    var password: String? = null
    override fun toString(): String {
        return "CreateSiteUser(user_name=$user_name, first_name=$first_name, last_name=$last_name, kappa_sigma=$kappa_sigma, big=$big, brother=$brother, password=$password)"
    }


}