package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  val amigos = mutableListOf<Usuario>()
  val excluidos = mutableListOf<Usuario>()
  val incluidos = mutableListOf<Usuario>()


  fun agregarExcluidos(usuario : Usuario) { excluidos.add(usuario) }
  fun agregarIncluidos(usuario : Usuario) { incluidos.add(usuario) }

  fun agregarPublicacionYPermiso(publicacion: Publicacion, permiso: Permiso) {
    publicacion.cargarPermiso(permiso)
    this.publicaciones.add(publicacion)
  }

  fun agregarAmigo(amigoNuevo: Usuario) {amigos.add(amigoNuevo)}

  fun cantidadDeAmigos() = this.amigos.size

  fun espacioDePublicaciones() = this.publicaciones.sumBy { it.espacioQueOcupa() }

  fun cualEsMasAmistoso(usuario1: Usuario ,usuario2 : Usuario) : Usuario {
    var elMasAmistoso = Usuario()
    if (usuario1.cantidadDeAmigos() > usuario2.cantidadDeAmigos()) {
      elMasAmistoso = usuario1
    }
      else elMasAmistoso = usuario2
    return elMasAmistoso
  }
  fun esPublicacionPropia(publicacion: Publicacion) = publicaciones.contains(publicacion)

  fun totalPublicacionesQuePuedeVerAmigo(amigo: Usuario) = this.publicaciones.filter{ it.puedeSerVistoPor(amigo)}.size

  fun amigosQueVenTodasLasPublicaciones() =
    this.amigos.filter { a -> this.totalPublicacionesQuePuedeVerAmigo(a) == this.publicaciones.size }.toSet()
}
