package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.*
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    // Publicaciones
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val videoDeCumpleanios = Video ("SD", 3000)
    val videoDeCumpleaniosEn720 = Video ("HD720", 3000)
    val videoDeCumpleaniosEn1080 = Video ("HD1080", 3000)
    val saludoVacaciones = Texto("Ojala te toque buen clima Pepito, que descanses")
    val fotoEnArequipa = Foto(1080, 1920)
    val fotoEnChascomus = Foto(1080, 1920)
    val videoEnMendoza = Video("HD720", 5000)
    val saludoMendoza = Texto("Vacaciones etilicas")
    val saludoChascomus = Texto("Atalaya ya no es lo mismo")

    // Usuarios
    val juana = Usuario()
    val ferAsam = Usuario()
    val luchoRobles = Usuario()
    val faloiFede = Usuario()
    val jorge = Usuario()
    val diego = Usuario()
    val marcos = Usuario()
    val pepe = Usuario()

    // Acciones Sobre los usuarios
    //Agregar publicaciones y permisos
    juana.agregarPublicacionYPermiso(fotoEnCuzco, SoloAmigos(juana.amigos))
    juana.agregarPublicacionYPermiso(saludoCumpleanios, Excluyente(mutableListOf(pepe, jorge)))
    juana.agregarPublicacionYPermiso(videoDeCumpleanios, Incluyente(mutableListOf(faloiFede, ferAsam, luchoRobles)))

    //luchoRobles
    luchoRobles.agregarPublicacionYPermiso(videoDeCumpleaniosEn720, SoloAmigos(luchoRobles.amigos))
    luchoRobles.agregarPublicacionYPermiso(videoDeCumpleaniosEn1080, Excluyente(mutableListOf(pepe, jorge)))

    //ferAsam
    ferAsam.agregarPublicacionYPermiso(saludoVacaciones, Incluyente(mutableListOf(faloiFede, ferAsam)))
    ferAsam.agregarPublicacionYPermiso(fotoEnArequipa, Publico())

    //faloiFede
    faloiFede.agregarPublicacionYPermiso(fotoEnChascomus, Incluyente(mutableListOf(pepe, jorge)))
    faloiFede.agregarPublicacionYPermiso(videoEnMendoza, SoloAmigos(faloiFede.amigos))
    faloiFede.agregarPublicacionYPermiso(saludoMendoza, SoloAmigos(faloiFede.amigos))
    faloiFede.agregarPublicacionYPermiso(saludoChascomus, SoloAmigos(faloiFede.amigos))

    //Agregar amigos
    juana.agregarAmigo(ferAsam)
    juana.agregarAmigo(luchoRobles)
    juana.agregarAmigo(faloiFede)

    // Acciones Sobre las publicaciones
    //juana
    ferAsam.darMeGusta(fotoEnCuzco)
    luchoRobles.darMeGusta(fotoEnCuzco)
    faloiFede.darMeGusta(fotoEnCuzco)
    pepe.darMeGusta(videoDeCumpleanios)

    //luchoRobles
    ferAsam.darMeGusta(videoDeCumpleaniosEn720)
    juana.darMeGusta(videoDeCumpleaniosEn720)
    faloiFede.darMeGusta(videoDeCumpleaniosEn720)
    ferAsam.darMeGusta(videoDeCumpleaniosEn1080)

    //ferAsam
    luchoRobles.darMeGusta(saludoVacaciones)
    faloiFede.darMeGusta(fotoEnArequipa)
    juana.darMeGusta(fotoEnArequipa)

    //faloiFede
    luchoRobles.darMeGusta(fotoEnChascomus)
    ferAsam.darMeGusta(fotoEnChascomus)
    juana.darMeGusta(fotoEnChascomus)
    luchoRobles.darMeGusta(videoEnMendoza)
    ferAsam.darMeGusta(videoEnMendoza)
    juana.darMeGusta(videoEnMendoza)
    juana.darMeGusta(saludoChascomus)
    juana.darMeGusta(saludoMendoza)

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
          videoDeCumpleaniosEn1080.espacioQueOcupa().shouldBe(18000)
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

      describe("un me gusta por usuario") {
        it("la cantidad de me gusta debe ser 3 para fotoEnCuzco y 1 para videoDeCumpleanios") {
          fotoEnCuzco.cuantasVecesFueVotada().shouldBe(3)
          videoDeCumpleanios.cuantasVecesFueVotada().shouldBe(1)
        }
      }

      describe("me gusta dados por usuarios que ya lo hicieron no modifican el total de votos de la publicacion") {
        ferAsam.darMeGusta(fotoEnCuzco)
        pepe.darMeGusta(videoDeCumpleanios)

        it("la cantidad de me gusta debe ser 3 para fotoEnCuzco y 1 para videoDeCumpleanios") {
          fotoEnCuzco.cuantasVecesFueVotada().shouldBe(3)
          videoDeCumpleanios.cuantasVecesFueVotada().shouldBe(1)
        }
      }

      describe("Req: Saber si un usuario puede ver una publicacion de otro") {
        juana.agregarAmigo(pepe)

        it("el usuario siempre puede ver sus publicaciones sin importar el permiso") {
          fotoEnCuzco.puedeSerVistoPor(juana).shouldBeTrue()
          videoDeCumpleanios.puedeSerVistoPor(juana).shouldBeTrue()
          saludoCumpleanios.puedeSerVistoPor(juana).shouldBeTrue()
        }
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
        juana.agregarPublicacionYPermiso(saludoVacaciones, Excluyente(mutableListOf(marcos, diego)))
        juana.agregarPublicacionYPermiso(fotoEnArequipa, SoloAmigos(juana.amigos))
        val listaMejoresAmigos = mutableListOf(ferAsam, luchoRobles, faloiFede)

        it("mejores Amigos: ferAsam, luchoRobles, faloiFede") {
          juana.mejoresAmigos().shouldContainExactlyInAnyOrder(listaMejoresAmigos)
        }
      }
      describe("Req 6: cual es el amigo mas popular de un usuario") {

        it("el amigo mas popular de juana es luchoRobles") {
          juana.amigoMasPopular().shouldBe(faloiFede)
        }
        it("ferAsam no es el amigo mas popular de juana") {
          juana.amigoMasPopular().shouldNotBe(ferAsam)
        }
        it("luchoRobles no es el amigo mas popular de juana") {
          juana.amigoMasPopular().shouldNotBe(luchoRobles)
        }
      }
    }
  }
})