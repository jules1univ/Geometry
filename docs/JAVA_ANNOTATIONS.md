# Annotations en Java

Comment fonctionne les annotations en Java ?

Cas de base d'utilisation vu en cours :

```java
@Override
public void maFonction() {
    ...
}
```

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SVGField {
    String[] value() default {};
}

```
