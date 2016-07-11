package foobar.style

import scalacss.Defaults._

object ExampleStyle extends StyleSheet.Inline {
  import dsl._

  val ul = style(
    backgroundColor.blue
  )

}
