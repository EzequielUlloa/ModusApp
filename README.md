# Modus

Modus es una app móvil que fotografía apuntes y materiales de clase, perfila la metodología de enseñanza de cada profesor (orden de pasos, notación, frecuencia de temas) y evalúa si el trabajo del estudiante se apega a ese método específico — no solo si la respuesta es correcta.

Proyecto desarrollado para el curso **EIF411 — Universidad Nacional, Costa Rica**.

## Equipo (Echo)

| Nombre             | GitHub |
|---------------------|--------|
| Andrel Ramirez       |        |
| Ezequiel Ulloa        |        |
| Josh Gamez            |        |

## Stack tecnológico

- **Android**: Kotlin, Jetpack Compose, Clean Architecture
- **Backend**: Spring Boot (repositorio separado o carpeta `/backend`, a definir)
- **IA**: Claude API (evaluación de adherencia al método del profesor)
- **Monitoreo**: Firebase Analytics, Crashlytics, AppCenter
- **Gestión**: GitHub Projects

## Estructura de carpetas

El proyecto Android sigue Clean Architecture dentro de `app/src/main/java/com/una/modus/`:

```
com.una.modus/
├── data/
│   ├── repository/   # Implementaciones de los repositorios del dominio
│   ├── remote/        # Fuentes de datos remotas (APIs, Claude API, backend)
│   ├── local/          # Fuentes de datos locales (DB, DataStore, caché)
│   └── dto/            # Data Transfer Objects
├── domain/
│   ├── model/          # Modelos de negocio puros
│   ├── usecase/        # Casos de uso (lógica de negocio)
│   └── repository/     # Interfaces de repositorios (contratos)
└── presentation/
    ├── auth/            # Pantallas de autenticación (login, registro)
    ├── menu/            # Menú / navegación / home
    ├── lists/           # Pantallas de listas (materiales, apuntes, etc.)
    └── common/          # Componentes y navegación compartida (NavGraph, splash)
```

## Setup local

### Requisitos

- Android Studio (última versión estable)
- JDK 11+
- SDK de Android con `compileSdk 37` / `minSdk 30`

### Pasos

1. Clonar el repositorio:
   ```
   git clone <url-del-repositorio>
   ```
2. Abrir la carpeta del proyecto con Android Studio.
3. Dejar que Gradle sincronice las dependencias (puede tardar la primera vez).
4. Crear un archivo `local.properties` en la raíz (si Android Studio no lo genera automáticamente) apuntando al SDK local:
   ```
   sdk.dir=/ruta/a/tu/Android/sdk
   ```
5. Ejecutar la app en un emulador o dispositivo físico con el botón **Run**.

> Nota: `local.properties`, `google-services.json` y cualquier keystore/credencial están excluidos del repositorio vía `.gitignore`. Pide estos archivos al equipo si los necesitas para correr integraciones (Firebase, backend, Claude API).

## Flujo de branches

- **`main`**: rama protegida, siempre desplegable. Solo se actualiza vía Pull Request aprobado.
- **`develop`**: rama de integración donde se combinan las features antes de pasar a `main`.
- **`feature/nombre-corto`**: una rama por tarea/feature, creada desde `develop` (ej. `feature/login-screen`, `feature/nav-graph`). Se mergea a `develop` vía PR.
