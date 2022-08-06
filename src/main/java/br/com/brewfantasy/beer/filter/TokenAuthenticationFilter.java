package br.com.brewfantasy.beer.filter;

import br.com.brewfantasy.beer.model.User;
import br.com.brewfantasy.beer.service.TokenService;
import br.com.brewfantasy.beer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    UserService userService;
    TokenService tokenService;

    public TokenAuthenticationFilter(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        boolean tokenValid = tokenService.isTokenValid(token);
        if(tokenValid){
            this.authenticate(token);
        }
        filterChain.doFilter(request, response);
    }
    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            token = null;
        }
        token = token.substring(7, token.length());
        return token;
    }
    private void authenticate(String tokenFromHeader) {
        Long id = tokenService.getTokenId(tokenFromHeader);

        Optional<User> optionalUser = userService.findById(id);

        if(optionalUser.isPresent()) {

            User user = optionalUser.get();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, getAuthority(user));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }
}
