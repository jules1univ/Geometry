# Annotations et classes en Java

## Qu’est-ce qu’une annotation en Java ?

Une annotation est une forme de métadonnée que l’on ajoute au code Java.
Elle ne change pas directement le comportement du programme, mais elle fournit des informations supplémentaires que Java (ou des frameworks) peuvent lire et exploiter.

Exemple classique vu en cours

```java
class A {
    public void maFonction() {
    }
}

class B extends A {

    @Override // L’annotation indique que cette méthode redéfinit une méthode de la classe parente
    public void maFonction() {
        ...
    }
}
```

Ici, @Override permet à Java de vérifier que la méthode existe bien dans la classe parente.
Si ce n’est pas le cas, le compilateur génère une erreur.

## Créer sa propre annotation

Nous allons maintenant créer notre annotation personnalisée.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SVGField {
    String[] value() default {};
}

```

Explication détaillée

`@Retention(RetentionPolicy.RUNTIME)`

- L’annotation sera disponible au moment de l’exécution (runtime).
  Cela signifie que nous pourrons la lire via la réflexion.

`@Target(ElementType.FIELD)`

- L’annotation ne peut être appliquée que sur des champs (attributs).

`String[] value() default {};`

- L’annotation accepte un tableau de chaînes de caractères en paramètre.
- Le paramètre est optionnel grâce à la valeur par défaut ({}).

## Utilisation de l’annotation

```java
class B {

    @SVGField
    private String monChamp;

    @SVGField("Hello ICI")
    private String monChampAvec1Parametre;

    @SVGField({ "Param1", "Param2", "ParamX" })
    private String monChampAvecPlusieursParametres;
}

```

Ce qu’il se passe ici

`@SVGField`

- Annotation sans paramètre → tableau vide par défaut

`@SVGField("Hello ICI")`

- Raccourci pour { "Hello ICI" }

`@SVGField({ "Param1", "Param2", "ParamX" })`

- Annotation avec plusieurs valeurs

## Mais à quoi servent les annotations ?

Les annotations deviennent utiles lorsqu’on les lit dynamiquement, grâce à la réflexion.

```java
// En java tout les object herite de la class Object donc:
class A {
    @SVGField("Coucou")
    private String monChamp;
}

// On créé A
A monA = new A();

// On l'utilise comme Objet java pure
Object objA = (Object)monA;


// Chaque objet en java a une class qui permet d'acceder a tout les attribute de la class (fonctions, champs, contrsucteur, etc...)
// Pour ce faire on fait:
objA.getClass();
// Ou aussi bien
monA.getClass();

// On obtient donc un object de type class:
Class<A> classDeA = monA.getClass();
/*
L’objet Class contient toutes les informations sur la classe :
champs
méthodes
constructeurs
annotations
etc...
*/

// On peut donc récuperer des attributs de A, par exemple:
Field[] lesChampsDeA = classDeA.getDeclaredFields() // Ici on recuperer tout les champs de la class A;

// Ici A n'a qu'un seul champ
Field leChampDeA_monChamp = lesChampsDeA[0];

// On veut savoir si ce champ a l'annotation SVGField ?
SVGField champDeAEnModeSVGField = leChampDeA_monChamp.getAnnotation(SVGField.class); // Le Object.class recupere la class de l'object sans instance c'est un peu comme monA.getClass() mais en mode "static"

if(champDeAEnModeSVGField != null) { // On verifie que le champ exists
    String leMessageDuChamp = champDeAEnModeSVGField.value()[0]; // Car SVGField a un tableau : "String[] value() default {}; "
    System.out.println(leMessageDuChamp) // Affiche "Coucou"
}

```
