package foobar.page

import foobar.style.ExampleStyle

import scalacss.Defaults._
import scalacss.ScalatagsCss._
import scalatags.Text._
import scalatags.Text.all._
import scalatags.Text.tags2.title

object Index {

  val page =
    html(
//      ExampleStyle.render[TypedTag[String]],
      title("FooBar"),
      head(),
      body(
        h1("FooBar"),
        TopNav.page
      )
    )

}
