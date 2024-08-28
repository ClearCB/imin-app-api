package clearcb.imin.BusinessApi.common.domain.model;

import clearcb.imin.BusinessApi.event.domain.model.User;
import lombok.Getter;

import java.util.*;

public class ManageableUsers {

    private final int maxOccupants;

    @Getter
    private final Map<String, List<User>> users;

    public ManageableUsers(int maxOccupants, List<String> roles) {
        this.maxOccupants = maxOccupants;
        this.users = new HashMap<>();

        // Add new roles to the array.
        roles.forEach(role -> users.put(role, new ArrayList<>()));
    }

    public void addUser(String roleName, User user) {
        if (!isFull() && !userIsPresent(user)) {
            users.get(roleName).add(user);
        }
    }

    public boolean isFull() {

        int totalUsersList = users.values().stream()
                .mapToInt(List::size)
                .sum();

        return totalUsersList == maxOccupants;
    }

    public boolean userIsPresent(User userToAdd) {
        return users.values().stream()
                .flatMap(Collection::stream)
                .anyMatch(user -> user.getId().equals(userToAdd.getId()));
    }

    public void removeUser(User user) {
        users.values().forEach(list -> list.removeIf(u -> u.equals(user)));
    }

    public void changeUsersRole(String roleName, User user) {
        // Remove the user from the current role
        users.values().forEach(list -> list.removeIf(u -> u.getId().equals(user.getId())));

        // Add the user to the new role
        addUser(roleName, user);
    }

}
