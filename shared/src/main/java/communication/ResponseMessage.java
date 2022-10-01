package communication;

import commandService.ExecutionResult;

import java.io.Serializable;

public record ResponseMessage(ExecutionResult result) implements Serializable {
}