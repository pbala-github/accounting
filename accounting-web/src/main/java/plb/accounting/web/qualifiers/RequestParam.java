package plb.accounting.web.qualifiers;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import javax.xml.ws.BindingType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: pbala
 * Date: 3/26/13 9:43 AM
 */
@BindingType
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.FIELD})
public @interface RequestParam {
    @Nonbinding String value() default "";
}
