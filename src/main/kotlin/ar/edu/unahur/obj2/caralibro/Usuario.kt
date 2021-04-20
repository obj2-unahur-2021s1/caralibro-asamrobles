package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  val amigos = mutableListOf<Usuario>()

  fun agregarPublicacionYPermiso(publicacion: Publicacion, permiso: Permiso) {
    publicacion.cargarPermiso(permiso)
    publicaciones.add(publicacion)
  }
  fun agregarAmigo(amigoNuevo: Usuario) {amigos.add(amigoNuevo)}

  fun cantidadDeAmigos() = amigos.size

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }

  fun cualEsMasAmistoso(usuario1: Usuario ,usuario2 : Usuario) : Usuario {
    var elMasAmistoso = Usuario()
    if (usuario1.cantidadDeAmigos() > usuario2.cantidadDeAmigos()) {
      elMasAmistoso = usuario1
    }
      else elMasAmistoso = usuario2
    return elMasAmistoso
  }
}
