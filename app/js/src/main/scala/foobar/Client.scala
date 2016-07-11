package foobar

import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.html
import upickle.default._

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

@JSExport
object Client extends {

  val inputName  = input(name := "name", `type` := "text", size := 30)
  val inputEmail = input(name := "email", `type` := "text", size := 30)
  val inputMessage = input(name := "message", `type` := "text", size := 255)

  @JSExport
  def main(container: html.Div) = {
    val nameBox = inputName.render
    val emailBox = inputEmail.render
    val messageBox = inputMessage.render

    def update() =
      Ajax.post("/ajax/list", write(FormData(nameBox.value, emailBox.value, messageBox.value))).foreach{ xhr =>
        println(s"received ${xhr.responseText}")
      }

    container.appendChild(
      form(
        div(label(`for` := "name", "Your name:"), nameBox),
        div(label(`for` := "email", "Your email:"), emailBox),
        div(label(`for` := "message", "Your message:"), messageBox),
        input(`type` := "submit", value := "Send Email", onclick := update())
      ).render
    )
  }
}
