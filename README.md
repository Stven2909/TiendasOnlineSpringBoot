# LunaStore

```bash# 🛍️ Luna Store SV - Frontend

[![Angular](https://img.shields.io/badge/Angular-17-red.svg)](https://angular.io/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.x-blue.svg)](https://www.typescriptlang.org/)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5-purple.svg)](https://getbootstrap.com/)
[![License](https://img.shields.io/badge/License-Academic-red.svg)](#)

**Luna Store SV Frontend** es una aplicación web desarrollada con **Angular** que proporciona una interfaz moderna e intuitiva para un sistema de comercio electrónico. Ofrece una experiencia de usuario completa para la navegación de productos, gestión de carritos y procesamiento de órdenes.

---

## 📋 Tabla de Contenidos

- [🚀 Tecnologías](#-tecnologías)
- [✨ Características](#-características)
- [📂 Estructura del Proyecto](#-estructura-del-proyecto)
- [⚙️ Configuración](#️-configuración)
- [🚀 Instalación y Ejecución](#-instalación-y-ejecución)
- [🔗 Integración con Backend](#-integración-con-backend)
- [📱 Características Responsive](#-características-responsive)
- [🚀 Despliegue](#-despliegue)
- [🤝 Contribución](#-contribución)
- [📄 Licencia](#-licencia)

---

## 🚀 Tecnologías

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| **Angular** | 17 | Framework principal de desarrollo |
| **TypeScript** | 5.x | Lenguaje de programación tipado |
| **HTML5** | - | Estructura y semántica |
| **CSS3/SCSS** | - | Estilos y animaciones |
| **Bootstrap** | 5 | Framework CSS responsive |
| **RxJS** | - | Programación reactiva |
| **Angular CLI** | - | Herramientas de desarrollo |

---

## ✨ Características

### 🛒 Funcionalidades de E-commerce
- **Catálogo de Productos**: Navegación intuitiva con filtros y búsqueda
- **Carrito de Compras**: Gestión completa con actualización en tiempo real
- **Gestión de Usuarios**: Registro, login y perfiles de cliente
- **Sistema de Facturación**: Proceso de checkout y generación de facturas
- **Historial de Compras**: Seguimiento de pedidos anteriores

### 💻 Características Técnicas
- **SPA (Single Page Application)**: Navegación fluida sin recargas
- **Responsive Design**: Adaptado para móviles, tablets y desktop
- **Comunicación API REST**: Integración completa con el backend
- **Gestión de Estado**: Manejo eficiente del estado de la aplicación
- **Lazy Loading**: Carga optimizada de componentes

---

## 📂 Estructura del Proyecto

```
src/app/
├── 📁 components/          # Componentes reutilizables
│   ├── header/
│   ├── footer/
│   ├── product-card/
│   ├── shopping-cart/
│   └── loading-spinner/
├── 📁 pages/               # Páginas principales
│   ├── home/
│   ├── products/
│   ├── product-detail/
│   ├── cart/
│   ├── checkout/
│   ├── login/
│   ├── register/
│   └── profile/
├── 📁 services/            # Servicios para API REST
│   ├── product.service.ts
│   ├── customer.service.ts
│   ├── cart.service.ts
│   ├── order.service.ts
│   └── auth.service.ts
├── 📁 models/              # Interfaces y DTOs
│   ├── product.model.ts
│   ├── customer.model.ts
│   ├── cart.model.ts
│   └── order.model.ts
├── 📁 guards/              # Guardias de rutas
│   ├── auth.guard.ts
│   └── admin.guard.ts
├── 📁 interceptors/        # Interceptores HTTP
│   ├── auth.interceptor.ts
│   └── error.interceptor.ts
├── 📁 shared/              # Módulos y utilidades compartidas
│   ├── pipes/
│   ├── directives/
│   └── validators/
└── 📁 assets/              # Recursos estáticos
    ├── images/
    ├── icons/
    └── styles/
```

---

## ⚙️ Configuración

### 🔧 Variables de Entorno

Crear archivo `src/environments/environment.ts`:

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8502/service-main/api',
  appName: 'Luna Store SV',
  version: '1.0.0'
};
```

### 🚀 Producción (`environment.prod.ts`):

```typescript
export const environment = {
  production: true,
  apiUrl: 'https://tu-api.com/service-main/api',
  appName: 'Luna Store SV',
  version: '1.0.0'
};
```

---

## 🚀 Instalación y Ejecución

### 📋 Prerrequisitos

- 📦 **Node.js** 18+ y **npm**
- 🅰️ **Angular CLI** 17+
- 🔧 **Git**

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/usuario/lunastore-frontend.git
cd lunastore-frontend
```

### 2️⃣ Instalar dependencias

```bash
npm install
```

### 3️⃣ Instalar Angular CLI (si no está instalado)

```bash
npm install -g @angular/cli
```

### 4️⃣ Configurar entorno

Verificar que `src/environments/environment.ts` tenga la URL correcta del backend.

### 5️⃣ Ejecutar la aplicación

```bash
# Desarrollo con recarga automática
ng serve -o

# O especificando puerto
ng serve --port 4200 --open
```

### 6️⃣ Verificar instalación

La aplicación estará disponible en:
```
🌐 http://localhost:4200/
```

---

## 🔗 Integración con Backend

### 📡 Configuración de API

Los servicios están configurados para comunicarse con el backend de Luna Store:

```typescript
// Ejemplo: product.service.ts
@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = `${environment.apiUrl}/products`;

  constructor(private http: HttpClient) {}

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }
}
```

### 🔄 Endpoints Principales

| Servicio | Endpoint Backend | Descripción |
|----------|------------------|-------------|
| **Products** | `/api/products` | Gestión de productos |
| **Customers** | `/api/customers` | Gestión de clientes |
| **Orders** | `/api/orders` | Gestión de pedidos |
| **Auth** | `/api/auth` | Autenticación |

---

## 📱 Características Responsive

### 📐 Breakpoints de Bootstrap

- **Mobile**: < 576px
- **Tablet**: 576px - 992px  
- **Desktop**: > 992px

### 🎨 Componentes Adaptativos

- Header con menú hamburguesa en móviles
- Grid de productos responsive
- Carrito lateral/modal según dispositivo
- Formularios optimizados para touch

---

## 🚀 Despliegue

### 🏗️ Build de Producción

```bash
# Build optimizado para producción
ng build --configuration production

# Los archivos se generan en dist/lunastore-frontend/
```

### 🌐 Opciones de Despliegue

#### Netlify
```bash
# Instalar Netlify CLI
npm install -g netlify-cli

# Desplegar
netlify deploy --prod --dir=dist/lunastore-frontend
```

#### Vercel
```bash
# Instalar Vercel CLI
npm install -g vercel

# Desplegar
vercel --prod
```

#### Firebase Hosting
```bash
# Instalar Firebase CLI
npm install -g firebase-tools

# Inicializar y desplegar
firebase init hosting
firebase deploy
```

---

## 🧪 Scripts Disponibles

| Comando | Descripción |
|---------|-------------|
| `ng serve` | Servidor de desarrollo |
| `ng build` | Build de producción |
| `ng test` | Ejecutar pruebas unitarias |
| `ng e2e` | Pruebas end-to-end |
| `ng lint` | Análisis de código |
| `npm start` | Alias para `ng serve` |

---

## 🔧 Configuración Adicional

### 📦 Dependencias Principales

```json
{
  "dependencies": {
    "@angular/core": "^17.0.0",
    "@angular/common": "^17.0.0",
    "@angular/router": "^17.0.0",
    "@angular/forms": "^17.0.0",
    "bootstrap": "^5.3.0",
    "rxjs": "^7.8.0"
  }
}
```

### 🎨 Personalización de Estilos

```scss
// src/styles.scss
@import 'bootstrap/scss/bootstrap';

:root {
  --primary-color: #007bff;
  --secondary-color: #6c757d;
  --success-color: #28a745;
  --danger-color: #dc3545;
}
```

---

## 🤝 Contribución

1. 🍴 Fork el proyecto
2. 🌿 Crea una rama feature (`git checkout -b feature/AmazingFeature`)
3. 📝 Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. 📤 Push a la rama (`git push origin feature/AmazingFeature`)
5. 🔄 Abre un Pull Request

### 📋 Estándares de Desarrollo

- Seguir **Angular Style Guide**
- Usar **TypeScript strict mode**
- Implementar **pruebas unitarias**
- Documentar componentes complejos
- Mantener **código limpio** y comentado

---

## 📄 Licencia

Este proyecto es de carácter **académico** desarrollado como parte de un proyecto educativo.

**Desarrollado con ❤️ por Steven Rivera**

---

## 📞 Soporte

¿Tienes alguna pregunta o sugerencia?

- 📧 **Email**: steven.melendez001@gmail.com

---

