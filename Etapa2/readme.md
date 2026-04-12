### Tarea 1: Simulador EloTelTag - Etapa 2
**Curso:** ELO329 - Diseño y Programación Orientados a Objetos  
**Institución:** Universidad Técnica Federico Santa María  
**Integrantes:** 
* Cristhofer Sandoval Huilipán
* [Nombre Integrante 2]
* [Nombre Integrante 3]
* [Nombre Integrante 4]

#### **Entorno de Desarrollo y Requisitos**
* **Sistema Operativo**: Linux (vía terminal integrada en VS Code).
* **Lenguaje**: Java 21 (JDK 21).
* **Herramienta de Construcción**: GNU Make 4.
* **Entrada/Salida**: Archivos de texto plano y formato CSV con delimitador `<TAB>`.

#### **Descripción General**
La segunda etapa introduce la infraestructura de red colaborativa mediante celulares y el servidor central `ETNube`. El sistema evoluciona de un reporte de trayectoria real a un modelo de localización reportada por proximidad, donde los tags dependen de celulares cercanos que actúan como gateways GPS.

#### **Arquitectura del Sistema**
| Clase | Responsabilidad |
| :--- | :--- |
| **`Equipo`** | Clase base abstracta que estandariza la lógica de movimiento y gestión de coordenadas. |
| **`EloTelTag`** | Dispositivo pasivo que valida su radio de cobertura ($\le 10[m]$) para ser detectado. |
| **`Cellular`** | Gateway GPS que reporta su ubicación a la nube al detectar un equipo cercano. |
| **`ETNube`** | Servidor de persistencia que almacena la última localización informada por la red. |
| **`Territory`** | Administrador de proximidad que gatilla los reportes entre dispositivos. |
| **`T1Stage2`** | **Controlador (Driver)** encargado de orquestar la simulación de esta etapa. |

#### **Gestión de Archivos de Entrada**
* **`config.txt`**: Carga de celulares con GPS y tags. Las tablets se registran pero no reportan GPS propio.
* **`move.txt`**: Permite mover tanto celulares como tags. El movimiento de un celular puede hacer que tags previamente "invisibles" entren en rango y se actualicen en la nube.

#### **Lógica de Red y Localización**
* **Radio de Detección:** La comunicación ocurre solo si la distancia euclidiana es $\le 10$ metros.
* **Reporte Indirecto:** Al ocurrir detección, el celular envía **su propia ubicación GPS** a la nube para el ítem detectado.
* **Persistencia:** Si un equipo sale de rango, la `ETNube` mantiene el último reporte exitoso en el `output.csv`.



#### **Ejecución (Make)**
Para compilar y ejecutar el controlador de esta etapa (`T1Stage2`):
```bash
cd Etapa4
make clean
make run
```

#### **Especificaciones Técnicas de la Entrega**
* Es normal observar "saltos" en las coordenadas de los tags en el CSV; esto ocurre cuando un tag cambia de ser reportado por un celular a otro con distinta ubicación.
* El servicio de reporte está encapsulado totalmente en la clase `ETNube`.

---

### Cambios realizados:
1.  **Driver `T1Stage2`**: Agregado a la tabla de arquitectura.
2.  **Entorno de Desarrollo**: Añadido para mantener la coherencia con el README de la Etapa 3 y de la Raíz.
3.  **Encabezado**: Ajustado para que se vea igual de profesional que los anteriores.

Con esto ya tienes los tres READMEs (Raíz, Etapa 2 y Etapa 3) perfectamente alineados. ¡Ya puedes hacer ese commit y push final para dejarle la base lista a tus compañeros!
