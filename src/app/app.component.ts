import { Component, OnInit } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router'; // Ensure Router, RouterModule, RouterOutlet are imported
import { CommonModule } from '@angular/common'; // Ensure CommonModule is imported
import { Producto } from './producto'; // Ensure Producto model is imported
import { FormsModule } from '@angular/forms'; // Ensure FormsModule is imported
import { HttpErrorResponse } from '@angular/common/http';
import { ProductoService } from './producto.service'; // This is your main product service for displaying products
import { AuthService } from './auth.service';
import { CarritoService } from './carrito.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, RouterOutlet] // Ensure all modules are in imports
})
export class AppComponent implements OnInit {
  public productos: Producto[] = []; // Array para almacenar los productos
  public carritoItems: Producto[] = []; //Array para almacenar los productos del carrito
  public categories = ['Pantalon', 'Camisa', 'Camison', 'Pantuflas', 'Regalos', 'Conjuntos']; //Array de categorias
  public showLoginModal: boolean = false;
  public loginData = {
    email: "",
    password: ""
  };
  private carritoId: number | null = null;

  constructor(
    // Ensure ProductoService is injected
    private productoService: ProductoService,
    public authService: AuthService,
    private carritoService: CarritoService,
    private router: Router // Ensure Router is injected
  ) {}

  ngOnInit(): void {
    this.getProductos(); // Call getProducts on init
    if (this.authService.isLoggedIn()) {
      this.cargarCarrito();
    }
   }

  private cargarCarrito(): void { //metodo para cargar el carrito del usuario
    if (this.authService.isLoggedIn()) { //verifica si el usuario esta logueado
      console.log('Cargando carrito...');
      this.carritoService.obtenerCarrito().subscribe({
        next: (carrito) => {
          this.carritoId = carrito.id; //almacena el ID del carrito cargadi
          this.carritoItems = carrito.productos || [];  //almacena los productos del carrito o array si esta vacio
          console.log('Carrito actualizado:', this.carritoItems, 'Carrito ID:', this.carritoId); //los registra
        },
        error: (error) => {
          console.error("Error al cargar el carrito:", error);
        }
      });
    } else {
      this.carritoItems = [];
      this.carritoId = null;
      console.log('Usuario no logueado, carrito vacío.');
    }
  }

  
  public getProductos(): void {
    this.productoService.getProductos().subscribe(
      (response: Producto[]) => {
        this.productos = response;
      },
      (error: HttpErrorResponse) => {
        console.error("Error al cargar productos:", error.message);
      }
    );
  }

  public agregarAlCarrito(producto: Producto): void {
    console.log('Agregar al carrito clickeado para:', producto.nombre);
    console.log('Carrito ID antes de agregar:', this.carritoId);
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);
      return;
    }

    if (this.carritoId) {
      this.carritoService.agregarProducto(this.carritoId, producto.id).subscribe({
        next: () => {
          console.log('Producto agregado al carrito:', producto.nombre);
          this.cargarCarrito();
        },
        error: (err) => {
          console.error('Error al agregar al carrito:', err);
        }
      });
    } else {
      console.error('No se pudo obtener el carrito del usuario al agregar.');
    }
  }

  public eliminarDelCarrito(productoId: number): void {
    if (!this.authService.isLoggedIn() || !this.carritoId) return;

    this.carritoService.eliminarProducto(this.carritoId, productoId).subscribe({
      next: () => {
        console.log('Producto eliminado del carrito');
        this.carritoItems = this.carritoItems.filter(item => item.id !== productoId);
      },
      error: (err) => {
        console.error('Error al eliminar del carrito:', err);
      }
    });
  }

  getImageUrl(image: string): string {
    return image ? `assets/imagenes/${image}` : 'assets/imagenes/default.jpg';
  }

  openLogin(): void {
    this.showLoginModal = true;
  }

  closeLogin(): void {
    this.showLoginModal = false;
  }

  //metodo que se llama cuando se envia el formulario de login
  onLoginSubmit(): void {
    //llama al servicio de autenticacion para enviar los datos de login al backend
    this.authService.login(this.loginData.email, this.loginData.password).subscribe({
      next: (response) => {
        console.log('Login exitoso', response);
        this.closeLogin();
        this.cargarCarrito();
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Error en el login', error);
      }
    });
  }

  //metodo para navegar a la pagina de registro
  openRegister(event: Event): void {
    event.preventDefault();
    this.router.navigate(['/register']);
    this.closeLogin();
  }

  //Este metodo verifica si la ruta actual es una ruta especial para aplicar estilos de navegación
  //Esto es para que el navbar se vea diferente en la ruta de carrito, productos, login y registr
  isSpecialRoute(): boolean {
    const currentUrl = this.router.url;
    //verifica si la ruta actual es alguna de las rutas especiales 
    return currentUrl === '/carrito' || currentUrl === '/productos' || currentUrl === '/login' || currentUrl === '/register';
  }
}