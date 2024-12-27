# Lab3_207823589_VicenteCarrascoAlvarez
Conecta4 en Java 11

## Esquema del proyecto

* [X] Código en Java 11
  * [X] TDA Player
  * [X] TDA Board
  * [X] TDA Game
  * [X] TDA Piece
* [X] Scripts de prueba
* [X] Integración con gradle
* [X] Diagramas UML de Clases y Objetos **(Plantuml)**
* [X] Informe **(pandoc)**
* [X] Autoevaluación y extras

## Cómo compilar y ejecutar

En GNU/Linux, o sistemas *nix:

```
./gradlew build
./gradlew run
```

En Windows:

```
gradlew.bat build
gradlew.bat run
```

En ambos casos, seguir las instrucciones del menú interactivo.

### Nota

Alternativamente, es posible compilar el programa construyendo 
el Artifact jar en IntelliJ, mediante la opción en el menú Build:
"Build Artifacts", y luego ejecutando el .jar correspondiente.

## Código del informe y diagramas

Este proyecto usa *pandoc* y *plantuml* para generar el informe 
y los diagramas UML, correspondientemente.

Para generar los diagramas UML:

```
plantuml uml*.txt
```

Para generar el informe, con dos columnas:

```
pandoc -f markdown informe.md -V geometry:margin=2.5cm -V lang=es -V fontsize=11pt --bibliography=referencias.bib --citeproc --toc -V classoption:twocolumn -o informe_20782358_CarrascoAlvarez.pdf
```

Se puede renderizar también en formato estándar:

```
pandoc -f markdown informe.md -V geometry:margin=2.5cm -V lang=es -V fontsize=11pt --bibliography=referencias.bib --citeproc --toc -o informe_20782358_CarrascoAlvarez.pdf
```
