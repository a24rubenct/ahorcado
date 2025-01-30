fun main() {
    val rm = ReproductorMidi("pugnodollari.mid")
    println("Cargando juego, espera instrucciones...")
    Thread.sleep(7000) //la música tarda un ratito y por eso hacemos este sleep (bajado a 7s)

    //variables globales
    val posibles = listOf("gato", "pato", "pavo", "vaca", "toro", "lobo", "loro", "reno", "rana") //posibles palabras
    val secreta = posibles.random() //se escoge la palabra secreta
    val adivinada = CharArray(secreta.length) { '*' } //asi se muestra la palabra segun se vaya jugando
    var intentos = 7 //se fija el numero de intentos
    var fallos = 0 //se fija el numero de fallos

    //bienvenida
    println("Bienvenido al juego del AHORCADO")
    println("Intenta adivinar el nombre de un animal de cuatro letras")

    //bucle para entrada por teclado
    while (intentos > 0 && adivinada.contains('*')) {
        println("Tu palabra con letras descubiertas por el momento es: ${adivinada.joinToString("")}")
        print("Llevas $fallos fallos (máximo permitido 7 fallos). Introduce una letra que pueda estar en la palabra secreta: ")
        val entrada = readln()
        val letra = entrada[0]

        //si la letra está
        if (secreta.contains(letra)) {
            println("Correcto, la letra '$entrada' está en la palabra secreta.")
            println()
            for (i in secreta.indices) {
                if (secreta[i] == letra) {
                    adivinada[i] = letra
                }
            }

        //si la letra no está
        } else {
            println("Has fallado, la letra '$entrada' no está en la palabra secreta.")
            println()
            DibujoAhorcado.dibujar(8 - intentos)
            println()
            intentos--
            fallos++
        }
    }

    //se acaba el bucle, comprobamos si has ganado o perdido
    if (intentos > 0) {
        println("Enhorabuena, has adivinado la palabra secreta '$secreta' con sólo $fallos fallos")
        println()
    } else {
        DibujoAhorcado.dibujar(0)
        println("Lo siento, has perdido. La palabra secreta era '$secreta'")
        println()
    }
    rm.cerrar() //si no cerramos el reproductor no acaba el main
}