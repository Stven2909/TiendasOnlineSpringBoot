import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { CarritoComponent } from './carrito.component';
import { LoginComponent } from './login.component'; 
import { ProductosComponent } from './productos.component';
import { RegisterComponent } from './register.component';

export const routes: Routes = [
  { path: '', component: AppComponent },
  { path: 'carrito', component: CarritoComponent },
  { path: 'login', component: LoginComponent }, 
  { path: 'register', component: RegisterComponent },
  { path: 'productos', component: ProductosComponent },
  { path: '**', redirectTo: '' } // manejo de rutas no encontradas
];