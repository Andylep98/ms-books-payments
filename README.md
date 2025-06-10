# ms-books-payments

**Construir la Imagen**
- `docker build -t ms-books-payments .`

**Ejecutar la Imagen**
- `docker run -d -p 8081:8081 ms-books-payments`

**Endpoint**
- `/api/purchases`: para crear una compra se necesita usar POST con un body que contenga `bookId` y `quantity`, ejemplo: 

```
{
    "bookId": 1,
    "quantity": 5
}
```
