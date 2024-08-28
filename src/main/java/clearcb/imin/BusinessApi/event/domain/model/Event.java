package clearcb.imin.BusinessApi.event.domain.model;

import clearcb.imin.BusinessApi.common.domain.model.Category;
import clearcb.imin.BusinessApi.common.domain.model.ManageableUsers;
import clearcb.imin.BusinessApi.common.domain.model.Tag;
import clearcb.imin.BusinessApi.community.domain.model.Community;
import clearcb.imin.BusinessApi.image.domain.model.Image;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class Event {

    private UUID id;
    private Community community;
    private String title;
    private UUID userId;
    private String locationName;
    private String smallDescription;
    private String largeDescription;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private Set<Category> categories;
    private Set<Tag> tags;
    private Set<Image> images;

    private ManageableUsers attendees;
    private boolean onlyMembers;
    private boolean isActive;
    private boolean isOnline;
    private Double latitude;
    private Double longitude;

    public Event(String title, String smallDescription, String largeDescription) {
        this.title = title;
        this.smallDescription = smallDescription;
        this.largeDescription = largeDescription;
    }

    public Event(UUID id, String title, String smallDescription, String largeDescription) {
        this.id = id;
        this.title = title;
        this.smallDescription = smallDescription;
        this.largeDescription = largeDescription;
    }

    public Event() {
    }

    public Event(Set<Tag> tags, ManageableUsers attendees) {
        this.tags = tags;
        this.attendees = attendees;
    }

    public Event(String title, String smallDescription, String largeDescription, ManageableUsers attendees) {
        this.title = title;
        this.smallDescription = smallDescription;
        this.largeDescription = largeDescription;
        this.attendees = attendees;
    }

    public Event(UUID id, UUID userId,String title, String smallDescription, String largeDescription, String locationName, Double latitude, Double longitude, boolean isOnline
            , LocalDateTime startDate, LocalDateTime finishDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.smallDescription = smallDescription;
        this.largeDescription = largeDescription;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isOnline = isOnline;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Event(UUID userId, String title, String smallDescription, String largeDescription, String locationName, Double latitude, Double longitude, boolean isOnline
            , LocalDateTime startDate, LocalDateTime finishDate) {
        this.userId =userId;
        this.title = title;
        this.smallDescription = smallDescription;
        this.largeDescription = largeDescription;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isOnline = isOnline;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getLargeDescription() {
        return largeDescription;
    }

    public void setLargeDescription(String largeDescription) {
        this.largeDescription = largeDescription;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public ManageableUsers getAttendees() {
        return attendees;
    }

    public void setAttendees(ManageableUsers attendees) {
        this.attendees = attendees;
    }

    public boolean isOnlyMembers() {
        return onlyMembers;
    }

    public void setOnlyMembers(boolean onlyMembers) {
        this.onlyMembers = onlyMembers;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return id.equals(event.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
