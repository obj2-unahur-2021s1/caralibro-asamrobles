package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  val amigos = mutableListOf<Usuario>()

  fun agregarPublicacionYPermiso(publicacion: Publicacion, permiso: Permiso) {
    publicacion.cargarPermiso(permiso)
    this.publicaciones.add(publicacion)
  }
  fun darMeGusta(publicacion: Publicacion) { publicacion.agregarMeGusta(this) }

  fun agregarAmigo(amigoNuevo: Usuario) { this.amigos.add(amigoNuevo) }

  fun cantidadDeAmigos() = this.amigos.size

  fun cantidadPublicaciones() = this.publicaciones.size

  fun espacioDePublicaciones() = this.publicaciones.sumBy { it.espacioQueOcupa() }

  fun esPublicacionPropia(publicacion: Publicacion) = this.publicaciones.contains(publicacion)

  //Requerimiento 3
  fun cualEsMasAmistoso(usuario1: Usuario ,usuario2 : Usuario) : Usuario {
    var elMasAmistoso = Usuario()
    if (usuario1.cantidadDeAmigos() > usuario2.cantidadDeAmigos()) {
      elMasAmistoso = usuario1
    }
    else elMasAmistoso = usuario2
    return elMasAmistoso
  }

  //Requerimiento 5
  fun publicacionesQPuedeVer(usuario: Usuario) = this.publicaciones.filter{ it.puedeSerVistoPor(usuario) }

  fun esMejorAmigo(usuario: Usuario) = this.publicacionesQPuedeVer(usuario).size == this.cantidadPublicaciones()

  fun mejoresAmigos() = this.amigos.filter { a -> this.esMejorAmigo(a) }.toSet()

  //Requerimiento 6
  fun meGustaTotal() = this.publicaciones.sumBy { it.cuantasVecesFueVotada() }

  fun amigoMasPopular() = this.amigos.maxByOrNull { it.meGustaTotal() }

}