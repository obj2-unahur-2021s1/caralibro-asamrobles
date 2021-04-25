package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil

abstract class Publicacion {
  var listaDeMeGusta = mutableSetOf<Usuario>()
  lateinit var permiso: Permiso // var de tipo Permiso sin inicializar

  abstract fun espacioQueOcupa(): Int

  fun agregarMeGusta(usuario: Usuario) { this.listaDeMeGusta.add(usuario) }

  fun cuantasVecesFueVotada() = this.listaDeMeGusta.size

  fun cargarPermiso(permisoNuevo: Permiso) { this.permiso = permisoNuevo }

  fun puedeSerVistoPor(usuario: Usuario) = this.permiso.permiteVerAUsuario(usuario) || usuario.esPublicacionPropia(this)
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
