package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil

abstract class Publicacion {
  var cantidadDeMeGusta = 0
  abstract fun espacioQueOcupa(): Int
  fun darMeGusta() {  cantidadDeMeGusta += 1 }
  fun cuantasVecesFueVotada() = cantidadDeMeGusta
}
class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  var factorDeCompresion = 0.7
  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()
  fun cambiarFactorDeCompresion(nuevoValor : Double) { factorDeCompresion = nuevoValor }
}

class Texto(val contenido: String) : Publicacion() {
  override fun espacioQueOcupa() = contenido.length
}

class Video (var calidad : String, var duracionSegundos : Int) : Publicacion() {
  var tamanioVideo = 0
  override fun espacioQueOcupa(): Int {
    if (calidad == "SD") {
      tamanioVideo = duracionSegundos
    }
    if (calidad == "HD720") {
      tamanioVideo = duracionSegundos * 3
    }
    if (calidad == "HD1080") {
      tamanioVideo = (duracionSegundos * 3) * 2
    }
    return tamanioVideo
  }
  fun cambiarCalidadDelVideo (calidadNueva : String) {
    calidad = calidadNueva
  }
}

