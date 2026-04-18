# Tarea 1: Simulando EloTelTag y Aplicación Find My
**Curso:** ELO329 - Diseño y Programación Orientados a Objetos  
**Institución:** Universidad Técnica Federico Santa María  
**Integrantes:** 
* Cristhofer Sandoval 
* Tomás Ramdohr
* Diego Mella
* Danilo Painevilo

## 1. Entorno de Desarrollo y Requisitos
El sistema fue desarrollado y testeado bajo las siguientes especificaciones técnicas para asegurar total compatibilidad con el entorno de evaluación **Aragorn**:
* **Sistema Operativo**: Linux (ejecutado mediante terminal integrada en VS Code).
* **Lenguaje**: Java 21 (JDK 21).
* **Herramienta de Construcción**: GNU Make 4.
* **Entrada/Salida**: Archivos de texto plano y formato CSV con delimitador `<TAB>`.

---

## 2. Organización del Repositorio
El proyecto se organiza en carpetas independientes por etapa. Cada carpeta es autónoma y cuenta con su propia lógica de compilación:

* `/Etapa1`, `/Etapa2`, `/Etapa3`: Versiones incrementales del desarrollo.
* **`/Etapa4`**: Versión final consolidada que utiliza el ejecutable `SimulatorTest` e integra toda la funcionalidad solicitada.

---

## 3. Inventario de Archivos (Etapa 4)
La implementación final consta de los siguientes componentes principales:

### Código Fuente (`.java`)

| Archivo | Función |
| :--- | :--- |
| **`Equipo.java`** | Superclase base que gestiona las coordenadas (x, y), el desplazamiento y los datos del dueño. |
| **`Cellular.java`** | Dispositivo con GPS que actúa como *gateway* para reportar la ubicación de tags y tablets a la nube. |
| **`EloTelTag.java`** | Rastreador pasivo que valida si se encuentra en rango (10m) para permitir reportes indirectos. |
| **`Tablet.java`** | Dispositivo sin GPS propio que requiere proximidad a un celular para ser localizado. |
| **`ETNube.java`** | Servidor de persistencia que almacena la última ubicación informada de cada equipo mediante una lista dinámica. |
| **`Viewer.java`** | Clase encargada de la interfaz de salida para mostrar los resultados del servicio FindMy. |
| **`Territory.java`** | Gestor del espacio físico que coordina las verificaciones de cercanía entre celulares y dispositivos. |
| **`SimulatorTest.java`** | Programa ejecutable principal que orquesta la simulación y genera el archivo de salida. |

### Archivos de Datos y Configuración
* `config.txt`: Define el escenario inicial (número de personas, equipos y coordenadas base).
* `move.txt`: Contiene la secuencia de comandos de movimiento (deltas) y solicitudes de visualización (FindMy).
* `output.csv`: Registro histórico de las posiciones reportadas en cada paso, utilizando tabulaciones como separador.

---

## 4. Arquitectura y Lógica de Red
El sistema implementa una **Red de Localización Comunitaria** basada en los siguientes principios:

1.  **Detección:** Los celulares escanean el `Territory` buscando `EloTelTag` o `Tablet` a una distancia euclidiana inferior o igual a **10 metros**.
2.  **Reporte:** Al detectar un dispositivo cercano, el celular reporta a la `ETNube` la posición actual del celular como ubicación estimada del dispositivo detectado.
3.  **Persistencia:** La `ETNube` almacena únicamente el **último reporte recibido**, permitiendo que el servicio `FindMy` muestre la última ubicación conocida del objeto.

---

## 5. Instrucciones de Compilación y Uso
Cada carpeta de etapa contiene su propio `Makefile`. Para la versión final:
```bash
cd Etapa4

# 1. Compilar el proyecto (crea la carpeta /bin y compila los .java de /src)
make

# 2. Ejecutar la simulación
# (Pasa automáticamente config.txt y move.txt como argumentos)
make run

# 3. Limpiar archivos binarios y el reporte generado
make clean
```


*Nota: El mismo procedimiento aplica para `/Etapa1`, `/Etapa2` y `/Etapa3`, utilizando sus respectivos controladores y archivos de prueba locales.*


---

## 6. Consideraciones de Diseño
* **Modularidad:** El uso de **herencia** en la clase `Equipo` permite que `Cellular`, `Tablet` y `EloTelTag` compartan la lógica de movimiento y gestión de coordenadas de forma eficiente.
* **Separación de Responsabilidades:** `ETNube` se encarga exclusivamente del almacenamiento de datos (capa de datos), mientras que `Viewer` gestiona exclusivamente la presentación en pantalla.
