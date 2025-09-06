import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true, //componente es standalone
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() { //envio del formulario de login con email y password
    this.authService.login(this.email, this.password).subscribe({ //subscribe para recibir respuesta del backend
      next: (response) => {
        console.log('Login exitoso', response);
        try {
          localStorage.setItem('currentUser', JSON.stringify(response));//guarda usuario en local storage
          console.log('Usuario guardado en Local Storage');
        } catch (error) {
          console.error('Error al guardar en Local Storage', error); // Loguea cualquier error
        }
        this.router.navigate(['/']);
      },
      error: (err) => alert('Credenciales incorrectas')
    });
  }
}