package clearcb.imin.BusinessApi.common.infrastructure.logging;

import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggerImpl implements Logger {
    @Override
    public void debug(String message) {
        log.debug(message);
    }

    @Override
    public void info(String message) {
        log.info(message);
    }

    @Override
    public void error(String message) {
        log.error(message);
    }
}
