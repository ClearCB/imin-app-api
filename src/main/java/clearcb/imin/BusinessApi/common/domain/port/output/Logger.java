package clearcb.imin.BusinessApi.common.domain.port.output;

public interface Logger {
    void debug(String message);

    void info(String message);

    void error(String message);
}
