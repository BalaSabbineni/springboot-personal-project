package com.personalProject.personalProject.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public JwtAuthenticationFilter(JwtService jwtService, @Qualifier("customUserDetailsService") UserDetailsService userDetailsService, HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    /*
     * To add filter logic, we need to get token from Headers and decode it and valid it.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        /*
         * If authHeader is null, then don't do anything
         */
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }


        try {
            /*
             * we are getting token after Bearer, jwt token starts after Bearer.
             */
            final String jwt = authHeader.substring(7);
            final String userName = jwtService.extractUserName(jwt);


            /*
             * After getting userName and jwt, we need to call SecurityContextHolder
             * to check user is authenticated or not
             */
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


            if (userName != null && authentication == null) {
                // then do Authenticate operation


                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                /*
                 * Verifying token is valid or not
                 */
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    logger.info("Token is valid for user: {}, authorities: {} ", userDetails.getUsername(), userDetails.getAuthorities());
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    null,
                                    userDetails.getAuthorities());

                    /*
                     * Setting sessionId and resourceURL which is needed.
                     */
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );


                    /*
                     * Before we checked SecurityContextHolder is null,
                     * Now here Setting SecurityContextHolder authentication.
                     */
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }

    }
}
