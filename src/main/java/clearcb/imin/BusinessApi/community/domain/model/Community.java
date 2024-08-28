package clearcb.imin.BusinessApi.community.domain.model;

import clearcb.imin.BusinessApi.common.domain.model.ManageableUsers;

public class Community {

    private Long id;
    private String name;
    private ManageableUsers members;
    private String email;
    // add contact and socialNetworks


    public Community() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Community community = (Community) o;

        if (!id.equals(community.id)) return false;
        return name.equals(community.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
