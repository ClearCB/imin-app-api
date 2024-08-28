package clearcb.imin.BusinessApi.auth.infrastructure.repository;

import clearcb.imin.BusinessApi.auth.domain.model.AuthorizatorMessageEnum;
import clearcb.imin.BusinessApi.auth.domain.model.Permission;
import clearcb.imin.BusinessApi.auth.domain.port.output.AuthorizatorRepositoryPort;
import clearcb.imin.BusinessApi.auth.infrastructure.entity.ActionEntity;
import clearcb.imin.BusinessApi.auth.infrastructure.entity.AttendanceEntity;
import clearcb.imin.BusinessApi.auth.infrastructure.entity.MembershipEntity;
import clearcb.imin.BusinessApi.auth.infrastructure.mapper.PermissionMapper;
import clearcb.imin.BusinessApi.common.domain.exception.DatabaseError;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaAuthorizatorRepositoryAdapter implements AuthorizatorRepositoryPort {

    private final JpaActionRepository jpaActionRepository;
    private final JpaAttendenceRepository jpaAttendenceRepository;
    private final JpaMembershipRepository jpaMembershipRepository;
    private final Logger logger;

    @Override
    public Optional<Set<Permission>> getActionPermissionsByActionId(UUID actionId) {

        try {

            logger.info("Searching action permission with id: " + actionId);
            Optional<ActionEntity> actionEntityOptional = jpaActionRepository.findById(actionId);

            return actionEntityOptional.map(actionEntity -> actionEntity
                    .getPermissionsNeeded()
                    .stream()
                    .map(PermissionMapper::toDomain)
                    .collect(Collectors.toSet()));

        } catch (Exception e) {

            logger.error("Database error: " + e.getMessage());
            throw new DatabaseError(AuthorizatorMessageEnum.ERROR_RETRIEVING_ACTION_PERMISSIONS.getMessage());

        }

    }

    @Override
    public Optional<Set<Permission>> getUserPermissionsByEventId(UUID userId, UUID eventId) {

        try {

            logger.info("Searching user in event role: " + userId + " event " + eventId);
            Optional<AttendanceEntity> attendanceEntityOptional = jpaAttendenceRepository.findByEventIdAndUserId(eventId, userId);

            return attendanceEntityOptional.map(attendanceEntity -> attendanceEntity
                    .getRole()
                    .getPermissions()
                    .stream()
                    .map(PermissionMapper::toDomain)
                    .collect(Collectors.toSet()));

        } catch (Exception e) {

            logger.error("Database error: " + e.getMessage());
            throw new DatabaseError(AuthorizatorMessageEnum.ERROR_RETRIEVING_ACTOR_PERMISSIONS.getMessage());

        }

    }

    @Override
    public Optional<Set<Permission>> getUserPermissionsByCommunityId(UUID userId, UUID communityId) {
        try {

            logger.info("Searching user in community role: " + userId + " community " + communityId);
            Optional<MembershipEntity> membershipEntityOptional = jpaMembershipRepository.findByUserIdAndCommunityId(userId, communityId);

            return membershipEntityOptional.map(membershipEntity -> membershipEntity
                    .getRole()
                    .getPermissions()
                    .stream()
                    .map(PermissionMapper::toDomain)
                    .collect(Collectors.toSet()));

        } catch (Exception e) {

            logger.error("Database error: " + e.getMessage());
            throw new DatabaseError(AuthorizatorMessageEnum.ERROR_RETRIEVING_ACTOR_PERMISSIONS.getMessage());

        }
    }
}
