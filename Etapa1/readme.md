### Tarea 1: Simulador EloTelTag - Etapa 1
**Curso:** ELO329 - Diseño y Programación Orientados a Objetos  
**Institución:** Universidad Técnica Federico Santa María  
**Integrantes:** 
* Cristhofer Sandoval Huilipán
* [Nombre Integrante 2]
* [Nombre Integrante 3]
* [Nombre Integrante 4]


#### **Descripción General**
Esta fase inicial se centra en la gestión de archivos de entrada y la representación básica de los rastreadores pasivos `EloTelTag`. El sistema valida la lectura de parámetros de configuración y la ejecución de comandos de movimiento aplicados exclusivamente a los tags. En esta etapa, el reporte refleja la trayectoria física real de los equipos sin considerar aún la infraestructura de red o gateways.

#### **Arquitectura del Sistema**
| Clase | Responsabilidad |
| :--- | :--- |
| **`EloTelTag`** | Encapsula la identidad (dueño y nombre) y gestiona su posición física $(x, y)$. |
| **`Territory`** | Contenedor que administra la colección global de equipos y delega la impresión de estados. |
| **`T1Stage1`** | Driver principal encargado del parsing de archivos y el control del flujo de simulación. |

#### **Gestión de Archivos de Entrada**
* **`config.txt`**: Define el número de personas y asigna tags dinámicamente con sus posiciones iniciales. Ignora dispositivos con pantalla en esta etapa.
* **`move.txt`**: Motor de la simulación. Contiene instrucciones con el formato `Dueño.Tag delta_x delta_y`. Estos valores se suman directamente a la posición actual del equipo.

#### **Lógica de Red y Localización**
* **Reporte Directo:** El archivo `output.csv` registra las coordenadas exactas tras cada comando de movimiento en `move.txt`.
* **Consistencia:** Si un equipo no recibe comandos en un paso, su posición se mantiene constante en el reporte para asegurar una ruta coherente.

#### **Ejecución (Make)**
Para compilar y ejecutar el simulador de la Etapa 1:
```bash
make clean
make run
```
*El Makefile automatiza la creación del directorio `bin/` y la ejecución con los archivos de texto proporcionados.*

#### **Especificaciones Técnicas de la Entrega**
* El programa ignora las posiciones de celulares y tablets en el `config.txt` para cumplir con la restricción de la Etapa 1.
* El archivo de salida muestra la "verdad absoluta" de la ubicación, permitiendo validar el correcto parsing de deltas.
