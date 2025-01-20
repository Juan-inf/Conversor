# Conversor de Monedas

El Conversor de Monedas es una herramienta de consola que permite a los usuarios realizar conversiones entre diversas monedas utilizando tasas de cambio actualizadas. Los usuarios pueden ingresar el monto y las monedas de origen y destino, y la aplicación calculará la conversión utilizando las tasas de cambio más recientes.

## Estado del Proyecto

🚀 El proyecto está **finalizado** y disponible para ser utilizado. Actualmente, se encuentra en su versión **1.0.0**.

## Características de la Aplicación y Demostración

La aplicación permite realizar las siguientes acciones:

- **Conversión de Monedas:** Ingresar el monto y seleccionar las monedas de origen y destino.
- **Obtención de Tasas de Cambio:** Obtener las tasas de cambio actuales entre las monedas seleccionadas.
- **Visualización de Resultados:** Mostrar el monto convertido junto con la tasa de cambio utilizada.

Además, ofrece un **menú interactivo** y la posibilidad de consultar el historial de conversiones realizadas.

## Acceso al Proyecto

Puedes clonar el repositorio del proyecto en tu máquina local con el siguiente comando:
bash
Copiar
git clone https://github.com/Juan-inf/Conversor.git
Luego, accede al directorio del proyecto:

cd ConversorDeMonedas
Y para ejecutar el programa, primero compila los archivos Java:

bash
Copiar
javac ui/MonedaConverter.java services/ExchangeRateService.java models/Currency.java

Finalmente, ejecuta la aplicación:

bash
Copiar
java ui.MonedaConverter

Tecnologías Utilizadas
Java: Lenguaje de programación utilizado para implementar la lógica del proyecto.
JDK (Java Development Kit): Necesario para compilar y ejecutar el código Java.
Scanner: Herramienta de Java utilizada para capturar la entrada de datos por consola.
API de Tasas de Cambio: Se utiliza una API externa para obtener las tasas de cambio en tiempo real.
JSON: Formato utilizado para procesar y mostrar los datos obtenidos de la API.
Personas Contribuyentes
Este proyecto ha sido realizado por varias personas, especialmente Alura, quienes contribuyeron significativamente al desarrollo del proyecto.

Personas Desarrolladoras del Proyecto
Este proyecto fue desarrollado y es mantenido por Juan Calla.

Conclusión
Conversor de Monedas es una aplicación sencilla y eficiente para realizar conversiones de divisas. Está diseñada para ser fácil de usar y accesible desde la consola, lo que permite realizar conversiones de manera rápida y con tasas de cambio actualizadas.


Este formato sigue una estructura clara y organizada que facilita la comprensión del proyecto para cualquier persona que lo lea en GitHub, proporcionándoles acceso a instrucciones, funcionalidades, tecnologías y contribuyentes.
