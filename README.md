# Challenge-Alkemy-AppDisney
# Que es

Este es un sistema basico que cuenta con peliculas o series (Films), personajes y generos. Cada Film tiene un Genero, cada Genero puede tener varios Films, cada Film puede tener varios Characters y cada Character puede pertenecer a varios Films

<img align="center" src="" />

## Como funciona


### Navegador

film

para ver la lista desordenada:

```
http://localhost:8080/films
```

para ver la lista ordenada de manera ascendente:

```
http://localhost:8080//fims?order=ASC 
```
para ver la lista ordenada de manera ascendente:

```
http://localhost:8080//fims?order=DESC 
```
para ver un objeto en particular:

```
http://localhost:8080//movies?id={}
```

### API

-film-
#### GET

todos los Films:

```
http://localhost:8080/api/
```

un solo Film:

```
http://localhost:8080/api/
```

#### POST

```
http://localhost:8080/api/

JSON BODY:

```
    {
      
    }
```

#### PUT

```
http://localhost:8080/api/
```

JSON BODY:

```
    {
       
    }
```

#### DELETE

```
http://localhost:8080/api
```
