package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val videoDeCumpleaños = Video ("SD", 3000)
    val videoDeCumpleañosEn720 = Video ("HD720", 3000)
    val videoDeCumpleñosEn1080 = Video ("HD1080", 3000)

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
          videoDeCumpleaños.espacioQueOcupa().shouldBe(3000)
        }
        it ("cuanto espacio ocupa el HD720") {
          videoDeCumpleañosEn720.espacioQueOcupa().shouldBe(9000)
        }
        it ("cuanto espacio ocupa el HD1080") {
          videoDeCumpleñosEn1080.espacioQueOcupa().shouldBe(18000)
        }
        videoDeCumpleaños.cambiarCalidadDelVideo("HD720")
        it ("cuanto espacio ocupa el SD despues de cambiar si calidad a HD720") {
          videoDeCumpleaños.espacioQueOcupa().shouldBe(9000)
        }
      }
    }

    describe("Un usuario") {
      it("puede calcular el espacio que ocupan sus publicaciones") {
        val juana = Usuario()
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.agregarPublicacion(videoDeCumpleaños)
        juana.espacioDePublicaciones().shouldBe(553548)


      }
    }
  }
})
