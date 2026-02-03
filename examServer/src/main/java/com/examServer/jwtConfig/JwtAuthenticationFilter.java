package com.examServer.jwtConfig;

import com.examServer.services.implementation.UserDetailServiceImplementation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailServiceImplementation userDetailServiceImplementation;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // Bearer 2321513asdsa
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
            jwtToken = requestTokenHeader.substring(7).trim();
            try {
                username = jwtTokenHelper.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println("Jwt token has expired");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error");
            }
        } else {
            logger.warn("Invalid token, JWT Token does not begin with Bearer String");
        }

        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            final UserDetails userDetails = this.userDetailServiceImplementation.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenHelper.validateToken(jwtToken, userDetails)) {
                // authentication korbo aikhane
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("Invalid jwt token");
            }
        } else {
            System.out.println("Token is not Valid");
        }

        filterChain.doFilter(request, response);
    }
}
