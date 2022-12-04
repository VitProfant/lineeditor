
# lineeditor

A line-oriented text editor that reads a text file and allows basic editing commands.


## Build

This is a java project. First make sure that you have a JDK in version 11 or more properly installed.

To build this project run

- on Linux

```bash
  ./gradlew distZip
```

- on Windows

```bash
  gradlew distZip
```

## Installation

In the `build/distributions` directory you'll find a ZIP file with the needed files.

Unzip the ZIP file to the directory where you want to run the program.

## Running

To execute the editor, in the `bin` subdirectory run

- on Linux

```bash
  ./lineeditor [path_to_the_file]
```

- on Windows

```bash
  lineeditor [path_to_the_file]
```
