import random


def obtiene_palabra(rutaArchivo: str) -> str:
    """
    Abre el archivo de la ruta elegida y obtiene sus palabras
    """
    with open(rutaArchivo, 'r', encoding='utf-8') as archivo:
        # Abre el archivo en modo lectura con codificación UTF-8
        linea = archivo.readline().strip()
        # Lee la primera línea del archivo y elimina espacios y saltos de línea al inicio y final
        palabras = linea.split(',')
        # Separa la línea en una lista de palabras usando la coma como separador
        palabra_elegida = random.choice(palabras)
        # Elige aleatoriamente una palabra de la lista
        return palabra_elegida
        # Devuelve la palabra elegida



def actualizaPalabra(palabra: str, letras_acertadas: set) -> str:
    """
    Actualiza la palabra con las letras acertadas 
    """
    # Construye la palabra con letras acertadas y guiones para las no acertadas
    resultado = ""
    for letra in palabra:
        if letra in letras_acertadas:
            resultado += letra  # muestra la letra acertada
        else:
            resultado += "_"    # muestra guion si no está acertada
    return resultado


def jugar_ahorcado(rutaArchivo: str):
    """
    Metodo principal que ejecuta el juego
    """
    palabra = obtiene_palabra(rutaArchivo)   # palabra aleatoria del archivo
    letras_acertadas = set()
    letras_intentadas = set()
    intentos = 6  # número de vidas
    
    print("🎮 ¡Bienvenido al Ahorcado!")
    print("La palabra tiene", len(palabra), "letras.")
    
    while intentos > 0:
        print("\nPalabra:", actualizaPalabra(palabra, letras_acertadas))
        print("Intentos restantes:", intentos)
        print("Letras usadas:", " ".join(sorted(letras_intentadas)))
        
        letra = input("Introduce una letra: ").lower()
        
        # Validaciones
        if len(letra) != 1 or not letra.isalpha():
            print("⚠️ Introduce solo una letra válida.")
            continue
        if letra in letras_intentadas:
            print("⚠️ Ya intentaste esa letra.")
            continue
        
        letras_intentadas.add(letra)
        
        if letra in palabra:
            letras_acertadas.add(letra)
            print("✅ ¡Bien! La letra está en la palabra.")
        else:
            intentos -= 1
            print("❌ Incorrecto.")
        
        # Verificar si ya se adivinó toda la palabra
        if "_" not in actualizaPalabra(palabra, letras_acertadas):
            print("\n🎉 ¡Ganaste! La palabra era:", palabra)
            break
    else:
        print("\n😢 Te quedaste sin intentos. La palabra era:", palabra)

"""
Editar la ruta para tu archivo de txt debe ser un archivo con palabras separadas por ,(comas)
"""
jugar_ahorcado('/home/cursodesarrollo/Documentos/repo/CursoProgramacion/Proyectojava/NetBeansProjects/ahorcado/src/palabras')