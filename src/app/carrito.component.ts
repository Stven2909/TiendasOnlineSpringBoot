import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarritoService } from './carrito.service';
import { FacturaService, Factura, Producto } from './factura.service'; //Importar FacturaService
import { Carrito } from './carrito';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from './auth.service'; //Para obtener usuarioId

@Component({
  selector: 'app-carrito',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})

//Propiedades o variables principales del carrito
export class CarritoComponent implements OnInit {
  carrito: Producto[] = [];
  carritoId: number | null = null;
  total: number = 0;
  loading: boolean = true;
  errorMessage: string | null = null;

  // Variables para la factura
  mostrarFactura: boolean = false;
  fechaFactura: Date = new Date();
  numeroFactura: string = '';
  facturaId: number | null = null; // Para almacenar el ID de la factura creada

  constructor(
    private carritoService: CarritoService,
    private facturaService: FacturaService, //Inyectar FacturaService
    private authService: AuthService, // Inyectar AuthService
    private router: Router
  ) {}

  //inicia el componente cargando el carrito
  ngOnInit(): void {
    this.cargarCarrito();
  }

  //obtiene los datos del carrito del servidor
  cargarCarrito(): void {
    this.loading = true;
    this.errorMessage = null;
    console.log('CarritoComponent: Obtener carrito...');

    this.carritoService.obtenerCarrito().subscribe({
      next: (carritoData: Carrito) => {
        if (carritoData) {
          this.carrito = carritoData.productos || [];
          this.carritoId = carritoData.id;
          this.calcularTotal();
          console.log('CarritoComponent: Carrito obtenido:', this.carrito, 'Carrito ID:', this.carritoId);
        } else {
          console.log('CarritoComponent: No se encontró carrito, creando uno nuevo...');
          this.crearCarrito();
        }
        this.loading = false;
      },
      error: (error) => {
        console.error('CarritoComponent: Error al obtener el carrito', error);
        this.errorMessage = 'Error al cargar el carrito';
        this.loading = false;
      }
    });
  }

  //crea un carrito si no existe
  crearCarrito(): void {
    this.carritoService.crearCarritoSiNoExiste().subscribe({
      next: (carritoData: Carrito) => {
        this.carrito = carritoData.productos || [];
        this.carritoId = carritoData.id;
        this.calcularTotal();
        console.log('CarritoComponent: Carrito creado:', this.carrito, 'Carrito ID:', this.carritoId);
      },
      error: (error) => {
        console.error('CarritoComponent: Error al crear el carrito', error);
        this.errorMessage = 'Error al crear el carrito';
      }
    });
  }

  //Elimina un producto especifico del carrito
  eliminarProducto(productoId: number): void {
    if (this.carritoId) {
      console.log('CarritoComponent: Eliminando producto', productoId, 'del carrito', this.carritoId);
      this.carritoService.eliminarProducto(this.carritoId, productoId).subscribe({
        next: () => {
          console.log('CarritoComponent: Producto eliminado');
          this.cargarCarrito();
        },
        error: (error) => {
          console.error('CarritoComponent: Error al eliminar producto', error);
          this.errorMessage = 'Error al eliminar el producto';
        }
      });
    } else {
      console.error('CarritoComponent: No se pudo obtener el ID del carrito para eliminar.');
      this.errorMessage = 'No se pudo eliminar el producto del carrito.';
    }
  }

  //Calcula el total del carrito
  calcularTotal(): void {
    this.total = this.carrito.reduce((sum, producto) => sum + producto.precio, 0);
  }

  //Elimina todos los productos del carrito
  vaciarCarrito(): void {
    if (this.carritoId) {
      this.carritoService.vaciarCarritoCompleto().subscribe({
        next: () => {
          this.carrito = [];
          this.calcularTotal();
          console.log('CarritoComponent: Carrito vaciado en el backend');
        },
        error: (error) => {
          console.error('CarritoComponent: Error al vaciar el carrito', error);
          this.errorMessage = 'Error al vaciar el carrito';
        }
      });
    } else {
      console.log('CarritoComponent: No se ha cargado el carrito.');
    }
  }

  // Generar factura con POST /api/facturas para el carrito/compra actual
  generarFactura(): void {
    if (this.carrito.length > 0) {
      this.fechaFactura = new Date();
      this.numeroFactura = 'F-' + Date.now().toString().slice(-8);

      // Crear la factura en el backend
      const factura: Factura = {
        id: 0,
        usuarioId: this.authService.getCurrentUserId(), // Usar AuthService
        productos: this.carrito,
        total: this.total,
        fecha: this.fechaFactura.toISOString().split('T')[0] // Formato YYYY-MM-DD
      };

      this.facturaService.crearFactura(factura).subscribe({
        next: (facturaCreada: Factura) => {
          this.facturaId = facturaCreada.id;
          this.mostrarFactura = true;
          console.log('CarritoComponent: Factura creada en el backend:', facturaCreada);
        },
        error: (error) => {
          console.error('CarritoComponent: Error al crear la factura', error);
          this.errorMessage = 'Error al generar la factura';
        }
      });
    } else {
      this.errorMessage = 'No hay productos en el carrito';
    }
  }

  //cierra la vista de la factura
  cerrarFactura(): void {
    this.mostrarFactura = false;
  }

  imprimirFactura(): void {
    window.print();
  }

  //Finaliza el proceso de compra y limpia el carrito una vez la compra se ha hecho
  finalizarCompra(): void {
    if (this.carritoId) {
      this.carritoService.vaciarCarritoCompleto().subscribe({
        next: () => {
          this.carrito = [];
          this.calcularTotal();
          this.mostrarFactura = false;
          this.router.navigate(['/']);
          alert('Compra realizada con éxito');
          console.log('CarritoComponent: Compra finalizada y carrito vaciado en el backend');
        },
        error: (error) => {
          console.error('CarritoComponent: Error al finalizar la compra', error);
          this.errorMessage = 'Error al finalizar la compra';
        }
      });
    }
  }

  //Obtiene la  URL de la imagen del producto
  getImageUrl(image: string): string {
    return image ? `assets/imagenes/${image}` : 'assets/imagenes/default.jpg';
  }
}