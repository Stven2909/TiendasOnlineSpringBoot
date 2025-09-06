import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Usuario } from './usuario';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  usuario: Usuario = {
    nombre: '',
    apellido: '',
    email: '',
    password: '',
    direccion: '',
    telefono: ''
  };
  confirmPassword: string = '';
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit(): void {
    // Validar que las contraseñas coincidan
    if (this.usuario.password !== this.confirmPassword) {
      this.errorMessage = 'Las contraseñas no coinciden';
      return;
    }

    // Validar campos obligatorios
    if (!this.usuario.nombre || !this.usuario.apellido || !this.usuario.email || !this.usuario.password) {
      this.errorMessage = 'Por favor, completa todos los campos obligatorios';
      return;
    }

    // Enviar solicitud al servidor
    this.http.post('http://localhost:8502/service-main/api/auth/register', this.usuario)
      .subscribe({
        next: (response) => {
          this.successMessage = 'Usuario registrado correctamente';
          this.errorMessage = null;
          // Redirigir al login después de 2 segundos
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 2000);
        },
        error: (error) => {
          console.error('Error al registrar usuario:', error);
          this.errorMessage = error.error?.message || 'Error al registrar usuario';
          this.successMessage = null;
        }
      });
  }

  goToLogin(): void {
    this.router.navigate(['/login']);
  }
}