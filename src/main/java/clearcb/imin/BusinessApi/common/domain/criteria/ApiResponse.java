package clearcb.imin.BusinessApi.common.domain.criteria;

import java.util.Objects;

public class ApiResponse {
    private Object data;
    private String message;
    private int status;

    public ApiResponse(Object data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiResponse that = (ApiResponse) o;

        if (status != that.status) return false;
        if (!Objects.equals(data, that.data)) return false;
        return message.equals(that.message);
    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + message.hashCode();
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
