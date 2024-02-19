package FieldAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Entity{
    String table_name();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface PK{}

@Retention(RetentionPolicy.RUNTIME)
@interface Ignored{}

@Entity(table_name="user")
public class User {
    @PK
    int id;

    String username;

    String email;

    @Ignored
    boolean is_active;
}
