package computer.roohoo.projectoa.Security

import computer.roohoo.projectoa.User.SiteUsersRepository
import computer.roohoo.projectoa.User.UserAuthDatabaseDetails
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    private val siteUsersRepository: SiteUsersRepository? = null

    @Autowired
    private val userAuthDatabaseDetails: UserAuthDatabaseDetails? = null

    val logger = LoggerFactory.getLogger(SecurityConfig::class.java)!!

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/create-user").permitAll()
                .antMatchers("/static/css/layout_header.css").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/images/*").permitAll()
                .antMatchers("/**/*.js", "/**/*.css").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/")
                .and()
                .logout().logoutRequestMatcher(AntPathRequestMatcher("/logout"));
    }

    @Throws(Exception::class)
    public override fun configure(builder: AuthenticationManagerBuilder) {
        logger.debug("we are right here")
        builder.userDetailsService<UserAuthDatabaseDetails>(userAuthDatabaseDetails)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(4)
    }
}