¡Perfecto! He actualizado la sección de integrantes para dejar los espacios listos para tus compañeros, manteniendo el formato y el estilo de ingeniería del documento.

---

### Tarea 1: Simulador EloTelTag - Etapa 3
**Curso:** ELO329 - Diseño y Programación Orientados a Objetos  
**Institución:** Universidad Técnica Federico Santa María  
**Integrantes:** 
* Cristhofer Sandoval Huilipán
* [Nombre Integrante 2]
* [Nombre Integrante 3]
* [Nombre Integrante 4]

#### **Descripción General**
Esta etapa implementa la infraestructura completa de red distribuida e inspirada en AirTag. Se integra el reporte indirecto para todos los dispositivos sin GPS (`EloTelTag` y `Tablet`) y la consulta activa de datos mediante la interfaz de usuario centralizada **FindMy**.

#### **Arquitectura del Sistema**
| Clase | Responsabilidad |
| :--- | :--- |
| **`Tablet`** | Dispositivo sin GPS que depende de reportes indirectos pero posee el servicio FindMy. |
| **`Viewer`** | Clase encargada de formatear y mostrar los bienes de la `ETNube` en consola. |
| **`SimuladorTest`** | Driver final que integra el ciclo de vida completo de la simulación. |
| **`Cellular` / `Tablet`** | Poseen una instancia de `Viewer` (composición) para interpelar a la nube. |

#### **Gestión de Archivos de Entrada**
* **`config.txt`**: Carga completa de la jerarquía. El flag de Tablet (0 o 1) habilita su instanciación y reporte.
* **`move.txt`**: Soporta el desplazamiento de toda la jerarquía de equipos. Cada movimiento gatilla una actualización de la "App FindMy" en la terminal si el equipo movido tiene pantalla.

#### **Lógica de Red y Localización**
* **Detección Comunitaria:** Un celular detecta cualquier tag o tablet a una distancia $\le 10$ metros, permitiendo que usuarios ajenos colaboren en la red.
* **Visualización:** El `Viewer` organiza los datos en "Items" (tags) y "Dispositivos" (celulares/tablets) según el propietario registrado.

#### **Ejecución (Make)**
Para ejecutar el programa final denominado `SimuladorTest`:
```bash
make clean
make run
```

#### **Especificaciones Técnicas de la Entrega**
* La salida por terminal simula la apertura de la App cada vez que un celular o tablet se desplaza.
* Se incluye una sección de verificación técnica basada en la geometría euclidiana para asegurar que los reportes de Tablets sigan la misma restricción de 10m que los tags.