![Java CI](https://github.com/falkoschumann/story-mapping-java/workflows/Java%20CI/badge.svg)

# Story Mapping

Beschreibe die Aktivitäten der Nutzer eines Softwaresystems, plane Release und
organisiere die Implementierung.

## Installation

## Usage

## Contributing

- Der Code Style [Google Java Style Guide][1] wird beim Build geprüft.
- Code formatieren: `./gradlew spotlessApply`
- Release erstellen: `./gradlew jpackage`

### Distribute for macOS

Im Folgenden müssen `$USERNAME` und `$PASSWORD` passend ersetzt werden. 

1. Notarisierung der App-Distribution beantragen: 

    `xcrun altool --notarize-app --primary-bundle-id de.muspellheim.storymapping --username $USERNAME --password $PASSWORD --file story-mapping/build/jpackage/story-mapping-1.0.0.dmg`

2. Status der Notarisierung prüfen:

    `xcrun altool --notarization-info 86c03aea-3f22-4ab8-8249-adee088507dc --username $USERNAME --password $PASSWORD`

3. Wenn Notarisierung beglaubigt, App-Distribution um Information zur Notarisierung ergänzen: 

    `xcrun stapler staple story-mapping/build/jpackage/story-mapping-1.0.0.dmg`


[1]: https://google.github.io/styleguide/javaguide.html
