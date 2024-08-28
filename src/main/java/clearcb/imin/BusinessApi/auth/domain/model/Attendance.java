package clearcb.imin.BusinessApi.auth.domain.model;

import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.domain.model.User;

import java.util.UUID;

public class Attendance {

    private UUID id;

    private Event event;

    private User user;

    private Role role;

    public Attendance() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendance that = (Attendance) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "event=" + event +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}
