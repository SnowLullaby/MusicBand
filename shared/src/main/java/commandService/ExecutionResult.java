package commandService;

import java.io.Serializable;

public record ExecutionResult(String message, boolean isOk) implements Serializable {
}
