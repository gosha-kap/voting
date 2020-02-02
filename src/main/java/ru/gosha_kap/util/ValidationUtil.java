package ru.gosha_kap.util;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ru.gosha_kap.model.Meal;
import ru.gosha_kap.model.Menu;
import ru.gosha_kap.model.hasID;
import ru.gosha_kap.util.exception.IllegalRequestDataException;
import ru.gosha_kap.util.exception.NotFoundException;

import javax.validation.*;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkTimeZone(String timezone){
        try {
            ZoneId.of(timezone);
        }
        catch(DateTimeException e){
            throw new IllegalRequestDataException("Invalid timezone:"+timezone);
        }

    }


    public static void checkNew(hasID object) {
        if (!object.isNew()) {
            throw new IllegalRequestDataException(object + " must be new (id=null)");
        }

    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void assureIdIsIncluded(Menu menu, Meal meal){
        if(Objects.isNull(menu.getMeals().stream().filter(x->x.getId().equals(meal.getId())).findFirst().orElse(null)))
            throw new IllegalRequestDataException("Not native meal for this menu");
    }


    public static void assureIdConsistent(hasID object, int id) {
        if (object.isNew()) {
            object.setId(id);
        } else if (object.id() != id) {
            throw new IllegalRequestDataException(object + " must be with id=" + id);
        }
     }


    public static Throwable getRootCause(Throwable t) {

        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }



    private static final Validator validator;

    static {
        //  From Javadoc: implementations are thread-safe and instances are typically cached and reused.
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        //  From Javadoc: implementations of this interface must be thread-safe
        validator = factory.getValidator();
    }

    public static <T> void validate(T bean) {
        // https://alexkosarev.name/2018/07/30/bean-validation-api/
        Set<ConstraintViolation<T>> violations = validator.validate(bean);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public static ResponseEntity<String> getErrorResponse(BindingResult result) {
        return ResponseEntity.unprocessableEntity().body(
                result.getFieldErrors().stream()
                        .map(fe -> String.format("[%s] %s", fe.getField(), fe.getDefaultMessage()))
                        .collect(Collectors.joining("<br>"))
        );
    }
}