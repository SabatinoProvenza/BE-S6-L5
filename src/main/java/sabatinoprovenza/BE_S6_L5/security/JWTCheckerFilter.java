package sabatinoprovenza.BE_S6_L5.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import sabatinoprovenza.BE_S6_L5.entities.Dipendente;
import sabatinoprovenza.BE_S6_L5.exceptions.UnauthorizedException;
import sabatinoprovenza.BE_S6_L5.services.DipendenteService;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {
    private final JWTTools jwtTools;
    private final DipendenteService dipendenteService;

    public JWTCheckerFilter(JWTTools jwtTools, DipendenteService dipendenteService) {
        this.jwtTools = jwtTools;
        this.dipendenteService = dipendenteService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //CONTROLLO HEADER
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Token non valido!");

        String token = authHeader.replace("Bearer ", "");

        jwtTools.verifyToken(token);

        UUID dipendenteId = jwtTools.extractIdFromToken(token);
        Dipendente dipendente = this.dipendenteService.findById(dipendenteId);

        Authentication authentication = new UsernamePasswordAuthenticationToken(dipendente, null, dipendente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
