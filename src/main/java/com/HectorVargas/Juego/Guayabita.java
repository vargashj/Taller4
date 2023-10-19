package com.HectorVargas.Juego;

import com.HectorVargas.Juego.Dado;
import com.HectorVargas.Juego.Jugador;

import javax.swing.*;

public class Guayabita {


        public static void main(String[] args) {
            ImageIcon icon =new ImageIcon("C:\\Users\\hector\\Taller4\\src\\main\\th (1).jpg");

            JFrame frame = new JFrame();
            frame.setIconImage(icon.getImage());

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "Bienvenidx al juego de la guayabita" + System.lineSeparator() + "¿Que quieres hacer?",
                    "Guayabita",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    new String[] {"Jugar", "Ver instrucciones"},
                    null
            );

            if (opcion == 0) {
                // Crear un nuevo juego
                // Solicitar número de jugadores y apuestas mínima
                int numJugadores = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de jugadores:", "Guayabita - Configuracion", JOptionPane.PLAIN_MESSAGE));
                int apuestaMinima = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la apuesta minima:", "Guayabita - Configuracion", JOptionPane.PLAIN_MESSAGE));

                // Crear los jugadores
                com.HectorVargas.Juego.Jugador[] jugadores = new com.HectorVargas.Juego.Jugador[numJugadores];
                for (int i = 0; i < numJugadores; i++) {
                    String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del jugador " + (i+1) + ":");
                    int dinero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el monto de dinero de " + nombre + ":"));
                    jugadores[i] = new com.HectorVargas.Juego.Jugador(nombre, dinero);
                }

                // Calcular el pote inicial
                int pote = numJugadores * apuestaMinima;

                // Iniciar el juego
                int jugadorActual = 0;
                while (pote > 0) {
                    com.HectorVargas.Juego.Jugador jugador = jugadores[jugadorActual];

                    // Mostrar ventana de lanzar el dado al jugador actual
                    int resultadoDado = JOptionPane.showOptionDialog(null, "Jugador: " + jugador.getNombre() + "\nPote actual: " + pote + "¿Deseas lanzar el dado?", "Guayabita - Lanzar dado", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon, new String[] {"Si", "No"}, null);
                    if (resultadoDado == 1) {
                        // El jugador no quiere lanzar el dado, juego terminado
                        break;
                    }

                    // Lanzar el dado
                    int dado = com.HectorVargas.Juego.Dado.lanzarDado();

                    // Mostrar ventana con el resultado del dado
                    JOptionPane.showMessageDialog(null, "Jugador: " + jugador.getNombre() + "\nResultado del dado: " + dado);

                    if (dado == 1 || dado == 6) {
                        // El jugador pierde la posibilidad de apostar
                        // Pasar al siguiente jugador
                        jugadorActual = (jugadorActual + 1) % numJugadores;
                        continue;
                    }

                    // Ver si el jugador quiere apostar
                    int opcionApostar = JOptionPane.showOptionDialog(null, "Jugador: " + jugador.getNombre() + "\nEste es tu dado: " + dado + "\n¿Deseas hacer una apuesta?", "Guayabita - Apuesta", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icon, new String[] {"Si", "No"}, null);
                    if (opcionApostar == 1) {
                        // El jugador no quiere apostar, pasar al siguiente jugador
                        jugadorActual = (jugadorActual + 1) % numJugadores;
                        continue;
                    }

                    // Verificar si el jugador tiene suficiente dinero para apostar
                    if (jugador.getDinero() == 0) {
                        // El jugador no tiene dinero para apostar, pasar al siguiente jugador
                        jugadorActual = (jugadorActual + 1) % numJugadores;
                        continue;
                    }

                    // Solicitar al jugador el monto de la apuesta
                    int apuesta = Integer.parseInt(JOptionPane.showInputDialog(null, "Jugador: " + jugador.getNombre() + "\nMonto a apostar (maximo " + jugador.getDinero() + "):"));
                    if (apuesta > jugador.getDinero()) {
                        // El jugador no tiene suficiente dinero para apostar ese monto, pasar al siguiente jugador
                        jugadorActual = (jugadorActual + 1) % numJugadores;
                        continue;
                    }

                    int nuevoDado = com.HectorVargas.Juego.Dado.lanzarDado();
                    if (nuevoDado > dado) {
                        // El jugador gana
                        jugador.aumentarDinero(apuesta);
                        pote -= apuesta;
                    } else {
                        // El jugador pierde, se suma la apuesta al pote
                        jugadores[jugadorActual].restarDinero(apuesta);
                        pote += apuesta;
                    }

                    // Pasar al siguiente jugador
                    jugadorActual = (jugadorActual + 1) % numJugadores;
                }

                // Mostrar el resultado del juego
                StringBuilder resultadoFinal = new StringBuilder();
                for (int i = 0; i < numJugadores; i++) {
                    resultadoFinal.append(jugadores[i].getNombre()).append(": ").append(jugadores[i].getDinero()).append("\n");
                }
                JOptionPane.showMessageDialog(null, "El juego ha terminado\nResultados:\n" + resultadoFinal.toString(), "Guayabita - Resultados", JOptionPane.PLAIN_MESSAGE);
            } else if (opcion == 1) {

                // Mostrar instrucciones


                String instrucciones = "Instrucciones del juego Guayabita:\n\n" +
                        "1. Cada jugador debe ingresar su nombre y el monto de dinero con el que comenzara el juego.\n" +
                        "2. En cada turno, se lanzara un dado y se mostrara el resultado.\n" +
                        "3. Si el resultado es 1 o 6, el jugador pierde su turno y pasa al siguiente.\n" +
                        "4. Si el resultado es diferente de 1 o 6, el jugador puede apostar una cantidad (maximo su dinero).\n" +
                        "5. Luego, se lanzara otro dado y si el resultado es mayor que el primero, el jugador gana la apuesta.\n" +
                        "6. Si el resultado es igual o menor al primer dado, el jugador pierde la apuesta.\n" +
                        "7. El juego continua hasta que un jugador decida no continuar o se quede sin dinero.\n" +
                        "8. Al final del juego, se mostraran los resultados.\n";

                JOptionPane.showMessageDialog(



                        null, instrucciones, "Guayabita - Instrucciones", JOptionPane.PLAIN_MESSAGE);



                // Mostrar instrucciones
                JOptionPane.showMessageDialog(null, "Instrucciones del juego", "Guayabita - Instrucciones", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }


