package clearcb.imin.BusinessApi.auth.infrastructure.repository;

import clearcb.imin.BusinessApi.auth.infrastructure.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAttendenceRepository extends JpaRepository<AttendanceEntity, UUID> {

    Optional<AttendanceEntity> findByEventIdAndUserId(UUID eventId, UUID userId);

    List<AttendanceEntity> findByUserId(UUID userId);

    List<AttendanceEntity> findByEventId(UUID eventId);
}
