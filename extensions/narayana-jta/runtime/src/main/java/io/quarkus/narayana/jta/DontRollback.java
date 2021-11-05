package io.quarkus.narayana.jta;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * If you annotate your exception with this annotation, the transactional interceptor will not
 * rollback when your exception it thrown.
 * 
 * FIXME: move to SPI so it can be used by TransactionalReactive
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DontRollback {

}
