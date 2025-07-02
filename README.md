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
Primero debemos haber creado una red Docker con:
- `docker network create relatos-net`
### Construir la imagen
- `docker build -t ms-books-payments .`
### Ejecutar el microservicio usando Docker
- `docker run --detach --network relatos-net --publish 8081:8081 ms-books-payments`