<div align="center">
  <div align="center">
  <img src="assets/muviz-logo.png" alt="Muviz Logo" width="150px">

# Muviz

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/yourusername/muviz)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/java-17-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0-green)](https://spring.io/projects/spring-boot)

Una plataforma moderna para compartir y descubrir opiniones sobre películas.

</div>

---

## 📋 Índicee

- [Introducción](#-introducción)
- [Problema y Solución](#-problema-y-solución)
- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Modelo de Datos](#-modelo-de-datos)
- [Seguridad](#-seguridad)
- [Testing](#-testing)
- [Eventos y Asincronía](#-eventos-y-asincronía)
- [Desarrollo](#-desarrollo)
- [Conclusión](#-conclusión)
- [Licencia](#-licencia)

---

## 🎬 Introducción

**Muviz** nace en el contexto del curso de Desarrollo Basado en Plataformas (DBP), con el objetivo de crear un espacio donde los amantes del cine puedan compartir sus pensamientos y descubrir nuevas películas.

**Objetivos:**

- 🛠 Desarrollar una API RESTful robusta
- 🔐 Implementar un sistema de autenticación seguro
- 👥 Fomentar una comunidad activa de cinéfilos

---

## 🎯 Problema y Solución

### El Desafío

Muchas plataformas actuales de reseñas de películas carecen de:

- 🔒 Sistemas de autenticación robustos
- 🧹 Mecanismos efectivos contra spam
- 🤝 Enfoque centrado en la comunidad

### Nuestra Respuesta

Muviz aborda estos problemas ofreciendo:

- 🛡 Autenticación segura con JWT
- 🎭 Sistema de roles para moderar contenido
- 💬 Interacciones enriquecedoras entre usuarios

---

## ✨ Características

1. **🔐 Autenticación Avanzada**

   - Registro de usuarios
   - Login seguro
   - Gestión de tokens JWT

2. **👤 Gestión de Usuarios**

   - Perfiles personalizables
   - Roles: USER, MODERATOR, ADMIN

3. **🎥 Catálogo de Películas**

   - Búsqueda y filtrado
   - Información detallada

4. **📝 Sistema de Posts**

   - Crear, leer, actualizar y eliminar reseñas
   - Interacción con otros usuarios

5. **💬 Comentarios**
   - Discusiones en tiempo real
   - Notificaciones

---

## 💻 Tecnologías

- **Backend:**

  - ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
  - ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)

- **Seguridad:**

  - ![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)

- **Base de Datos:**

  - ![JPA](https://img.shields.io/badge/JPA-007396?style=for-the-badge&logo=java&logoColor=white)
  - ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)

- **Herramientas:**
  - ![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
  - ![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
  - ![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)

---

## 📊 Modelo de Datos

```mermaid
erDiagram
    USER ||--o{ POST : creates
    USER ||--o{ COMMENT : writes
    MOVIE ||--o{ POST : subject_of
    POST ||--o{ COMMENT : has

    USER {
        Long id
        String name
        String email
        String password
        Set roles
    }

    MOVIE {
        Long id
        String title
        Integer releaseYear
        String director
    }

    POST {
        Long id
        String content
        DateTime createdAt
    }

    COMMENT {
        Long id
        String content
        DateTime createdAt
    }
```

---

## 🔒 Seguridad

- **🔑 JWT Authentication**
- **🔐 Password Encryption:** BCrypt
- **🚦 Role-based Access Control**
- **🛡 CORS Configuration**
- **🚫 Rate Limiting**

---

## 🧪 Testing

### Niveles

- ✅ Unit Testing
- 🔄 Integration Testing
- 🌐 API Testing

### Manejo de Errores

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        // Lógica de manejo de excepciones
    }
}
```

---

## ⚡ Eventos y Asincronía

```java
@Async
@EventListener
public void handleUserRegisteredEvent(UserRegisteredEvent event) {
    // Envío asíncrono de correo de bienvenida
    emailService.sendWelcomeEmail(event.getUserEmail());
}
```

---

## 👨‍💻 Desarrollo

El proyecto sigue las mejores prácticas de desarrollo:

- 🌿 Branching estratégico
- 👀 Code reviews vía Pull Requests
- 🎫 Tracking de issues
- 🔄 Flujo de trabajo Gitflow

---

## 🎉 Conclusión

Muviz representa un hito significativo en nuestro aprendizaje, demostrando:

- 🏗 Arquitectura robusta
- 🔐 Implementación de seguridad avanzada
- 🚀 Eficiencia mediante programación asíncrona

**Próximos Pasos:**

1. 🖥 Desarrollo de frontend
2. 🧠 Sistema de recomendaciones
3. 🌐 Integración con APIs externas de películas

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

---

<div align="center">
  Desarrollado con ❤️ por el equipo de Muviz
</div>
