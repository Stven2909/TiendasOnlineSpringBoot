# 🌙 Luna Store SV

[![Java](https://img.shields.io/badge/Java-17-orange.svg?logo=openjdk)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg?logo=spring)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-17-red.svg?logo=angular)](https://angular.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg?logo=mysql)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-Academic-red.svg)](#)

**Luna Store SV** es una aplicación web de tipo **e-commerce** desarrollada como proyecto académico. El sistema está dividido en dos módulos principales que trabajan en conjunto para ofrecer una experiencia completa de comercio electrónico.

---

## 📋 Tabla de Contenidos

- [Arquitectura](#-arquitectura)
- [Módulos del Sistema](#-módulos-del-sistema)
- [Objetivo del Proyecto](#-objetivo-del-proyecto)
- [Tecnologías](#️-tecnologías)
- [Instalación Rápida](#-instalación-rápida)
- [Organización del Repositorio](#-organización-del-repositorio)
- [Funcionalidades](#-funcionalidades)
- [Configuración de Correos](#-configuración-de-correos)
- [Screenshots](#-screenshots)
- [Autor](#-autor)
- [Licencia](#-licencia)

---

## 🏗️ Arquitectura

El proyecto sigue una **arquitectura cliente-servidor** moderna y escalable:

- 🖥️ **Backend** → API REST desarrollada con **Spring Boot** (Java)
- 🎨 **Frontend** → Interfaz de usuario desarrollada con **Angular**
- 🗄️ **Base de Datos** → **MySQL** para persistencia de datos
- 📧 **Notificaciones** → Sistema de correos automáticos

```
┌─────────────────┐    HTTP/REST    ┌─────────────────┐    JPA/Hibernate    ┌─────────────────┐
│                 │◄──────────────►│                 │◄──────────────────►│                 │
│   Angular 17    │                │  Spring Boot    │                     │    MySQL 8.0   │
│   Frontend      │                │    Backend      │                     │    Database     │
│   Port: 4200    │                │   Port: 8502    │                     │   Port: 3306    │
└─────────────────┘                └─────────────────┘                     └─────────────────┘
```

![Arquitectura Luna Store](docs/db/Diagrama-Arquitectura-LunaStore.png)

---

## 📦 Módulos del Sistema

| Módulo | Tecnología | Puerto | Descripción |
|--------|------------|--------|-------------|
| **🖥️ Backend** | Spring Boot 3.x | `8502` | API REST, lógica de negocio, gestión de datos |
| **🎨 Frontend** | Angular 17 | `4200` | Interfaz de usuario, experiencia del cliente |

Cada módulo cuenta con su propio `README.md` detallado con instrucciones específicas de instalación y configuración.

---

## 📌 Objetivo del Proyecto

**Luna Store SV** simula el funcionamiento completo de una tienda en línea moderna, implementando:

### 🛒 **Funcionalidades Core**
- ✅ **Gestión de Productos** - Catálogo completo con categorías y filtros
- ✅ **Gestión de Clientes** - Registro, autenticación y perfiles de usuario
- ✅ **Carrito de Compras** - Experiencia de compra fluida y responsive
- ✅ **Sistema de Facturación** - Generación automática de facturas
- ✅ **Notificaciones por Email** - Alertas automáticas y confirmaciones

### 🎯 **Objetivos Académicos**
- Aplicar patrones de diseño en desarrollo web
- Implementar arquitectura de microservicios
- Integrar tecnologías frontend y backend modernas
- Desarrollar APIs RESTful bien documentadas
- Aplicar mejores prácticas de desarrollo

---

## 🛠️ Tecnologías

### 🔹 **Backend Stack**

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Java** | 17 | Lenguaje principal |
| **Spring Boot** | 3.x | Framework de desarrollo |
| **Spring Data JPA** | - | Persistencia y ORM (Hibernate) |
| **MySQL** | 8.0 | Base de datos relacional |
| **Spring Mail** | - | Servicio de correos electrónicos |
| **Lombok** | - | Reducción de código boilerplate |
| **Gradle** | 8+ | Gestión de dependencias |

### 🔹 **Frontend Stack**

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Angular** | 17 | Framework de desarrollo |
| **TypeScript** | 5.x | Lenguaje tipado |
| **HTML5/CSS3** | - | Estructura y presentación |
| **SCSS** | - | Preprocesador CSS |
| **Bootstrap** | 5 | Framework CSS responsive |

---

## 🚀 Instalación Rápida

### **Prerrequisitos**
```bash
# Verificar instalaciones
java -version     # Java 17+
node -version     # Node.js 18+
mysql --version   # MySQL 8.0+
```

### **1. Clonar el repositorio**
```bash
git clone https://github.com/usuario/lunastore.git
cd lunastore
```

### **2. Configurar base de datos**
```sql
-- Conectar a MySQL
mysql -u root -p

-- Crear base de datos
CREATE DATABASE lunastore_db;
USE lunastore_db;
```

### **3. Levantar el Backend**
```bash
cd backend
./gradlew bootRun
```
✅ **API disponible en**: `http://localhost:8502/service-main`

### **4. Levantar el Frontend**
```bash
cd frontend
npm install
ng serve -o
```
✅ **Aplicación disponible en**: `http://localhost:4200/`

### **5. Verificar funcionamiento**
- Backend: `http://localhost:8502/service-main/api/health`
- Frontend: `http://localhost:4200`

---

## 📂 Organización del Repositorio

```
📁 Luna Store SV/
├── 📁 backend/                 # 🖥️ Proyecto Spring Boot
│   ├── src/main/java/         # Código fuente Java
│   ├── src/main/resources/    # Configuraciones
│   ├── build.gradle           # Dependencias
│   └── README.md              # Documentación backend
│
├── 📁 frontend/               # 🎨 Proyecto Angular
│   ├── src/app/               # Código fuente TypeScript
│   ├── src/assets/            # Recursos estáticos
│   ├── package.json           # Dependencias npm
│   └── README.md              # Documentación frontend
│
├── 📁 docs/                   # 📚 Documentación
│   ├── db/                    # Diagramas de base de datos
│   ├── api/                   # Documentación de API
│   └── screenshots/           # Capturas de pantalla
│
└── README.md                  # 📖 Este archivo
```

---

## ✨ Funcionalidades

### 🛍️ **Para Clientes**
- **Navegación de Productos**: Catálogo organizado por categorías
- **Búsqueda Avanzada**: Filtros por precio, categoría y nombre
- **Carrito de Compras**: Gestión completa con actualización en tiempo real
- **Proceso de Checkout**: Flujo de compra intuitivo y seguro
- **Historial de Pedidos**: Seguimiento de compras anteriores
- **Perfil de Usuario**: Gestión de datos personales

### 🔧 **Para Administradores**
- **Gestión de Inventario**: CRUD completo de productos
- **Gestión de Clientes**: Administración de usuarios registrados
- **Reportes de Ventas**: Análisis de rendimiento
- **Configuración del Sistema**: Parámetros globales

### 📊 **Características Técnicas**
- **API RESTful**: Endpoints bien documentados
- **Responsive Design**: Adaptado a todos los dispositivos
- **Autenticación JWT**: Seguridad robusta
- **Validaciones**: Frontend y backend
- **Manejo de Errores**: Respuestas informativas

---

## 📧 Configuración de Correos

El sistema incluye un módulo de notificaciones automáticas por email configurado con **Gmail SMTP**:

### **Casos de Uso**
- 🚨 Notificaciones de errores del backend
- 📬 Confirmaciones de registro de usuario
- 🧾 Confirmaciones de pedidos
- 📊 Reportes administrativos

### **Configuración**
```yaml
# application.yml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: ${EMAIL_USERNAME}
    password: ${EMAIL_PASSWORD}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```

---

## 📸 Screenshots

*Screenshots disponibles en `/docs/screenshots/`*

| Vista | Descripción |
|-------|-------------|
| 🏠 Home | Página principal con productos destacados |
| 🛍️ Catálogo | Vista de productos con filtros |
| 🛒 Carrito | Gestión del carrito de compras |
| 💳 Checkout | Proceso de finalización de compra |
| 👤 Perfil | Panel de usuario |

---

## 👤 Autor

**Steven Rivera**  
*Estudiante de Ingeniería en Sistemas Computacionales*  
**Universidad Evangélica de El Salvador (UEES)**

### 📞 Contacto
- 📧 **Email**: steven.melendez001@gmail.com
- 🎓 **Carrera**: Ingeniería en Sistemas Computacionales

---

## 📝 Licencia

Este proyecto fue desarrollado con **fines académicos** como parte del programa de estudios de Ingeniería en Sistemas Computacionales.

### 📋 **Términos de Uso**
- ✅ El código puede ser utilizado con fines educativos
- ✅ Libre adaptación para proyectos académicos
- ✅ Referencia y estudio del código fuente
- ❌ No se permite uso comercial sin autorización

---

**Desarrollado con ❤️ para fines académicos | SORM 2025**
