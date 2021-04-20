package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.*
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    // Publicaciones
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val videoDeCumpleanios = Video ("SD", 3000)
    val videoDeCumpleaniosEn720 = Video ("HD720", 3000)
    val videoDeCumpleniosEn1080 = Video ("HD1080", 3000)

    // Usuarios
    val juana = Usuario()

    // Amigos
    val ferAsam = Usuario()
    val luchoRobles = Usuario()
    val faloiFede = Usuario()
    val jorge = Usuario()
    val diego = Usuario()
    val marcos = Usuario()
    val marcelo = Usuario()
    val pepe = Usuario()

    // Acciones Sobre los usuarios
    //Agregar publicaciones y permisos
    juana.agregarPublicacionYPermiso(fotoEnCuzco, SoloAmigos(juana.amigos))
    juana.agregarPublicacionYPermiso(saludoCumpleanios, Excluyente(mutableListOf(pepe, ferAsam)))
    juana.agregarPublicacionYPermiso(videoDeCumpleanios, Incluyente(mutableListOf(faloiFede, luchoRobles)))

    //Agregar amigos
    juana.agregarAmigo(ferAsam)
    juana.agregarAmigo(luchoRobles)
    juana.agregarAmigo(faloiFede)

    // Acciones Sobre las publicaciones
    fotoEnCuzco.darMeGusta()
    fotoEnCuzco.darMeGusta()
    fotoEnCuzco.darMeGusta()
    videoDeCumpleanios.darMeGusta()

    //Tests

    describe("Una publicación") {
      describe("de tipo foto") {
        it("ocupa ancho * alto * compresion bytes") {
          fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
        }
      }

      describe("de tipo texto") {
        it("ocupa tantos bytes como su longitud") {
          saludoCumpleanios.espacioQueOcupa().shouldBe(45)
        }
      }

      describe ("de tipo Video") {
        it ("cuanto espacio ocupa el SD") {
          videoDeCumpleanios.espacioQueOcupa().shouldBe(3000)
        }
        it ("cuanto espacio ocupa el HD720") {
          videoDeCumpleaniosEn720.espacioQueOcupa().shouldBe(9000)
        }
        it ("cuanto espacio ocupa el HD1080") {
          videoDeCumpleniosEn1080.espacioQueOcupa().shouldBe(18000)
        }
        videoDeCumpleanios.cambiarCalidadDelVideo("HD720")
        it ("cuanto espacio ocupa el SD despues de cambiar su calidad a HD720") {
          videoDeCumpleanios.espacioQueOcupa().shouldBe(9000)
        }
      }

      describe ("Cuantas veces fue votada una publicacion") {
        it ("Cuantas veces fue botada Cuzco se espera 3") {
          fotoEnCuzco.cuantasVecesFueVotada().shouldBe(3)
        }
        it ("Cuantas veces fue votada videoDeCumpleaños se espera 1") {
          videoDeCumpleanios.cuantasVecesFueVotada().shouldBe(1)
        }
      }
    }

    describe("Un usuario") {

      it("puede calcular el espacio que ocupan sus publicaciones") {
        juana.espacioDePublicaciones().shouldBe(553548)
      }
      it(" Cuantos amigos tiene juana") {
        juana.cantidadDeAmigos().shouldBe(3)
      }
      it(" Cuantos amigos tiene pepe") {
        pepe.cantidadDeAmigos().shouldBe(0)
      }
      it(" Cual es mas amistoso pepe o juana : Es Juana") {
        juana.cualEsMasAmistoso(juana, pepe).shouldBe(juana)
      }
      pepe.agregarAmigo(luchoRobles)
      pepe.agregarAmigo(luchoRobles)
      pepe.agregarAmigo(luchoRobles)
      pepe.agregarAmigo(luchoRobles)

      it("Cual es mas amistoso pepe o juana : Es Pepe") {
        juana.cualEsMasAmistoso(juana, pepe).shouldBe(pepe)
      }

      describe("Saber si un usuario puede ver una publicacion de otro") {
        juana.agregarAmigo(pepe)

        it("pepe puede ver fotoEnCuzco con permiso soloAmigos") {
          fotoEnCuzco.puedeSerVistoPor(pepe).shouldBeTrue()
        }
        it("pepe no puede ver videoCumpleanios con permiso incluyente") {
          videoDeCumpleanios.puedeSerVistoPor(pepe).shouldBeFalse()
        }
        it("pepe no puede ver saludoCumpleanios con permiso excluyente") {
          saludoCumpleanios.puedeSerVistoPor(pepe).shouldBeFalse()
        }
      }

      describe("Cambio de permiso fotoEnCuzco a Excluyente") {
        fotoEnCuzco.cargarPermiso(Excluyente(mutableListOf(pepe, ferAsam, faloiFede)))

        it("pepe no puede ver fotoEnCuzco con permiso excluyente") {
          fotoEnCuzco.puedeSerVistoPor(pepe).shouldBeFalse()
        }
      }

      describe("Req 5: Mejores amigos de un usuario") {
        juana.agregarAmigo(jorge)
        juana.agregarAmigo(diego)
        juana.agregarAmigo(marcos)
        juana.agregarAmigo(marcelo)
      }
    }
  }
})
