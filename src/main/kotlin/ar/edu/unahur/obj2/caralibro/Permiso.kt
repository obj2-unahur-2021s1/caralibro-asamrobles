package ar.edu.unahur.obj2.caralibro

abstract class Permiso {
    abstract fun permiteVerAUsuario(usuario: Usuario): Boolean
}

class Publico: Permiso() {
    override fun permiteVerAUsuario(usuario: Usuario) = true
}
class SoloAmigos(var amigos: List<Usuario>): Permiso() {
    override fun permiteVerAUsuario(usuario: Usuario) = amigos.contains(usuario)
}

class Excluyente(var excluidos: List<Usuario>): Permiso() {
    override fun permiteVerAUsuario(usuario: Usuario) = !excluidos.contains(usuario)
}

class Incluyente(var incluidos: List<Usuario>): Permiso() {
    override fun permiteVerAUsuario(usuario: Usuario) = incluidos.contains(usuario)
}