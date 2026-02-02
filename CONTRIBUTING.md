# Guide de contribution

## Règles générales

- Ne travaillez pas sur les mêmes fichiers en même temps que d'autres contributeurs. répartissez vous les tâches par formes géométriques (carré, rectangle, cercle, etc.)
- Ne créé pas de branches autre que `main` pour simplifier la gestion du code.

## Organisation du travail

### Conventions de code

> Suivez les conventions de code suivantes pour éviter les conflits et faciliter la relecture du code.

#### Java

- Installer [les extentions recommandées pour VSCode](.vscode/extensions.json)
- Indentation : **2 espaces**
- Noms de classes : `MyClasse`
- Noms de méthodes et variables : `camelCase`
- Une classe = un fichier

#### Formatage

- Utiliser le **formatage automatique dans VS Code**
- **Ne pas committer de code non formaté !!**

## Tests

> Tests unitaires avec JUnit

- Vérifiez que le projet compile avant chaque commit
- Ajoutez des tests pour des features qui sont "testables"

## Message de commits

> Ecrivez des messages de commit clairs et concis en anglais, en utilisant les préfixes suivants :

| Reparer un bug            | Ajouter une fonctionnalité | Refactorer du code             | Mettre à jour la documentation |
| ------------------------- | -------------------------- | ------------------------------ | ------------------------------ |
| `fix: description courte` | `feat: description courte` | `refactor: description courte` | `docs: description courte`     |

Rappel des [commandes de git](https://git-scm.com/cheat-sheet).
Rappel de l'utilisation de git dans [VSCode](https://code.visualstudio.com/docs/sourcecontrol/quickstart).

## TODOs

> Ajoutez des TODOs dans le code pour les tâches à faire plus tard, en utilisant le format suivant :

```java
public void maMethode() {
  // TODO: Implémenter cette méthode
}
```

Pour voir la liste des TODO dans VSCode ouvrez le terminal et aller dans l'onglet "Problèmes" (ou utilisez le raccourci `Ctrl+Shift+M`).
Ensuite filtez par "TODO" pour voir toutes les tâches en attente.
