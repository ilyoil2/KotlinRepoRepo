package kotlinRepo.reporepo.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinRepo.reporepo.global.security.jwt.JwtTokenProvider
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class FilterConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>(){

    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
        http.addFilterBefore(GlobalExceptionFilter(objectMapper), JwtFilter::class.java)
    }

}
