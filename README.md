# Angry Bird Game

A simple 2D Angry Birds–style game implemented in Java. This project contains the game code, assets (sprites, sounds), and basic physics to launch birds, destroy structures, and score points.

> Note: This README is a general, ready-to-use template. Update sections marked with placeholders (e.g., Main class name, build system) if your repository uses a specific project structure or build tool.


## Features
- Launch multiple birds with mouse drag-and-release slingshot mechanic
- Destructible structures and collision detection
- Score tracking and simple level progression
- Simple particle / sound effects (if included)
- Keyboard shortcuts for quick restart or next level

## Requirements
- Java 8 or later (recommend Java 11+)

- jbox2d 2.2.1 (physics)
- javax.sound (audio)

## Getting started

### Clone the repo
```bash
git clone https://github.com/TamimCHowdhury61/Angry-Bird-game.git
cd Angry-Bird-game
```

### Build and run (plain Java / javac)

1. Compile:
```bash
# create output directory and compile all source files (adjust path if different)
mkdir -p out
find src -name "*.java" > sources.txt
javac -d out @sources.txt
```

2. Package (optional):
```bash
# create an executable jar (replace <MainClass> with your actual main class)
jar cfe AngryBirds.jar <MainClass> -C out .
```

3. Run:
```bash
java -cp out <MainClass>
# or if you created a jar:
java -jar AngryBirds.jar
```


### Build and run (Maven or Gradle)


Gradle:
```bash
./gradlew build
java -jar build/libs/angry-bird-game.jar
```


### Run from IDE (IntelliJ IDEA / Eclipse)
- Import the project as a Java project.
- Ensure the project's SDK is set (Java 8+).
- Locate the class with `public static void main(String[] args)` and run it using the IDE run configuration.

## Gameplay / Controls
- Aim and launch: Click and drag the mouse on the bird, then release to launch.
- Restart level: R
- Next level: N (if implemented)
- Toggle sound: M
- Pause: P or Esc


## Project structure

A typical layout :
- src/ - Java source files
- assets/ or resources/ - images, sounds, level data
- docs/ - screenshots, design notes
- out/ or build/ - compiled output (ignored in VCS)
- README.md - this file

Example:
```
src/
  com/
    example/
      angrybirds/
        Main.java
        game/
        physics/
assets/
  images/
  sounds/
docs/
  screenshot-1.png
```



## Known issues & roadmap
- Planned improvements:
  - Add more levels and level editor
  - Improve physics with a 3rd-party physics engine (if not used)
  - Add mobile controls / touch support
  - Add unit tests and CI (GitHub Actions)

## Testing

```bash
mvn test
# or
./gradlew test
```


## Contact
Project maintained by TamimCHowdhury61.

If you want to reach out:
- GitHub: https://github.com/TamimCHowdhury61
- Email: tamim23544@iiitd.ac.in

## Acknowledgements
- Inspired by the original Angry Birds game

