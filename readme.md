# Tarea 1: Simulando EloTelTag y Aplicación Find My
**Curso:** ELO329 - Diseño y Programación Orientados a Objetos  
**Institución:** Universidad Técnica Federico Santa María  
**Integrantes:** 
* Cristhofer Sandoval Huilipán
* Tomás Ramdohr
* [Nombre Integrante 3]
* [Nombre Integrante 4]

## 1. Entorno de Desarrollo y Requisitos
El sistema fue desarrollado y testeado bajo las siguientes especificaciones técnicas para asegurar total compatibilidad con el entorno de evaluación **Aragorn**:
* **Sistema Operativo**: Linux (ejecutado mediante terminal integrada en VS Code).
* **Lenguaje**: Java 21 (JDK 21).
* **Herramienta de Construcción**: GNU Make 4.
* **Entrada/Salida**: Archivos de texto plano y formato CSV con delimitador `<TAB>`.

---

## 2. Organización del Repositorio
El proyecto se entrega organizado en 4 carpetas independientes. Cada carpeta representa una fase del desarrollo incremental y es completamente autónoma, contando con su propio código fuente, archivos de configuración locales y lógica de compilación:

* **`/Etapa1`**: Gestión de archivos y reporte de trayectoria física real.
* **`/Etapa2`**: Implementación de herencia y lógica de reporte por proximidad (10 metros).
* **`/Etapa3`**: Visualizador `Viewer` e interfaz centralizada **FindMy**.
* **`/Etapa4`**: Consolidación del ejecutable final **`SimuladorTest`** y generación de documentación de análisis.

---

## 3. Inventario de Archivos
La implementación consta de los siguientes archivos fuente y de datos:

### Código Fuente (`.java`)
| Archivo | Función Principal |
| :--- | :--- |
| **`Equipo.java`** | Clase abstracta base para la gestión de coordenadas y desplazamiento. |
| **`Cellular.java`** | Dispositivo con GPS que actúa como gateway para reportar ítems a la nube. |
| **`EloTelTag.java`** | Rastreador pasivo que valida proximidad para reportes indirectos. |
| **`Tablet.java`** | Dispositivo híbrido que requiere de un celular para ser localizado en la red. |
| **`ETNube.java`** | Servidor de persistencia que almacena la última ubicación informada. |
| **`Viewer.java`** | Capa de presentación encargada de formatear la salida del servicio FindMy. |
| **`Territory.java`** | Gestor del espacio físico y de las comunicaciones locales por cercanía. |
| **`T1Stage[1-3].java`** | Controladores específicos para las simulaciones de las etapas 1, 2 y 3 respectivamente. |
| **`SimuladorTest.java`** | Programa ejecutable final consolidado en la Etapa 4. |

### Archivos de Datos y Configuración
* **`config.txt`**: Define el escenario inicial (dueños, equipos y coordenadas base).
* **`move.txt`**: Contiene la secuencia de comandos de movimiento (deltas) y visualización.
* **`output.csv`**: Registro histórico de las posiciones reportadas en cada paso de tiempo.

---

## 4. Arquitectura y Lógica de Red
El sistema se rige por el principio de **Red Comunitaria**:
1. **Detección**: Los celulares escanean el entorno buscando dispositivos sin GPS a una distancia euclidiana $\le 10 [m]$.
2. **Reporte**: Al detectar un tag o tablet, el celular presta su ubicación GPS para actualizar el registro en la `ETNube`.
3. **Persistencia**: La nube almacena la "última posición informada", permitiendo que FindMy muestre datos históricos si el equipo queda fuera de rango.



---

## 5. Instrucciones de Compilación y Uso
Cada carpeta de etapa contiene su propio `Makefile`. Para la versión final:
```bash
cd Etapa4
make         # Para compilar
make run     # Para ejecutar con config.txt y move.txt
make clean   # Para eliminar archivos .class y reportes previos
```

*Nota: El mismo procedimiento aplica para `/Etapa1`, `/Etapa2` y `/Etapa3`, utilizando sus respectivos controladores y archivos de prueba locales.*


---

## 6. Consideraciones para la Evaluación
* **Formato de Salida**: El `output.csv` utiliza tabulaciones (`\t`) como separador para facilitar el análisis posterior en planillas de cálculo.
* **Modularidad**: El uso de herencia en la clase `Equipo` permite una integración escalable de nuevos dispositivos.
* **Visualización**: La aplicación FindMy organiza los bienes en "Items" y "Dispositivos", reflejando fielmente la jerarquía del propietario.
