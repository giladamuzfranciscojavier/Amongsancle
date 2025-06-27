NOTA IMPORTANTE (2025)
El siguiente proyecto se muestra tal cual se presentó como tarea del módulo de Programación de DAM, siendo los únicos añadidos recientes esta pequeña nota y la release compilada. Consiste en un pequeño videojuego con interfaz de usuario por consola inspirado en el famoso videojuego Among Us. Además de los puntos tratados en el resto del documento, uno de los objetivos de la tarea era el desarrollo de menús mediante el patrón composite.

Desafortunadamente, el código empleado para limpiar refrescar la consola no funciona en determinados terminales de consola, incluídos los de windows (el proyecto fue desarrollado y probado en visual studio code, en cuyo terminal sí funciona).

# AmongSanCle

## Impresiones
- En un caso real habría sido más adecuado utilizar un archivo json o xml para almacenar las opciones de configuración en vez de generarlas cada vez que se inicia el programa, pero se omite ya que no es competencia de este módulo.

- La gestión de los jugadores en partida es algo "sucia", recurriendo a 3 listas distintas (una para estudiantes, otra para impostores y otra para ambos tipos de jugadores). Esto tiene sentido dentro de la lógica del programa, pero igualmente implica
  que cada muerte supone modificar dos listas distintas, lo que podría suponer un riesgo de inconsistencia en los datos de las listas.

## Uso de iteradores
Fue necesario el uso de iteradores para gestionar las muertes de los jugadores, ya que se borran dichos jugadores de las listas y esto provocaría una excepción por modificación concurrente utilizando un for mejorado.

## Sobreescritura hashcode/equals
Fue necesario sobreescribir el método equals para poder comprobar si dos objetos de una clase (en este caso si las habitaciones en las que se encuentran los distintos jugadores) coinciden o no y, por lo tanto, 
fue necesario también sobreescribir el método hashcode para que los valores devueltos por el mismo sean consistentes en base a la propiedad que se quiera comparar (en este caso el nombre de la habitación).

## Uso de interfaz Comparable
Todas las clases cuyos objetos se quieran ordenar (jugadores, tareas y habitaciones) deben implementar la interfaz Comparable para indicar bajo qué criterio se deben ordenar (en este caso, para los jugadores y las habitaciones es bastante simple, solo se comparan los nombres,
pero las tareas requieren ordenar primero por habitación y, si la habitación es la misma, por nombre).

## Lista de mejoras
- En lugar de limitarse a ser strings atributo de las tareas, las habitaciones son objetos que, además de su nombre, contienen una lista con los jugadores muertos en dicha habitación (se limpian al final de cada juicio).
- De forma algo más similar al original, en lugar de elegir a quien expulsar al final de cada ronda se celebra una especie de "juicio" cada vez que un jugador encuentra muertos o presencia un asesinato. En este último caso, para mantener el componente de cara o cruz que pide el enunciado,
 existe la posibilidad de que el impostor realice una acusación falsa dirigida al testigo (probabilidad ajustable en opciones).
- Varias opciones de menú, incluyendo otro submenú para las habitaciones (al crear una tarea se elige una habitación de la lista en lugar de introducir sin más el nombre de la misma), cambiar la probabilidad de acusaciones falsas y un modo debug que muestra cuando un impostor comete un asesinato
- Los sucesos importantes aparecen coloreados (las detecciones de cadáveres/asesinatos y las expulsiones de estudiantes aparecen en rojo y las expultiones de impostores aparecen en verde)

## Otras observaciones
- Los impostores cuentan con una lista de los jugadores que han eliminado, tal y como se pide en el enunciado, pero no tiene una utilidad real dentro de la aplicación (de hecho se realiza un sort de la misma cada vez que se añade un jugador para que cumpla otro de los requisitos).
- Se da por supuesto que el requisito de que puedan haber varios asesinatos por ronda se refiere a uno por impostor, por lo que evidentemente solo se cumple si hay más de uno en la partida
