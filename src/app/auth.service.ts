import { Injectable } from '@angular/core';
 import { HttpClient, HttpParams } from '@angular/common/http';
 import { Router } from '@angular/router';
 import { tap } from 'rxjs/operators'; // Importa el operador tap

 @Injectable({ providedIn: 'root' })
 export class AuthService {
  private apiUrl = 'http://localhost:8502/service-main/api/auth'; // 

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string) { //metodo para login
   const params = new HttpParams() //parametros http para enviar al backend
    .set('email', email)
    .set('password', password);
   return this.http.post(`${this.apiUrl}/login`, null, { params: params }).pipe( //envia peticion POST al endpoint
    tap(response => {
     localStorage.setItem('currentUser', JSON.stringify(response)); // Guarda la respuesta del usuario
    })
   );
  }

  // Método para logout
  logout(): void {
   localStorage.removeItem('currentUser');
   this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean { //verifica si el usuario esta logueado
   return !!localStorage.getItem('currentUser');
  }

  getCurrentUserId(): number { //Obtiene el ID del usuario actual
   const user = JSON.parse(localStorage.getItem('currentUser') || '{}');
   return user?.id || 1; // Retorna 1 como valor por defecto si no hay usuario
  }

}