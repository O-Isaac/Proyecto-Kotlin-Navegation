# ProyectoAB

Proyecto Android desarrollado con Jetpack Compose y Navigation Compose para una aplicación de gestión de jugadores.

## 📋 Requisitos

- Android Studio Hedgehog o superior
- Kotlin 2.0.21
- JDK 11 o superior
- Android SDK mínimo: API 24 (Android 7.0)
- Android SDK objetivo: API 35

## 🚀 Tecnologías

- **Jetpack Compose**: UI moderna y declarativa
- **Navigation Compose**: Sistema de navegación type-safe
- **Kotlin Serialization**: Serialización de datos para navegación
- **Material Design 3**: Componentes de diseño
- **ViewModel**: Gestión de estado

## 📦 Dependencias de Navegación

### Configuración de Version Catalog

En el archivo `gradle/libs.versions.toml`, se definen las versiones de las dependencias de navegación:

```toml
[versions]
navigation = "2.9.6"
serializationPlugin = "2.2.0"
serializationDep = "1.9.0"

[libraries]
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigation" }
kotlin-serialization-json = { group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref="serializationDep" }

[plugins]
kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref="serializationPlugin" }
```

### Configuración en build.gradle.kts

#### 1. Agregar el plugin de serialización en `build.gradle.kts` (nivel raíz):

```kotlin
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}
```

#### 2. Agregar dependencias en `app/build.gradle.kts`:

```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization) // Plugin necesario para serialización
}

dependencies {
    // Dependencias de navegación
    implementation(libs.androidx.navigation.compose)
    
    // Dependencia de Kotlin Serialization para navegación type-safe
    implementation(libs.kotlin.serialization.json)
    
    // Otras dependencias requeridas
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
}
```

## 🧭 Estructura de Navegación

El proyecto utiliza **Type-Safe Navigation** con Kotlin Serialization, una característica moderna de Navigation Compose que proporciona seguridad de tipos en tiempo de compilación.

### Definición de Rutas

En `navigation/Rutas.kt`, se definen las rutas como objetos y clases serializables:

```kotlin
package com.codelab.proyectoab.navigation

import kotlinx.serialization.Serializable

@Serializable
object Inicio

@Serializable
data class JugadoresRutas(val idJugador: Int)
```

**Ventajas:**
- ✅ Seguridad de tipos en tiempo de compilación
- ✅ No más errores con strings de rutas
- ✅ Paso de parámetros type-safe
- ✅ Autocompletado en IDE

### Configuración del NavHost

En `navigation/AppNavigation.kt`, se configura el grafo de navegación:

```kotlin
package com.codelab.proyectoab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.codelab.proyectoab.ui.screens.InicioScreen
import com.codelab.proyectoab.ui.screens.PlantillaScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Inicio) {
        composable<Inicio> {
            InicioScreen { idJugador ->
                navController.navigate(JugadoresRutas(idJugador))
            }
        }

        composable<JugadoresRutas> { backStackEntry ->
            val jugador = backStackEntry.toRoute<JugadoresRutas>()
            PlantillaScreen()
        }
    }
}
```

### Integración en MainActivity

En `MainActivity.kt`, se integra la navegación en la actividad principal:

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoABTheme {
                AppNavigation()
            }
        }
    }
}
```

## 💡 Uso de la Navegación

### 1. Crear una nueva ruta

En `navigation/Rutas.kt`:

```kotlin
// Ruta sin parámetros
@Serializable
object MiNuevaRuta

// Ruta con parámetros
@Serializable
data class DetalleRuta(val id: Int, val nombre: String)
```

### 2. Agregar la ruta al NavHost

En `navigation/AppNavigation.kt`:

```kotlin
NavHost(navController = navController, startDestination = Inicio) {
    // ... rutas existentes ...
    
    // Nueva ruta sin parámetros
    composable<MiNuevaRuta> {
        MiNuevaPantalla()
    }
    
    // Nueva ruta con parámetros
    composable<DetalleRuta> { backStackEntry ->
        val detalle = backStackEntry.toRoute<DetalleRuta>()
        DetallePantalla(
            id = detalle.id,
            nombre = detalle.nombre
        )
    }
}
```

### 3. Navegar desde una pantalla

```kotlin
@Composable
fun MiPantalla(navController: NavController) {
    Button(onClick = {
        // Navegar a ruta sin parámetros
        navController.navigate(MiNuevaRuta)
    }) {
        Text("Ir a nueva pantalla")
    }
    
    Button(onClick = {
        // Navegar con parámetros
        navController.navigate(DetalleRuta(id = 123, nombre = "Ejemplo"))
    }) {
        Text("Ir a detalle")
    }
}
```

### 4. Navegar hacia atrás

```kotlin
Button(onClick = {
    navController.popBackStack()
}) {
    Text("Volver")
}
```

### 5. Navegar y limpiar el back stack

```kotlin
Button(onClick = {
    navController.navigate(Inicio) {
        popUpTo(0) { inclusive = true }
    }
}) {
    Text("Ir al inicio")
}
```

## 🔄 Flujo de Navegación Actual

```
┌─────────────┐
│InicioScreen │ 
└──────┬──────┘
       │ click en jugador (idJugador)
       │
       ▼
┌────────────────────┐
│JugadoresRutas(id)  │
│PlantillaScreen     │
└────────────────────┘
```

## 📚 Recursos Adicionales

- [Documentación oficial Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- [Type-safe navigation](https://developer.android.com/guide/navigation/design/type-safety)
- [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html)

## 🏗️ Compilación

Para compilar el proyecto:

```bash
./gradlew assembleDebug
```

Para instalar en dispositivo/emulador:

```bash
./gradlew installDebug
```

## 📝 Notas

- El proyecto utiliza **Jetpack Compose** para la UI
- La navegación es **Type-Safe** gracias a Kotlin Serialization
- Se recomienda usar **Navigation Compose 2.9.6** o superior para soporte completo de Type-Safe Navigation
- El plugin de Kotlin Serialization debe estar habilitado en el proyecto

---

Desarrollado con ❤️ usando Jetpack Compose
