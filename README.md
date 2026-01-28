# Projet Geométrie (GEN)


## Utilisation de Git

> Prérequis

- Git installé
- VS Code (recommandé)
- Un compte GitHub avec accès au dépôt


> Cloner le dépôt

```bash
git clone https://github.com/jules1univ/GEN.git
cd GEN
```

> Récupérer les dernières modifications 

**Il faut le faire avant de commencer à travailler.**

```bash
git pull origin main
```

> Ajouter des modifications

```bash
git add .
git commit -m "ajoute de <ma forme>"
```

> Envoyer ces modifications

```bash
git push origin main
```

> Le push ne fonctionne pas ?

```bash
git pull --no-rebase --tags origin main
git pull
git push origin main
```
