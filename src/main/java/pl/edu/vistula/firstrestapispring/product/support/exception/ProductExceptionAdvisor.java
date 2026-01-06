
package pl.edu.vistula.firstrestapispring.product.support.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.vistula.firstrestapispring.shared.api.response.ErrorMessageResponse;

@ControllerAdvice
public class ProductExceptionAdvisor {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageResponse productNotFound(Exception e) {
        return new ErrorMessageResponse(e.getMessage());
    }
}
