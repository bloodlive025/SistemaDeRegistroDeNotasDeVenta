CREATE TABLE detallenotasdeventa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nota_de_venta_id BIGINT,
    producto_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (nota_de_venta_id) REFERENCES notasdeventa(id) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);