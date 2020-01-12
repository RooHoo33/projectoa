package computer.roohoo.projectoa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class ProjectoaApplication

fun main(args: Array<String>) {
	runApplication<ProjectoaApplication>(*args)

}
