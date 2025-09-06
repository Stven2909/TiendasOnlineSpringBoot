# 🛍️ Luna Store SV - Backend

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-Database-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-Academic-red.svg)](#)

**Luna Store SV Backend** es una API REST desarrollada con **Spring Boot** que proporciona la lógica de negocio para un sistema de comercio electrónico. Gestiona productos, clientes, carritos de compra y facturación básica.

---

## 📋 Tabla de Contenidos

- [🚀 Tecnologías](#-tecnologías)
- [✨ Características](#-características)
- [📂 Estructura del Proyecto](#-estructura-del-proyecto)
- [⚙️ Configuración](#️-configuración)
- [🚀 Instalación y Ejecución](#-instalación-y-ejecución)
- [📚 API Endpoints](#-api-endpoints)
- [🔧 Configuración de Perfiles](#-configuración-de-perfiles)
- [📧 Notificaciones por Email](#-notificaciones-por-email)
- [🤝 Contribución](#-contribución)
- [📄 Licencia](#-licencia)

---

## 🚀 Tecnologías

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| **Java** | 17 | Lenguaje de programación principal |
| **Spring Boot** | 3.x | Framework de desarrollo |
| **Spring Data JPA** | - | ORM con Hibernate |
| **MySQL** | 8.0+ | Base de datos relacional |
| **Spring Mail** | - | Envío de notificaciones |
| **Lombok** | - | Reducción de código boilerplate |
| **Spring DevTools** | - | Herramientas de desarrollo |

---

## ✨ Características

### 🛒 Funcionalidades Core
- **Gestión de Productos**: CRUD completo con categorías y stock
- **Gestión de Clientes**: Registro, autenticación y perfiles
- **Carrito de Compras**: Funcionalidad completa de e-commerce
- **Sistema de Facturación**: Generación y gestión de facturas
- **Notificaciones**: Sistema de alertas por correo electrónico

### 🔧 Características Técnicas
- **Multi-entorno**: Configuración separada para desarrollo y producción
- **Manejo de Errores**: Sistema centralizado de excepciones
- **Logging**: Sistema de logs configurables por entorno
- **API REST**: Endpoints bien documentados y estructurados

---

## 📂 Estructura del Proyecto

```
src/main/java/com/lunastore/backend/
├── 📁 controller/          # Controladores REST API
│   ├── ProductController.java
│   ├── CustomerController.java
│   └── OrderController.java
├── 📁 dto/                 # Data Transfer Objects
│   ├── ProductDTO.java
│   ├── CustomerDTO.java
│   └── OrderDTO.java
├── 📁 entity/              # Entidades JPA
│   ├── Product.java
│   ├── Customer.java
│   └── Order.java
├── 📁 repository/          # Repositorios de datos
│   ├── ProductRepository.java
│   ├── CustomerRepository.java
│   └── OrderRepository.java
├── 📁 service/             # Interfaces de servicios
│   ├── ProductService.java
│   ├── CustomerService.java
│   └── OrderService.java
├── 📁 service.impl/        # Implementaciones de servicios
│   ├── ProductServiceImpl.java
│   ├── CustomerServiceImpl.java
│   └── OrderServiceImpl.java
├── 📁 config/              # Configuraciones
│   ├── MailConfig.java
│   ├── ExceptionConfig.java
│   └── DatabaseConfig.java
└── 📁 exception/           # Excepciones personalizadas
    ├── ProductNotFoundException.java
    └── CustomerNotFoundException.java
```

---

## ⚙️ Configuración

### 📄 Archivos de Configuración

El proyecto utiliza **perfiles YAML** para manejar diferentes entornos:

```yaml
# application.yml (Principal)
spring:
  profiles:
    active: dev # Cambiar a 'prod' para producción
  
server:
  port: 8502
  servlet:
    context-path: /service-main
```

### 🔧 Configuración por Entornos

| Perfil | Archivo | DDL Auto | Logs | Email |
|--------|---------|----------|------|-------|
| **Desarrollo** | `application-dev.yml` | `update` | ✅ Activos | ❌ |
| **Producción** | `application-prod.yml` | `none` | ⚠️ Limitados | ✅ |

---

## 🚀 Instalación y Ejecución

### 📋 Prerrequisitos

- ☕ **Java 17** o superior
- 🗄️ **MySQL 8.0+**
- 🔧 **Gradle** (incluido wrapper)

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/usuario/lunastore-backend.git
cd lunastore-backend
```

### 2️⃣ Configurar Base de Datos

```sql
-- Crear base de datos
CREATE DATABASE db_lunastore;

-- Crear usuario (opcional)
CREATE USER 'lunastore_user'@'localhost' IDENTIFIED BY 'tu_password';
GRANT ALL PRIVILEGES ON db_lunastore.* TO 'lunastore_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3️⃣ Configurar Variables de Entorno

Crear archivo `.env` o configurar variables:

```bash
export DB_HOST=localhost
export DB_PORT=3306
export DB_NAME=db_lunastore
export DB_USERNAME=tu_usuario
export DB_PASSWORD=tu_password
```

### 4️⃣ Ejecutar la Aplicación

```bash
# Usando Gradle Wrapper
./gradlew bootRun

# O usando Gradle instalado
gradle bootRun
```

### 5️⃣ Verificar Instalación

La API estará disponible en:
```
🌐 http://localhost:8502/service-main
```

---

## 📚 API Endpoints

### 📋 Documentación Base

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/products` | Listar productos |
| `POST` | `/api/products` | Crear producto |
| `GET` | `/api/products/{id}` | Obtener producto |
| `PUT` | `/api/products/{id}` | Actualizar producto |
| `DELETE` | `/api/products/{id}` | Eliminar producto |
| `GET` | `/api/customers` | Listar clientes |
| `POST` | `/api/customers` | Crear cliente |
| `GET` | `/api/orders` | Listar órdenes |
| `POST` | `/api/orders` | Crear orden |

> 📖 **Documentación completa**: Disponible en `/swagger-ui.html` cuando la aplicación esté ejecutándose.

---

## 🔧 Configuración de Perfiles

### 🔨 Desarrollo (`dev`)

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/db_lunastore
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  
logging:
  level:
    com.lunastore: DEBUG
```

### 🚀 Producción (`prod`)

```yaml
spring:
  datasource:
    url: ${DATABASE_URL}
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: false
  
logging:
  level:
    com.lunastore: INFO
```

---

## 📧 Notificaciones por Email

El sistema está configurado para enviar notificaciones automáticas:

### 📤 Configuración SMTP

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: ${MAIL_USERNAME}
    password: ${MAIL_PASSWORD}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```

### 🎯 Casos de Uso

- ❌ **Errores críticos** del sistema
- 📦 **Confirmaciones** de pedidos
- 🔄 **Actualizaciones** de stock
- 🚨 **Alertas** de seguridad

---

## 🤝 Contribución

1. 🍴 Fork el proyecto
2. 🌿 Crea una rama feature (`git checkout -b feature/AmazingFeature`)
3. 📝 Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. 📤 Push a la rama (`git push origin feature/AmazingFeature`)
5. 🔄 Abre un Pull Request

### 📋 Estándares de Código

- Usar **Java Code Conventions**
- Documentar métodos públicos con **Javadoc**
- Escribir **pruebas unitarias** para nuevas funcionalidades
- Seguir principios **SOLID**

---

## 📄 Licencia

Este proyecto es de carácter **académico** desarrollado como parte de un proyecto educativo.

**Desarrollado con ❤️ por Steven Rivera**

---

## 📞 Soporte

¿Tienes alguna pregunta o sugerencia?

- 📧 **Email**: steven.melendez001@gmail.com

---
