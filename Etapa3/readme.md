### Tarea 1: Simulador EloTelTag - Etapa 3
**Curso:** ELO329 - Diseño y Programación Orientados a Objetos  
**Institución:** Universidad Técnica Federico Santa María  
**Integrantes:** * Cristhofer Sandoval Huilipán
* [Nombre Integrante 2]
* [Nombre Integrante 3]
* [Nombre Integrante 4]

#### **Entorno de Desarrollo y Requisitos**
El sistema fue desarrollado y testeado bajo las siguientes especificaciones técnicas para asegurar total compatibilidad con el entorno de evaluación **Aragorn**:
* **Sistema Operativo**: Linux (vía terminal integrada en VS Code).
* **Lenguaje**: Java 21 (JDK 21).
* **Herramienta de Construcción**: GNU Make 4.
* **Entrada/Salida**: Archivos de texto plano y formato CSV con delimitador `<TAB>`.

#### **Descripción General**
Esta etapa implementa la infraestructura de red distribuida para la localización de objetos personales. Se integra el reporte indirecto para dispositivos sin GPS (`EloTelTag` y `Tablet`) y la consulta de datos mediante la interfaz de usuario centralizada **FindMy**.

#### **Arquitectura del Sistema**
Basado en el contenido de la carpeta `src/`, el diseño se compone de las siguientes clases:

| Clase | Responsabilidad |
| :--- | :--- |
| **`Equipo`** | Clase abstracta base que gestiona la identidad y coordenadas físicas. |
| **`Cellular`** | Gateway con GPS y terminal de consulta para el servicio FindMy. |
| **`EloTelTag`** | Sensor pasivo que requiere proximidad ($\le 10[m]$) para reportar su ubicación. |
| **`Tablet`** | Dispositivo híbrido sin GPS; reporta vía celular pero posee interfaz FindMy. |
| **`ETNube`** | Servidor de persistencia que almacena el último registro de cada equipo. |
| **`Territory`** | Gestor del espacio físico y de la lógica de detección por cercanía. |
| **`Viewer`** | Clase encargada de formatear y desplegar la información en consola. |
| **`T1Stage3`** | **Controlador (Driver)** encargado de orquestar la simulación de esta etapa. |

#### **Gestión de Archivos de Entrada**
* **`config.txt`**: Configuración inicial de dueños y equipos.
* **`move.txt`**: Comandos de desplazamiento y solicitudes de visualización (`FindMy`).
* **`output.csv`**: Registro generado tras la ejecución con las posiciones reportadas a la nube.

#### **Lógica de Red y Localización**
* **Detección Comunitaria:** Un `Cellular` detecta cualquier tag o tablet a una distancia $\le 10$ metros.
* **Cálculo de Distancia:** Se utiliza la fórmula euclidiana $d = \sqrt{(x_2-x_1)^2 + (y_2-y_1)^2}$.
* **Reporte:** El celular presta sus coordenadas GPS para actualizar la ubicación del equipo detectado en la `ETNube`.

#### **Ejecución (Make)**
Para compilar y ejecutar el controlador de esta etapa (`T1Stage3`):
```bash
cd Etapa3
make clean
make run
```

#### **Especificaciones Técnicas de la Entrega**
* **Composición:** Los dispositivos con pantalla (`Cellular` y `Tablet`) incorporan una instancia de `Viewer` para acceder a los datos de la nube.
* **Persistencia:** Si un equipo queda fuera de rango, la nube conserva la última posición reportada por el último gateway que lo detectó.

