package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  val amigos = mutableListOf<Usuario>()

  fun agregarPublicacionYPermiso(publicacion: Publicacion, permiso: Permiso) {
    publicacion.cargarPermiso(permiso)
    this.publicaciones.add(publicacion)
  }
  fun darMeGusta(publicacion: Publicacion) { publicacion.agregarMeGusta(this) }

  fun cambiarFactorSiEsFoto(publicacion: Publicacion, factor: Double) {
    if (publicacion is Foto) {
      publicacion.cambiarFactorDeCompresion(factor)
    }
  }
  fun cambiarFactorCompresionParaTodas(factor: Double) {
    this.publicaciones.forEach { p -> this.cambiarFactorSiEsFoto(p, factor) }
  }

  fun agregarAmigo(amigoNuevo: Usuario) { this.amigos.add(amigoNuevo) }

  fun cantidadDeAmigos() = this.amigos.size

  fun cantidadPublicaciones() = this.publicaciones.size

  fun espacioDePublicaciones() = this.publicaciones.sumBy { it.espacioQueOcupa() }

  fun esPublicacionPropia(publicacion: Publicacion) = this.publicaciones.contains(publicacion)

  //Requerimiento 3
  fun esMasAmistosoQue(usuario: Usuario) = this.cantidadDeAmigos() > usuario.cantidadDeAmigos()

  //Requerimiento 4
  fun puedeVerPublicacion(publicacion: Publicacion) = publicacion.puedeSerVistaPor(this)

  //Requerimiento 5
  fun publicacionesQPuedeVer(usuario: Usuario) = this.publicaciones.filter { it.puedeSerVistaPor(usuario) }

  fun esMejorAmigo(usuario: Usuario) = this.publicacionesQPuedeVer(usuario).size == this.cantidadPublicaciones()

  fun mejoresAmigos() = this.amigos.filter { a -> this.esMejorAmigo(a) }.toSet()

  //Requerimiento 6
  fun meGustaTotal() = this.publicaciones.sumBy { it.cuantasVecesFueVotada() }

  fun amigoMasPopular() = this.amigos.maxByOrNull { it.meGustaTotal() }

  //Requerimiento 7
  fun cantidadMeGustaQueDio(usuario: Usuario) = usuario.publicaciones.count { it.dioMeGusta(this) }

  fun stalkeaA(usuario: Usuario) = this.cantidadMeGustaQueDio(usuario) > usuario.cantidadPublicaciones() * 0.9
}