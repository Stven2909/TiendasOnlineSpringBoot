// src/app/productos/productos.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ProductoService } from './producto.service';
import { Producto } from './producto';
import { AuthService } from './auth.service';
@Component({
    selector: 'app-productos',
    standalone: true,
    imports: [CommonModule, FormsModule, RouterModule],
    templateUrl: './productos.component.html',
    styleUrls: ['./productos.component.css']
  })
  export class ProductosComponent implements OnInit {
    productos: Producto[] = [];
    selectedProducto: Producto | null = null;
    newProducto: Producto = new Producto(0, '', '', '', 0, 0, '');
    loading: boolean = false;
    errorMessage: string | null = null;
    showForm: boolean = false;
  
    constructor(private productoService: ProductoService) {}
  
    ngOnInit(): void {
      this.cargarProductos();
    }
  
    //Carga todos los productos
    cargarProductos(): void {
      this.loading = true;
      this.errorMessage = null;
      this.productoService.getProductos().subscribe({
        next: (productos) => { //en caso de exito
          this.productos = productos; //asigna los productos a la lista de productos
          this.loading = false;
          console.log('ProductosComponent: productos:', productos);
        },
        error: (error) => {
          console.error('ProductosComponent: error al obtener productos', error);
          this.errorMessage = 'Error al cargar los productos';
          this.loading = false;
        }
      });
    }
  
    //Selecciona un producto para mostrar en el formulario y editarlo
    seleccionarProducto(producto: Producto): void {
      this.selectedProducto = new Producto(
        producto.id,
        producto.nombre,
        producto.descripcion,
        producto.categoria,
        producto.precio,
        producto.stock,
        producto.image
      );
      this.showForm = true;
    }
  
    //Crea un nuevo producto
    crearProducto(): void {
      if (this.validarProducto(this.newProducto)) {
        this.productoService.addProducto(this.newProducto).subscribe({
          next: (productoCreado) => {
            this.productos.push(productoCreado);
            this.newProducto = new Producto(0, '', '', '', 0, 0, '');
            this.showForm = false;
            console.log('ProductosComponent: nuevo producto:', productoCreado);
          },
          error: (error) => {
            console.error('ProductosComponent: error al crear:', error);
            this.errorMessage = 'Error al crear el producto';
          }
        });
      } else {
        this.errorMessage = 'Por favor, completa todos los campos requeridos';
      }
    }
  
    //Actualiza un producto especifico
    actualizarProducto(): void {
      if (this.selectedProducto && this.validarProducto(this.selectedProducto)) { //Valida el producto seleccionado
        this.productoService.updateProducto(this.selectedProducto).subscribe({ //Llama al servicio para actualizar el producto
          next: (productoActualizado) => {
            const index = this.productos.findIndex(p => p.id === productoActualizado.id);
            if (index !== -1) {
              this.productos[index] = productoActualizado;
            }
            this.selectedProducto = null;
            this.showForm = false;
            console.log('ProductosComponent: producto actualizado:', productoActualizado);
          },
          error: (error) => {
            console.error('ProductosComponent: error al actualizar:', error);
            this.errorMessage = 'Error al actualizar el producto';
          }
        });
      } else {
        this.errorMessage = 'Por favor, completa todos los campos requeridos';
      }
    }
  
    //Elimina un producto especifico
    eliminarProducto(id: number): void {
      if (confirm('¿Estás seguro de eliminar este producto?')) {
        this.productoService.deleteProducto(id).subscribe({
          next: () => {
            this.productos = this.productos.filter(p => p.id !== id);
            console.log('ProductosComponent: producto eliminado:', id);
          },
          error: (error) => {
            console.error('ProductosComponent: error al eliminar:', error);
            this.errorMessage = 'Error al eliminar el producto';
          }
        });
      }
    }
  
    //Valida que el producto tenga todos los campos requeridos
    validarProducto(producto: Producto): boolean {
      return (
        producto.nombre.trim() !== '' &&
        producto.descripcion.trim() !== '' &&
        producto.categoria.trim() !== '' &&
        producto.precio > 0 &&
        producto.stock >= 0 &&
        producto.image.trim() !== ''
      );
    }
  
    //Muestra el formulario para crear un nuevo producto
    mostrarFormularioCrear(): void {
      this.selectedProducto = null;
      this.newProducto = new Producto(0, '', '', '', 0, 0, '');
      this.showForm = true;
    }
  
    //Cancelar la operacion del formulario
    cancelarFormulario(): void {
      this.showForm = false;
      this.selectedProducto = null;
      this.errorMessage = null;
    }
  
    //Obtiene la URL de la imagen del producto
    getImageUrl(image: string): string {
      return image ? `assets/imagenes/${image}` : 'assets/imagenes/default.jpg';
    }
  }