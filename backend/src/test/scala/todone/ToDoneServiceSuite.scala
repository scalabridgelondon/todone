package todone

import cats.effect.IO
import munit.FunSuite
import org.http4s.{Method, Request, Response, Status}
import org.http4s.implicits._

class ToDoneServiceSuite extends FunSuite {
  test("ToDone service should return a 200 code when a GET request is made to '/api/tasks'") {
    val request: Request[IO] = Request(method = Method.GET, uri = uri"/api/tasks")
    val response: Response[IO] = ToDoneService.service.orNotFound.run(request).unsafeRunSync()
    val expected: Status = Status.Ok
    assertEquals(response.status, expected)
  }
  test("ToDone service should return a 404 code when a request is made to an undefined route") {
    val request: Request[IO] = Request(method = Method.GET, uri = uri"/api/hi")
    val response: Response[IO] = ToDoneService.service.orNotFound.run(request).unsafeRunSync()
    val expected: Status = Status.NotFound
    assertEquals(response.status, expected)
  }
}