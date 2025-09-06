// factura.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

export interface Producto {
  id: number;
  nombre: string;
  descripcion: string;
  precio: number;
  image: string;
}

export interface Factura {
  id: number;
  usuarioId: number;
  productos: Producto[];
  total: number;
  fecha: string;
}

@Injectable({
  providedIn: 'root'
})
export class FacturaService {
  private apiUrl = `${environment.apiBaseUrl}/api/facturas`;

  constructor(private http: HttpClient) {}

  crearFactura(factura: Factura): Observable<Factura> {
    return this.http.post<Factura>(this.apiUrl, factura);
  }

  // Usar con POST /api/facturas/usuario/{usuarioId}
  crearFacturaDesdeCarrito(usuarioId: number): Observable<Factura> {
    return this.http.post<Factura>(`${this.apiUrl}/usuario/${usuarioId}`, {});
  }
}