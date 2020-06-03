# Bysykkel sanntidsdata

Dette repositoryet inneholder et REST-api som viser data fra Oslo Bysykkels API. Eksempel

```bash
./gradlew run
---------------------------------------------------------------------
Stasjon                              Ledige sykler       Ledige låser
---------------------------------------------------------------------
7 Juni Plassen                       0                   21
Adamstuen                            4                   4
Aker Brygge                          11                  22
Akersgata                            1                   20
<mange flere rader>
```

## Kjør prosjektet

```bash
./gradlew run
```

Dersom dette mot formodning ikke funker, kan man kjøre følgende kommando i stedet:

```bash
docker run -p 8080:8080 yngvark/bysykkel_sanntidsdata:0.0.1
```

## Test API-et

```bash
curl http://localhost:8080/station_availability | jq
```

jq: https://stedolan.github.io/jq/download/

## Kjør tester

```bash
./gradlew test
```

Hvis gradle har allerede kjørt testene, kan testene force kjøres på nytt med output slik:

```bash
./gradlew --rerun-tasks --info test
```
