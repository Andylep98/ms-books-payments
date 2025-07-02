# ms-books-payments

## Requisitos para ejecutar el microservicio
**Java 21**
- Necesitas tener instalado openjdk21.
- Si no tienes Java 21, puedes instalarlo con Homebrew:

```
brew install openjdk@21
```
- Ejecuta:
```
export JAVA_HOME="/opt/homebrew/opt/openjdk@21"
export PATH="$JAVA_HOME/bin:$PATH"
```
- Verifica tu versión con: debe ser la 21
 ```
 java -version
 ```

**Maven**
- Necesitas Maven para compilar y ejecutar el proyecto. Puedes usar el wrapper incluido (`./mvnw`) o instalar Maven:
```
brew install maven
```
- Verifica Maven con:
```
mvn -version
```

**Puerto 8081 libre**
- El microservicio usa el puerto 8081. Asegúrate de que esté disponible.

## Pasos para ejecutar

- Abre una terminal en la raíz del proyecto y ejecuta:
```
mvn spring-boot:run
```

- Utiliza el siguiente endpoint:
```
/api/purchases
```
- Para ver todas las compras se necesita usar GET
- Para crear una compra se necesita usar POST con un body que contenga `bookId` y `quantity`, ejemplo: 

```
{
    "bookId": 1,
    "quantity": 5
}
```

## Docker
### Construir la imagen
- `docker build -t ms-books-payments .`
### Obtener IP de Eureka
- Para obtener la IP de Eureka debemos ejecutar el siguiente comando, donde `<<container-id>>` lo podemos obtener ejecutando `docker ps`.
  ```
  docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' <<container-id>>
  ```
### Ejecutar el microservicio usando Docker
- Para ejecutar el microservicio ejecutamos el siguiente comando, donde `<<IP_EUREKA>>` es la IP obtenida anteriormente.
  ```
  docker run -p 8081:8081 -e EUREKA_URL=http://<<IP_EUREKA>>:8761/eureka ms-books-payments
  ```