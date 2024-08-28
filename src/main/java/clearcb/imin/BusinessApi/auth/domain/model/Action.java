package clearcb.imin.BusinessApi.auth.domain.model;

import java.util.Set;
import java.util.UUID;

public class Action {

    private final Set<Permission> permissionsNeeded;
    private UUID id;
    private String name;

    public Action(UUID id, String name, Set<Permission> permissionsNeeded) {
        this.id = id;
        this.name = name;
        this.permissionsNeeded = permissionsNeeded;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissionsNeeded() {
        return permissionsNeeded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action = (Action) o;

        if (!id.equals(action.id)) return false;
        return name.equals(action.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Action{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}