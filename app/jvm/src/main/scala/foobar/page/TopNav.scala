package foobar.page

import foobar.style.ExampleStyle

import scalacss.ScalatagsCss._
import scalatags.Text.all._

object TopNav {

  val page =
    div(`class` := "top-nav",
      ul(ExampleStyle.ul,
        li(a(href := "/", `class` := "active", "Scala Training")),
        li(a(href := "/about", "About")),
        li(a(href := "/contact", "Contact"))
      )
    )

}
