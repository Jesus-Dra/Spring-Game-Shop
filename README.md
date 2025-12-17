# 🎮 Spring Game Shop API

REST API desarrollada con **Spring Boot** para la gestión de **juegos y categorías**, aplicando buenas prácticas de backend como arquitectura en capas, DTOs, validaciones y persistencia con JPA.

Este proyecto fue creado como parte de mi formación y práctica en desarrollo backend con Java y Spring Boot.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Docker (configuración básica)
- Git & GitHub

---

## 🧱 Arquitectura

El proyecto sigue una **arquitectura en capas**:

- **Controller** → Manejo de peticiones HTTP (REST)
- **Service** → Lógica de negocio
- **Repository** → Acceso a datos (JPA)
- **DTOs** → Separación entre modelo interno y datos expuestos
- **Entity** → Modelos persistentes
- **Exception Handling** → Manejo de errores controlado

---

## 📌 Funcionalidades

### 🎮 Games
- Crear juego
- Obtener todos los juegos
- Actualizar juego (PUT)
- Actualizar parcialmente juego (PATCH)
- Eliminar juego por ID
- Relación **ManyToOne** con Category

### 🏷️ Categories
- Crear categoría
- Obtener todas las categorías
- Actualizar categoría (PATCH)
- Eliminar categoría por ID

---

## ✅ Validaciones

Se aplican validaciones a nivel de backend mediante **DTOs** y `@Valid`, incluyendo:

- Campos obligatorios
- Longitud mínima y máxima
- Prevención de datos inválidos o vacíos

---

## 🔐 Buenas prácticas aplicadas

- Uso de DTOs (Request / Response / Patch)
- Separación clara de responsabilidades
- Uso correcto de HTTP methods (GET, POST, PUT, PATCH, DELETE)
- Manejo explícito de errores
- Código legible y mantenible

---

## 📦 Base de datos

- PostgreSQL
- Relaciones entre entidades con JPA
- Persistencia mediante Hibernate

---

## ▶️ Cómo ejecutar el proyecto

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Jesus-Dra/Spring-Game-Shop.git


👨‍💻 Autor

Jesús Ramírez
Desarrollador Backend Java | Spring Boot
