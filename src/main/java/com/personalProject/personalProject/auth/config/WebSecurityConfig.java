package com.personalProject.personalProject.auth.config;

import com.personalProject.personalProject.auth.jwt.JwtAuthenticationEntryPoint;
import com.personalProject.personalProject.auth.jwt.JwtAuthenticationFilter;
import com.personalProject.personalProject.auth.repository.UserAuthRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    /*
     * We injected Interface, If we have only one implementation, No need specify @Qualifier or @Primary.
     */
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // use @Qualifier("customUserDetailsService")  or use @Primary on required bean
    // public WebSecurityConfig(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {

    public WebSecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter, JwtAuthenticationEntryPoint authenticationEntryPoint, UserAuthRepository userAuthRepository) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        // return httpSecurity.build(); // If you return just this, no password needed.
//
//        /*
//         * Below code snippet makes all requests needed to get authenticated. This just Basic security.
//         * We will get popup to enter user/pwd (we defined user/pwd in properties).
//         * To get Login page add .formLogin(Customizer.withDefaults())
//         * To disable CSRF, add .csrf(csrf -> csrf.disable)
//         */
//        httpSecurity
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(
//                        requests -> requests
//                                .requestMatchers("signUp", "login").permitAll() // bypassed these two APIs
//                                .anyRequest().authenticated()) // rest all APIs needed authentication
//                //.formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
//        return httpSecurity.build();
//
//        /*
//         *The above config is just basic where we hardcoded user.pwd in properties file.
//         * To add List of users, we need to use UserDetailsService bean for it. Below did that.
//         *
//         */
//    }

    /*
     * The above filter is just checks DB authentication.
     * In this filter chain, Added JWT filter verification.
     * To add we need JWTFilter, created new JwtAuthenticationFilter class
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable()) // Implement CSRF token for PROD applications
                .authorizeHttpRequests(
                        requests -> requests
                                .requestMatchers("signUp", "login", "/swagger-ui/**", "/api-docs/**").permitAll() // bypassed these APIs
                                .anyRequest().authenticated()  // rest all APIs needed authentication
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                //.formLogin(Customizer.withDefaults()) // gets login popup window
                //.httpBasic(Customizer.withDefaults()); // This is used for Basic Auth
//                .exceptionHandling(exception ->
//                        exception.authenticationEntryPoint(authenticationEntryPoint))
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class); // JWT filter checks
        return httpSecurity.build();
    }


    // //@Bean
    public UserDetailsService userDetailsService() {
        /*
         * This UserDetailsService have only one method that is UserDetails loadUserByUsername().
         * There are multiple implementations for this method. We can use anything. Below used hard coded InMemoryUserDetailsManager
         * If we defined this, comment user/pwd in properties file.
         */
        UserDetails user1 = User.withUsername("test")
                //.password("test")// If we add pwd like plain text, we will get  java.lang.IllegalArgumentException: You have entered a password with no PasswordEncoder
                .password("{noop}test") // this just for testing, not used in PROD
                .roles("USER").build();

        UserDetails user2 = User.withUsername("test1")
                .password("{noop}test1")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
        /*
         * The above one just hard coded user/pwd. To check user/pwd in DB, uncomment this bean,
         * create implementation for UserDetailsService
         */
    }

    /*
     * This is Alternative implementation to above method. Above is hard coded InMemoryUserDetailsManager service
     * This implementation talks with DB and checks user/pwd
     * So, we created custom UserDetailsService as CustomUserDetailsService and injected userDetailsService interface
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);

        /*
         *  this just plain text, we need to encrypt it.
         * Below created bCryptPasswordEncoder() to encrypt and created bean
         */
        // provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        provider.setPasswordEncoder(bCryptPasswordEncoder()); // encrypt password using below method.s
        return provider;
    }

    /*
     * This for encrypting password, we will use in DaoAuthenticationProvider
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * This is a AuthenticationManager to authenticate and used to check user is authenticated or not.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    //TODO Implement Security Exception handling
    //TODO Implement CSRF Token
    //TODO implement JWT token LogOut

}
