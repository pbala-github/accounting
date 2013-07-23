package plb.accounting.common.injection;

import javax.inject.Qualifier;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: pbala
 * Date: 4/4/13 10:22 PM
 */
@Qualifier
@Target({CONSTRUCTOR, METHOD, FIELD, PARAMETER, TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Accounting {
}
