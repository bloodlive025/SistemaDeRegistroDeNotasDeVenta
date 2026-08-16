SISTEMA DE REGISTRO DE PRODUCTOS PARA UN ECOMMERCE

Casos de uso: Crear Nota de venta
- Se obtiene el nombre del cliente como un string, los productos como una tupla de id, y cantidad.

Mensaje Http para crear nota de venta:
{
  "cliente": "Raul",
  "productos": [
    { "productoId": 1, "cantidad": 2 },
    { "productoId": 3, "cantidad": 1 }
  ]
}

- Verifica que exista un producto con ese id.
- Si existe entonces crea una nota de venta de este producto
- Aun no hay verificacion de inventariado
-Aun no hay relacion entre la nota de venta y el cliente, que deberia ser muchos a uno.



