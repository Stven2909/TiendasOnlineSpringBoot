import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Carrito } from './carrito';
import { Producto } from './producto';
import { environment } from "../environments/environment";
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private apiServerUrl = `${environment.apiBaseUrl}/api/carritos`;

  constructor(
    private http: HttpClient,
    private authService: AuthService // Inyecta AuthService
  ) {}

  // Obtiene el carrito del usuario actual
public obtenerCarrito(): Observable<Carrito> {
  const usuarioId = this.authService.getCurrentUserId();
  return this.http.get<Carrito>(`${this.apiServerUrl}/usuario/${usuarioId}`);
}

// Método para crear un carrito solo si no existe
public crearCarritoSiNoExiste(): Observable<Carrito> {
  const usuarioId = this.authService.getCurrentUserId();
  return this.http.post<Carrito>(`${this.apiServerUrl}/usuario/${usuarioId}`, null);
}

// Agrega producto al carrito del usuario actual
public agregarProducto(carritoId: number, productoId: number): Observable<void> {
  return this.http.post<void>(
    `${this.apiServerUrl}/${carritoId}/productos/${productoId}`,
    null
  );
}

// Elimina producto del carrito del usuario actual
public eliminarProducto(carritoId: number, productoId: number): Observable<void> {
  return this.http.delete<void>(
    `${this.apiServerUrl}/${carritoId}/productos/${productoId}`
  );
}

//Vaciar el carrito del usuario actual
public vaciarCarritoCompleto(): Observable<void> {
  const usuarioId = this.authService.getCurrentUserId();
  return this.http.delete<void>(`${this.apiServerUrl}/usuario/${usuarioId}`);
}
}