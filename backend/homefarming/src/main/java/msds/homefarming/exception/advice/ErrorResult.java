package msds.homefarming.exception.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResult
{
    private Long status;
    private String error;
    private String message;
}
