package clearcb.imin.BusinessApi.image.domain.model;

import java.util.UUID;

public class Image {

    private UUID id;
    private byte[] bytes;
    private boolean isMain;

    public Image() {
    }

    public Image(byte[] bytes, boolean isMain) {
        this.bytes = bytes;
        this.isMain = isMain;
    }


    public Image(UUID id, byte[] bytes, boolean isMain) {
        this.id = id;
        this.bytes = bytes;
        this.isMain = isMain;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        return id.equals(image.id);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", isMain=" + isMain +
                '}';
    }
}