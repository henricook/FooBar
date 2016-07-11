package foobar.page

import scalatags.Text.all._
import scalatags.Text.tags2.title


object Contact {

  val page =
    html(
      title("FooBar"),
      head(script(src:="/foobar-fastopt.js")),
      body(
        h1("FooBar"),
        TopNav.page,
        h2("Contact"),
        div(
          p("If you want to ...")
        )
      )
    )

}
