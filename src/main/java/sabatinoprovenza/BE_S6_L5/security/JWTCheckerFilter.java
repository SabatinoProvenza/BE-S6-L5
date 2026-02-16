package sabatinoprovenza.BE_S6_L5.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import sabatinoprovenza.BE_S6_L5.exceptions.UnauthorizedException;

import java.io.IOException;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {
    private final JWTTools jwtTools;

    public JWTCheckerFilter(JWTTools jwtTools) {
        this.jwtTools = jwtTools;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //CONTROLLO HEADER
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Token non valido!");

        String token = authHeader.replace("Bearer ", "");

        jwtTools.verifyToken(token);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
