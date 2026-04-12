```markdown
# Etapa 1: Simulador de Movimientos

Esta entrega inicial implementa la arquitectura base para el seguimiento de equipos. El sistema es capaz de leer una configuración inicial de usuarios y procesar una serie de movimientos vectoriales.

## Estructura de Archivos

- **src/**:
  - `T1Stage1.java`: Clase principal que orquesta la lectura de archivos y el ciclo de simulación.
  - `Territory.java`: Clase responsable de gestionar la lista de equipos y generar el reporte de salida.
  - `EloTelTag.java`: Representación de los dispositivos, gestionando su posición y desplazamientos.

- **Datos y Compilación**:
  - `Makefile`: Automatiza la compilación y ejecución.
  - `config.txt`: Define los equipos iniciales por usuario.
  - `move.txt`: Contiene los comandos de desplazamiento para la simulación.

## Instrucciones de Compilación y Ejecución

Desde la terminal, dentro de esta carpeta, utilice los siguientes comandos:

1. **Compilar**:
   ```bash
   make
   ```

2. **Ejecutar**:
   ```bash
   make run
   ```

3. **Limpiar**:
   ```bash
   make clean
   ```

## Detalles de Implementación
- **Salida**: Los resultados se almacenan en `output.csv` utilizando el tabulador como separador de columnas.
- **Precisión**: Se utiliza el tipo de dato `double` para las coordenadas espaciales.
- **Configuración Regional**: Se emplea `java.util.Locale.US` para garantizar que el punto sea reconocido como separador decimal en cualquier sistema.
