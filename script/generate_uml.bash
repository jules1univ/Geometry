#!/bin/bash

# Accorder les droit au script bash
# Ne pas oublier de faire: chmod +x generate_docs.bash


SRC_DIR="src"
DOC_DIR="uml"

PLANTUML_JAR="lib/plantuml.jar"

if [ ! -f "$PLANTUML_JAR" ]; then
    echo "plantuml.jar introuvable"
    exit 1
fi

if [ ! -d "$SRC_DIR" ]; then
    echo "Dossier src introuvable"
    exit 1
fi

mkdir -p "$DOC_DIR"
echo "Génération des diagrammes UML depuis '$SRC_DIR' vers '$DOC_DIR'..."

java -jar "$PLANTUML_JAR" \
     -recursive \
     "$SRC_DIR" \
     -o "../../$DOC_DIR"

if [ $? -eq 0 ]; then
    echo "Diagrammes UML générés avec succès dans '$DOC_DIR'"
else
    echo "Erreur lors de la génération des diagrammes UML !"
fi
