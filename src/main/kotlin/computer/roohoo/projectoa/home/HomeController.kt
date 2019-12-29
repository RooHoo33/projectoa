package computer.roohoo.projectoa.home

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class HomeController() {
    @GetMapping("/")
    fun getHome(): String {
        return "home"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @PostMapping("/login")
    fun logUserIn(){

    }
}
