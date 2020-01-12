package computer.roohoo.projectoa.security

import computer.roohoo.projectoa.security.filters.JwtRequestFilter
import computer.roohoo.projectoa.user.SiteUsersRepository
import computer.roohoo.projectoa.user.UserAuthDatabaseDetails
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    val jwtRequestFilter : JwtRequestFilter? = null

    @Autowired
    private val siteUsersRepository: SiteUsersRepository? = null

    @Autowired
    private val userAuthDatabaseDetails: UserAuthDatabaseDetails? = null

    val logger = LoggerFactory.getLogger(SecurityConfig::class.java)!!



    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .cors().and()
                .csrf().disable()
                .httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/create-user").permitAll()
                .antMatchers("/rest/authenticate").permitAll()
                .antMatchers("/static/css/layout_header.css").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/images/*").permitAll()
                .antMatchers("/**/*.js", "/**/*.css").permitAll()
                .antMatchers("/rest/security/createuser").permitAll()
//                .antMatchers("/rest/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin().disable()
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
//                .loginPage("/login")
//                .permitAll()
//                .defaultSuccessUrl("/")

//                .and()
//                .logout().logoutRequestMatcher(AntPathRequestMatcher("/logout"));
    }

    @Throws(Exception::class)
    public override fun configure(builder: AuthenticationManagerBuilder) {

        logger.debug("we are right here")
        builder.userDetailsService<UserAuthDatabaseDetails>(userAuthDatabaseDetails)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(java.lang.Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager? {
        return super.authenticationManagerBean()
    }

//    @Bean
//    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
//        val configuration = CorsConfiguration()
//        configuration.allowedOrigins = Arrays.asList("http://localhost:3000")
//
//        configuration.allowedMethods = Arrays.asList("GET", "POST")
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", configuration)
//        return source
//    }
}