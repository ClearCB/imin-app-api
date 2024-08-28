package clearcb.imin.BusinessApi.auth.infrastructure.mapper;

import clearcb.imin.BusinessApi.auth.domain.model.Permission;
import clearcb.imin.BusinessApi.auth.infrastructure.entity.PermissionEntity;

public class PermissionMapper {

    private PermissionMapper() {
    }

    public static Permission toDomain(PermissionEntity permissionEntity) {
        return new Permission(
                permissionEntity.getId(),
                permissionEntity.getName()
        );
    }
}
