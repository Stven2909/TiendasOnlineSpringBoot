// src/app/producto.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Producto } from './producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  private apiServerUrl = environment.apiBaseUrl; //url base del backend desde el entorno

  constructor(private http: HttpClient) {} //Inyecta el HTTP

  public getProductos(): Observable<Producto[]> { //Obtiene todos los productos
    //peticion GET a la url base del backend
    return this.http.get<Producto[]>(`${this.apiServerUrl}/api/v1/productos/obtenerTodosLosProductos`);
  }

  //Agrega un nuevo producto
  public addProducto(producto: Producto): Observable<Producto> {
    //peticion POST a la url base del backend
    return this.http.post<Producto>(`${this.apiServerUrl}/api/v1/productos/crearProducto`, producto);
  }

  //Actualiza un producto existente
  public updateProducto(producto: Producto): Observable<Producto> {
    //peticion PUT a la url base del backend
    return this.http.put<Producto>(`${this.apiServerUrl}/api/v1/productos/${producto.id}/actualizarProducto`, producto);
  }

  //Elimina un producto existente
  public deleteProducto(productoId: number): Observable<void> {
    //peticion DELETE a la url base del backend
    return this.http.delete<void>(`${this.apiServerUrl}/api/v1/productos/${productoId}/eliminarProducto`);
  }

  //Obtiene un producto por su ID
  public obtenerProductoPorId(productoId: number): Observable<Producto> {
    //peticion GET a la url base del backend
    return this.http.get<Producto>(`${this.apiServerUrl}/api/v1/productos/${productoId}/obtenerProducto`);
  }
}