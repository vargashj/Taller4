package com.HectorVargas.Juego;

public class Dado {
    // Clase Dado para simular el lanzamiento de un dado de 6 caras

        public static int lanzarDado() {
            return (int) (Math.random() * 6) + 1;
        }
    }

