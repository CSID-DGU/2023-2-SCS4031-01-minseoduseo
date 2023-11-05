package msds.homefarming.exception.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResult
{
    private Long code;
    private String status;
    private String message;
}
