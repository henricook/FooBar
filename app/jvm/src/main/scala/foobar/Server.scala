package foobar

import foobar.page.{Contact, Index}
import org.http4s.MediaType.`text/html`
import org.http4s.dsl._
import org.http4s.headers.`Content-Type`
import org.http4s.server.ServerApp
import org.http4s.server.blaze._
import org.http4s.{HttpService, Response, StaticFile}

import scalatags.Text.TypedTag
import scalaz.concurrent.Task

object Server extends ServerApp {

  def page(p: TypedTag[String]): Task[Response] =
    Ok(p.render).withContentType(Some(`Content-Type`(`text/html`)))

  val service = HttpService {
    case GET -> Root              => page(Index.page)
    case GET -> Root / "contact"  => page(Contact.page)
    case req @ GET -> Root / path =>
      StaticFile.fromResource(path.toString, Some(req)).fold(NotFound())(Task.now)
  }

  def server(args: List[String]) =
    BlazeBuilder.bindHttp(8080)
      .mountService(service, "/")
      .start
}
