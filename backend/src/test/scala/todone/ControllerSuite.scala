package todone

import munit.{FunSuite, ScalaCheckSuite}
import org.scalacheck.Gen
import org.scalacheck.Prop.{forAll, propBoolean}
import todone.data.{Id, State, Task}

class ControllerSuite extends FunSuite with ScalaCheckSuite {
  test("Controller should get all the tasks") {
    assertEquals(Controller.tasks.tasks.size, 2)
  }
  test("Controller should close the task when given a valid open task Id") {
    val taskId = Id(1)
    val before: Option[(Id, Task)] = Controller.tasks.tasks.find(_._1 == taskId)
    val after: Option[Task] = Controller.close(taskId)
    assertEquals(before.map(_._2.state), Some(State.open))
    assertEquals(after.map(_.state), Some(State.closed))
  }

  // Here for simplicity we're relying on state from previous spec,
  // In real life this could lead to issues with ordering, debugging, and mental load of keeping track of state.
  // One way of addressing that is setting up and tearing down the "database" before every test or suite of
  // tests by using fixtures https://scalameta.org/munit/docs/fixtures.html
  test("Controller should do nothing when given a closed task Id") {
    val taskId = Id(1)
    val result: Option[Task] = Controller.close(taskId)
    assertEquals(result.map(_.state), Some(State.closed))
  }
  test("Close method should return None when called with a nonexistent task Id") {
    val taskId = Id(12345)
    val result: Option[Task] = Controller.close(taskId)
    assertEquals(result, Option.empty[Task])
  }
  // Same as the last test but using Scalacheck to try lots of different possible id inputs
  property("Close method should return None when called with any nonexistent task Id") {
    val taskIds: List[Int] = Controller.tasks.tasks.map(_._1.id)
    val nonexistentTaskIdGenerator = Gen.choose(-10000,10000) suchThat(n => !taskIds.contains(n))
    forAll(nonexistentTaskIdGenerator) {n => Controller.close(Id(n)).isEmpty}
  }
}
