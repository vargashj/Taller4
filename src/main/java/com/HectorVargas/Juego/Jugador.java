package com.HectorVargas.Juego;

public class Jugador {
    // Clase Jugador para representar a cada jugador

        private String nombre;
        private int dinero;

        public Jugador(String nombre, int dinero) {
            this.nombre = nombre;
            this.dinero = dinero;
        }

        public String getNombre() {
            return nombre;
        }

        public int getDinero() {
            return dinero;
        }

        public void aumentarDinero(int cantidad) {
            dinero += cantidad;
        }

        public void restarDinero(int cantidad) {
            dinero -= cantidad;
        }
    }


