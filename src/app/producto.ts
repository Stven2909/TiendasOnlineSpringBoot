export class Producto {
  id: number;
  nombre: string;
  descripcion: string;
  categoria: string;
  precio: number;
  stock: number;
  image: string;

  constructor(id: number, nombre: string, descripcion: string, categoria: string, precio: number, stock: number, image: string) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.precio = precio;
    this.stock = stock;
    this.image = image;
  }
}