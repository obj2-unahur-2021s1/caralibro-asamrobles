package ar.edu.unahur.obj2.caralibro

import java.lang.NullPointerException
import kotlin.math.ceil
import kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue

abstract class Publicacion {
  abstract fun espacioQueOcupa(): Int
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
  var tamañoVideo = 0
  override fun espacioQueOcupa(): Int {
    if (calidad == "SD") {
      tamañoVideo = duracionSegundos
    }
    if (calidad == "HD720") {
      tamañoVideo = duracionSegundos * 3
    }
    if (calidad == "HD1080") {
      tamañoVideo = (duracionSegundos * 3) * 2
    }
    return tamañoVideo
  }
  fun cambiarCalidadDelVideo (calidadNueva : String) {
    calidad = calidadNueva
  }
}

