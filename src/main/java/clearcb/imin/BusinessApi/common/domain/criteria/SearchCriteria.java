package clearcb.imin.BusinessApi.common.domain.criteria;

public class SearchCriteria {
    private String filterKey;
    private Object value;
    private String operation;
    private String dataOption;

    public SearchCriteria() {
    }

    public SearchCriteria(String filterKey, String operation, Object value) {
        super();
        this.filterKey = filterKey;
        this.operation = operation;
        this.value = value;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDataOption() {
        return dataOption;
    }

    public void setDataOption(String dataOption) {
        this.dataOption = dataOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchCriteria that = (SearchCriteria) o;

        if (!filterKey.equals(that.filterKey)) return false;
        if (!value.equals(that.value)) return false;
        if (!operation.equals(that.operation)) return false;
        return dataOption.equals(that.dataOption);
    }

    @Override
    public int hashCode() {
        int result = filterKey.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + operation.hashCode();
        result = 31 * result + dataOption.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "filterKey='" + filterKey + '\'' +
                ", value=" + value +
                ", operation='" + operation + '\'' +
                ", dataOption='" + dataOption + '\'' +
                '}';
    }
}