package plb.accounting.services.impl.intercept;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;
import javax.enterprise.util.Nonbinding;

/**
 * User: pbala
 * Date: 4/1/13 1:26 PM
 */
@Inherited
@InterceptorBinding
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
    @Nonbinding Class<?>[] groups() default {};
}
