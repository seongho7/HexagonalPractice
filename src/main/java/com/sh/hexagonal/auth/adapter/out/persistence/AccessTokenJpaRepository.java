package com.sh.hexagonal.auth.adapter.out.persistence;

import com.sh.hexagonal.auth.domain.AccessToken;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessTokenJpaRepository extends JpaRepository<AccessToken, Long> {
    @EntityGraph(attributePaths = {"account"})
    Optional<AccessToken> findByToken(String token);
}
