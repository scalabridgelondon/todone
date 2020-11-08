package todone.component

import scala.util._
import org.scalajs.dom.console
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.web.html._

import todone.api.Api
import todone.data._

@react object Controller {

  val component = FunctionalComponent[Unit]{ _ =>
    object State {
      val (tasks, updateTasks) = useState(Tasks.empty)
    }

    object Effects {
      implicit val ec = Api.ec

      val tasks: () => Unit = () =>
        Api.tasks.onComplete{
          case Success(tasks) => State.updateTasks(tasks)
          case Failure(err) => console.error(err.getMessage())
        }

      val close: Id => Unit = id =>
      Api.close(id).onComplete(_ => tasks())
    }

    // Initialize tasks from the server
    useEffect(Effects.tasks, List.empty)

    Container(
      left = div(
        ProjectList(List(Project("project 1"), Project("project 2"))),
        TagList(List(Tag("scala"), Tag("programming")))
      ),
      right = TaskList(tasks = State.tasks, close = Effects.close)
    )
  }
}
