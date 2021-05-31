# Challenge-Alkemy-AppDisney
# Que es

Este es un sistema basico que cuenta con peliculas o series (Films), personajes y generos. Cada Film tiene un Genero, cada Genero puede tener varios Films, cada Film puede tener varios Characters y cada Character puede pertenecer a varios Films

<img align="center" src="https://user-images.githubusercontent.com/36177129/120087813-9bcc2300-c0c1-11eb-937f-e69251bdedec.png" />

## Como funciona
Hay dos formas de usar la aplicacion, para ver los datos, podemos verlos directamente por el navegador, y si ademas queremos hacer otras operaciones como alta, baja o modificacion, podemos usar la API.

### Navegador
Contamos con 3 funcionalidades, ver la lista desordenada, ver la lista ordenada, ver un objeto en particular. Para mostrar las urls, utilizaremos "/character", pero tambien podemos utilizar "/film" y "/gender"
film
Los endpoints encargados de la autenticación son:

```
http://localhost:8080/films/auth/login
```
```
http://localhost:8080/films/auth/register
```

para ver la lista desordenada:

```
http://localhost:8080/films/
```
para ver la lista ordenada:

```
http://localhost:8080/films/sorted
```

para ver la lista ordenada de manera ascendente:

```
http://localhost:8080/fims?order=ASC 
```
para ver la lista ordenada de manera ascendente:

```
http://localhost:8080/fims?order=DESC 
```
para ver un objeto en particular:

```
http://localhost:8080/movies?id={id}
```

### API
Desde la api podemos utilizar get, post, put y delete. A continuacion se muestran ejemplos utilizando Film, pero tambien funciona de la misma manera para character y gender.

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

Bbuscar por nombre, y filtrar por edad, peso o películas/series en las que participó.
Para especificar el término de búsqueda o filtros se deberán enviar como parámetros de query:
● /characters?name=nombre
● /characters?age=edad
● /characters?movies=idMovie
