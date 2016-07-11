package foobar

import foobar.page.{Contact, Index}
import org.http4s.MediaType.`text/html`
import org.http4s.dsl._
import org.http4s.headers.`Content-Type`
import org.http4s.server.ServerApp
import org.http4s.server.blaze._
import org.http4s.{HttpService, Response, StaticFile}

import scala.io.Source
import scala.util.Try
import scalatags.Text.TypedTag
import scalaz.concurrent.Task

object Server extends ServerApp {

  def page(p: TypedTag[String]): Task[Response] =
    Ok(p.render).withContentType(Some(`Content-Type`(`text/html`)))

  val service = HttpService {
    case GET -> Root              => page(Index.page)
    case GET -> Root / "contact"  => page(Contact.page)
    case req @ GET -> Root / path =>
      println("file: " + Try(Source.fromFile(path).getLines().mkString))
      StaticFile.fromResource(path.toString, Some(req)).fold(NotFound())(Task.now)
  }

  def server(args: List[String]) =
    BlazeBuilder.bindHttp(8080)
      .mountService(service, "/")
      .start
}
