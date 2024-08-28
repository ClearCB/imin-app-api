package clearcb.imin.BusinessApi.auth.infrastructure.repository;

import clearcb.imin.BusinessApi.auth.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.verificationCode = ?1")
    public UserEntity findByVerificationCode(String code);
}
