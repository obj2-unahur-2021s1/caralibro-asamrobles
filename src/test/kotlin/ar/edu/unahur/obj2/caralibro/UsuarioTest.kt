package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val videoDeCumpleanios = Video ("SD", 3000)
    val videoDeCumpleaniosEn720 = Video ("HD720", 3000)
    val videoDeCumpleniosEn1080 = Video ("HD1080", 3000)

    fotoEnCuzco.darMeGusta()
    fotoEnCuzco.darMeGusta()
    fotoEnCuzco.darMeGusta()
    videoDeCumpleanios.darMeGusta()

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
        it ("cuanto espacio ocupa el SD despues de cambiar si calidad a HD720") {
          videoDeCumpleanios.espacioQueOcupa().shouldBe(9000)
        }
      }

      describe ("Cuantas veces fue botada una publicacion") {
        it ("Cuantas veces fue botada Cuzco se espera 3") {
          fotoEnCuzco.cuantasVecesFueVotada().shouldBe(3)
        }
        it ("Cuantas veces fue botada videoDeCumpleaños se espera 1") {
          videoDeCumpleanios.cuantasVecesFueVotada().shouldBe(1)
        }
      }
    }

    describe("Un usuario") {
      val juana = Usuario()
      juana.agregarPublicacion(fotoEnCuzco)
      juana.agregarPublicacion(saludoCumpleanios)
      juana.agregarPublicacion(videoDeCumpleanios)

      it("puede calcular el espacio que ocupan sus publicaciones") {

        juana.espacioDePublicaciones().shouldBe(553548)
      }
    }
  }

})
