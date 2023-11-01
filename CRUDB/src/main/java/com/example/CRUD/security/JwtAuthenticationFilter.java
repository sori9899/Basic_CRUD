package com.example.CRUD.security;


import com.example.CRUD.entity.Users;
import com.example.CRUD.repository.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    UsersRepository usersRepository;

    @Autowired
    public JwtAuthenticationFilter(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            if (jwt != null && !jwt.equals("") && JwtTokenProvider.validateToken(jwt)) {
                String username = JwtTokenProvider.getUsernameFromJwt(jwt);

                try {
                    Users user = usersRepository.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not present"));

                    UserAuthentication authentication = new UserAuthentication(username, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                catch (Exception e) {
                    request.setAttribute("unauthorization", "401 인증 중 오류");
                }
            } else {
                if (jwt != null && !jwt.equals("")) {
                    request.setAttribute("unauthorization", "401 인증키 없음.");
                }

                if (!JwtTokenProvider.validateToken(jwt)) {
                    request.setAttribute("unauthorization", "401-001 인증키 만료.");
                }
            }
        } catch (Exception e) {

        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("token")) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
